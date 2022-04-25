package com.Cart_Management.Api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cart_Management.Api.model.Cart;

public interface CartRepository  extends JpaRepository<Cart, Integer>{


}
