package com.blogs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogs.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	List<CartItem> findByCart_CartId(Long cartId);
    void deleteByCart_CartIdAndProduct_ProductId(Long cartId, Long productId);
}
