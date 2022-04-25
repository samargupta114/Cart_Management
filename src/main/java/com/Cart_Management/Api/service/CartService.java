package com.Cart_Management.Api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.Cart_Management.Api.exception.EmptycartException;
import com.Cart_Management.Api.exception.NoItemIncartExceptioin;
import com.Cart_Management.Api.model.Cart;
import com.Cart_Management.Api.model.Item;
import com.Cart_Management.Api.repository.CartRepository;
import com.Cart_Management.Api.repository.ItemRepo;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ItemRepo itemrepo;

	// FOR GET METHODS

	// get all the carts
	public List<Cart> getallcarts() {
		if(cartRepository.count()<=0) {
			throw new  EmptycartException("no cart present");
		}
		return cartRepository.findAll();
	}

	// get single cart
	public Optional<Cart> getsinglecart(int cartid) {
		if(cartRepository.existsById(cartid)==false) {
			throw new  EmptycartException("no cart with cartid : "+cartid+" is present.");
		}
		return cartRepository.findById(cartid);
	}

	// get items of particular cart
	public List<Item> getitemsofcart(int cartid) {
		if(cartRepository.getById(cartid).getItems().size()==0) {
			throw new NoItemIncartExceptioin("no items present in this cart, ADD ITEMS !");
		}
		return cartRepository.getById(cartid).getItems();
	}

	// get particular item of cart
	public Optional<Item> getitem(int cartid, int itemid)
	{
		if(itemrepo.existsById(itemid)==false) {
			throw new NoItemIncartExceptioin("no item with itemid :"+ itemid+"is present");
		}
		return itemrepo.findById(itemid);
	}

	// get total price of the cart
	public String getthis(int cartid, String func) 
	{
		if(cartRepository.getById(cartid).getItems().size()==0) {
			throw new NoItemIncartExceptioin("no items present in this cart .ADD ITEMS!");
		}
		if (func.equalsIgnoreCase("totalprice")) 
		{
			long ans = 0;
			for (Item it : cartRepository.getById(cartid).getItems()) { // use java 8 here streams
				ans += it.getPrice();
			}
			return "total price  for " + cartRepository.getById(cartid).getCustomer_name() + " is :" + ans;
		} 
		else if(func.equalsIgnoreCase("gettotalitems"))
		{ long ans=0;
		for(Item it:cartRepository.getById(cartid).getItems()) {
			ans+=it.getQuantity();
		}
		return "total number of items in  cart of "+ cartRepository.getById(cartid).getCustomer_name() +" is : "+ans;
			
		}else
			return null;

	}

	// FOR POST METHODS

	// saves a new cart into database
	public Cart savenewcart(Cart cart) {
		return cartRepository.save(cart);
	}

	// FOR PUT METHODS

	// to add more items to specific cart
	public ResponseEntity<Cart> additems(int cartid, Item it) {
		cartRepository.getById(cartid).getItems().add(it);
		itemrepo.save(it);
		return new ResponseEntity<Cart>(HttpStatus.OK);

	}

	// to update item quantity and price
	public ResponseEntity<Cart> updatequantity(int cartid, int itemid, int quantity) {

		if(itemrepo.existsById(itemid)==false) {
			throw new NoItemIncartExceptioin("No item with this itemid : "+itemid+" is present.");
		}
		int newprice = itemrepo.getById(itemid).getPrice() / itemrepo.getById(itemid).getQuantity();
		newprice *= quantity;
		int newquantity = quantity;
		List<Item> li = cartRepository.getById(cartid).getItems();
		for (Item i : li) {
			if (i.getItemid() == itemid) {
				i.setPrice(newprice);
				i.setQuantity(quantity);
				itemrepo.save(i);
			}
		}
		return new ResponseEntity<Cart>(HttpStatus.OK);

	}

	// FOR DELETE METHODS

	// to delete all carts and their items from the db
	public ResponseEntity<Cart> deleteall() {
		if(cartRepository.count()<=0) {
			throw new  EmptycartException("no cart present .");
		}
		cartRepository.deleteAll();
		itemrepo.deleteAll();
		return new ResponseEntity<Cart>(HttpStatus.OK);
	}

	// delete particular cart from db
	public ResponseEntity<Cart> deletecart(int cartid) {
		if(cartRepository.existsById(cartid)==false) {
			throw new  EmptycartException("no cart with cartid : "+cartid +" is present.");
		}
		cartRepository.deleteById(cartid);
		return new ResponseEntity<Cart>(HttpStatus.OK);
	}

	// delete specific item from specific cart
	public ResponseEntity<Cart> deleteitem(int cartid, int itemid) {
		if(itemrepo.existsById(itemid)==false) {
			throw new NoItemIncartExceptioin("no item with itemid : "+itemid+" is present.");
		}
		itemrepo.deleteById(itemid);
		return new ResponseEntity<Cart>(HttpStatus.OK);
	}

	public Cart getcartbyName(String name) {
		
			if(cartRepository.count()<=0) {
				throw new  EmptycartException("no cart present .");
			}
		for(Cart c:cartRepository.findAll()) {
			if(c.getCustomer_name().equalsIgnoreCase(name)) {
				return c;
			}
		}
		return null;
	}

}
