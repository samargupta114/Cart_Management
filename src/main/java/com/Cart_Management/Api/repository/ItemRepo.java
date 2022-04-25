package com.Cart_Management.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Cart_Management.Api.model.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
