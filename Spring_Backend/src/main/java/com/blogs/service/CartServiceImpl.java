package com.blogs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogs.dto.CartDTO;
import com.blogs.dto.CartItemDTO;
import com.blogs.entities.Cart;
import com.blogs.entities.CartItem;
import com.blogs.entities.Product;
import com.blogs.repository.CartItemRepository;
import com.blogs.repository.CartRepository;
import com.blogs.repository.ProductRepository;
import com.blogs.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public CartDTO addProductToCart(Long userId, CartItemDTO cartItemDTO) {
        //The method first retrieves the user's cart using the getOrCreateCart(userId) method. This method likely checks if a cart already exists for the user. If it does, it returns the existing cart; if not, it creates a new cart for the user.
    	Cart cart = getOrCreateCart(userId);

        // Find the product
        Product product = productRepository.findById(cartItemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the product already exists in the cart
        Optional<CartItem> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getProductId().equals(cartItemDTO.getProductId()))
                .findFirst();

        if (existingCartItem.isPresent()) {
            // Update the quantity and total price
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + cartItemDTO.getQuantity());
            cartItem.setTotalPrice(cartItem.getQuantity() * cartItem.getPrice());
            cartItemRepository.save(cartItem);
        } else {
            // Create a new cart item
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemDTO.getQuantity());
            cartItem.setPrice(product.getPrice());
            cartItem.setTotalPrice(cartItemDTO.getQuantity() * product.getPrice());
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
            cartItemRepository.save(cartItem);
        }

        return getCartByUserId(userId);
    }

    @Override
    public CartDTO removeProductFromCart(Long userId, Long productId) {
        Cart cart = getOrCreateCart(userId);

        // Remove the product from the cart
        cartItemRepository.deleteByCart_CartIdAndProduct_ProductId(cart.getCartId(), productId);

        return getCartByUserId(userId);
    }

    @Override
    public CartDTO getCartByUserId(Long userId) {
        Cart cart = getOrCreateCart(userId);

        List<CartItemDTO> cartItemDTOs = cart.getCartItems().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getCartId());
        cartDTO.setUserId(cart.getUser().getUserId());
        cartDTO.setCartItems(cartItemDTOs);
        cartDTO.setTotalAmount(calculateTotalAmount(cart.getCartId()));

        return cartDTO;
    }

    @Override
    public double calculateTotalAmount(Long cartId) {
        List<CartItem> cartItems = cartItemRepository.findByCart_CartId(cartId);
        return cartItems.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    private Cart getOrCreateCart(Long userId) {
        Cart cart = cartRepository.findByUser_UserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found")));
            cartRepository.save(cart);
        }
        return cart;
    }

    private CartItemDTO convertToDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setCartItemId(cartItem.getCartItemId());
        cartItemDTO.setProductId(cartItem.getProduct().getProductId());
        cartItemDTO.setProductName(cartItem.getProduct().getName());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setPrice(cartItem.getPrice());
        cartItemDTO.setTotalPrice(cartItem.getTotalPrice());
        return cartItemDTO;
    }

}
