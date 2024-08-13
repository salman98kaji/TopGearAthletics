package com.blogs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogs.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	Cart findByUser_UserId(Long userId);
}
