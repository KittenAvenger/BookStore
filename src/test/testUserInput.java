package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.*;

public class testUserInput {

	private BookStore bookStore;
	private Book book;

	@Before
	public void setUp(){
		bookStore = new BookStore();
		book = new Book();
	}
	
	@Test
	public void testAddBook() {
		
		book.setTitle("How To spend money");
		assertTrue(bookStore.add(book, 2));
		
		Book book2 = new Book();
		
		book2.setTitle("Something");
		book2.setAuthor("blabla");
		book2.setPrice(new BigDecimal(100.0));
		
		assertFalse(bookStore.add(book2, 5));
	}
	
	@Test
	public void testSearchTitleOrAuthor(){
		Book [] list = bookStore.list("Generic");
		assertEquals(list[0].getTitle(), "Generic Title");
		
		list = bookStore.list("Cunning");
		assertEquals(list[0].getTitle(), "Random Sales");
		
	}
	
	@Test
	public void testDisplayAll(){
		Book [] list = bookStore.showAll();
		
		assertEquals(list[list.length - 1].getTitle(), "Desired");
	}
	
	@Test
	public void testAddBookToCart(){
	//	ArrayList<Book> cart = new ArrayList<Book>();
		book.setTitle("Generic Title");
		bookStore.addBookToCart(book);
		ArrayList<Book> cart = bookStore.cart;
		assertEquals(cart.get(0).getTitle(), book.getTitle());
		
		book.setTitle("generic title");
		bookStore.addBookToCart(book);
		assertEquals(cart.get(0).getQuantity(), 2);
	}
	
	@Test
	public void testRemoveBookFromCart(){
		book.setTitle("Generic Title");
		bookStore.addBookToCart(book);
		bookStore.removeBookFromCart(book);
		assertTrue(bookStore.cart.size() == 0);
		
		book.setTitle("something");
		bookStore.removeBookFromCart(book);
		assertTrue(bookStore.cart.size() == 0);
		
		book.setTitle("Generic Title");
		bookStore.addBookToCart(book);
		
		Book book2 = new Book();
		book2.setTitle("something");
		bookStore.removeBookFromCart(book2);
		assertTrue(bookStore.cart.size() == 1);
		
		bookStore.addBookToCart(book);
		assertTrue(bookStore.cart.get(0).getQuantity() == 2);
	}
	
	@Test
	public void testCheckout(){
		book.setTitle("Generic Title");
		bookStore.addBookToCart(book);
		Book book2 = new Book();
		book2.setTitle("Whatever");
		bookStore.addBookToCart(book2);
		int [] statusList = bookStore.buy(bookStore.cart);
		
		assertEquals(statusList[0], 0);
		assertEquals(statusList[1], 3);
		
		Book book3 = new Book();
		book3.setTitle("How To Spend Money");
		bookStore.addBookToCart(book3);
		bookStore.addBookToCart(book3);
		bookStore.addBookToCart(book3);

		
		statusList = bookStore.buy(bookStore.cart);
		
		assertEquals(statusList[2], 1);
	}

	//TODO write tests for adding book to inventory, removing, checkout and adding book to cart
}
