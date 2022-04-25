package com.Cart_Management.Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Cart_Management.Api.model.Cart;
import com.Cart_Management.Api.model.Item;
import com.Cart_Management.Api.repository.CartRepository;
import com.Cart_Management.Api.repository.ItemRepo;
import com.Cart_Management.Api.service.CartService;

import java.lang.annotation.Repeatable;
import java.util.*;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;

	// GET MAPPINGS

	@GetMapping("/cart") // get all carts
	public List<Cart> getcarts() {
		return cartService.getallcarts();
	}

	@GetMapping(path="/cart/{id}") // get particular cart
	public Optional<Cart> getparticularcart(@PathVariable(value = "id") int cartid) 
	{
		return cartService.getsinglecart(cartid);
	}

	@GetMapping("/cart/{id}/items") // get items of specific cart
	public List<Item> getallitemsofthecart(@PathVariable(name = "id") int cartid) {
		return cartService.getitemsofcart(cartid);
	}

	@GetMapping("/cart/{id}/items/{itemid}") // get particular item of cart
	public Optional<Item> getthisitemsofthecart(
			@PathVariable(name = "id") int cartid,
			@PathVariable(name = "itemid") int itemid) {

		return cartService.getitem(cartid, itemid);
	}
	
	@GetMapping(path="/cart" ,params="fn")
	public Cart getthiscart(@RequestParam(name="fn") String func,@RequestBody Cart cart)
	{ 
		String name=cart.getCustomer_name();
		
		return cartService.getcartbyName(name);
	}
	
	
	

	
	@GetMapping(path="/cart/{id}",params = "fn") // get the total price of the cart
	public String gettotalprice(
			@PathVariable(value="id") int cartid,
			@RequestParam(name = "fn") String func) {
		
		return cartService.getthis(cartid,func);
	}

	// POST MAPPINGS

	@PostMapping("/cart") // add a new cart
	public Cart addnewcart(@RequestBody Cart cart) {
		return cartService.savenewcart(cart);
	
	}

	// PUT MAPPINGS

	@PutMapping("/cart/{id}/items") // add item to cart
	public ResponseEntity<Cart> additemstocart
	(@PathVariable(name = "id") int cartid, @RequestBody Item it) {
		return cartService.additems(cartid, it);

	}

	@PutMapping("/cart/{id}/items/{itemid}") // will update the item quan and price according to the need
	public ResponseEntity<Cart> increasequantityofitem(
			@PathVariable(name = "id") int cartid,
			@PathVariable(name = "itemid") int itemid, 
			@RequestBody Item it) {
	return cartService.updatequantity(cartid, itemid, it.getQuantity());

	}

	// DELETE MAPPINGS

	@DeleteMapping("/cart") // delete all carts
	public ResponseEntity<Cart> deleteallcarts() {
		return cartService.deleteall();
	}

	@DeleteMapping("/cart/{id}") // delete particular cart
	public ResponseEntity<Cart> deleteparticularcart(@PathVariable(name = "id") int cartid) {
		return cartService.deletecart(cartid);
	}

	@DeleteMapping("/cart/{id}/item/{itemid}") // delete particular item from particular cart
	public ResponseEntity<Cart> deleteitemfromcart(
			@PathVariable(name = "id") int cartid,
			@PathVariable(name = "itemid") int itemid) {
		return cartService.deleteitem(cartid, itemid);
	}

}
