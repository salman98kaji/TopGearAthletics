package com.blogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogs.dto.CartDTO;
import com.blogs.dto.CartItemDTO;
import com.blogs.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartDTO> addProductToCart(@RequestParam("userId") Long userId, @RequestBody CartItemDTO cartItemDTO) {
        CartDTO updatedCart = cartService.addProductToCart(userId, cartItemDTO);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<CartDTO> removeProductFromCart(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {
        CartDTO updatedCart = cartService.removeProductFromCart(userId, productId);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<CartDTO> viewCart(@RequestParam("userId") Long userId) {
        CartDTO cartDTO = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotalAmount(@RequestParam("userId") Long userId) {
        CartDTO cartDTO = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(cartDTO.getTotalAmount(), HttpStatus.OK);
    }
}
