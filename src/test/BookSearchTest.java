package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Book;
import main.BookStore;

public class BookSearchTest {

	BookStore bookStore;
	Book [] searchResults;
	
	@Before
	public void setUp(){
		bookStore = new BookStore();
		searchResults = bookStore.list("Generic");
	}
	
	@Test
	public void testTitleSearch() {
		searchResults = bookStore.list("Generic");
		assertEquals(searchResults[0].getTitle(), "Generic Title");
		assertEquals(searchResults[1].getTitle(), "Generic Title");
		
	}
	
	@Test
	public void testAuthorSearch(){
		searchResults = bookStore.list("cunning Bastard");
		assertEquals(searchResults[0].getAuthor(), "Cunning Bastard");
		assertEquals(searchResults[1].getAuthor(), "Cunning Bastard");
	}
	
	@Test
	public void testEmptySearchResult(){
		searchResults = bookStore.list("bla bla");
		assertNull(searchResults[0]);
	}
	
	@Test
	public void testShowAllBooks(){
		searchResults = bookStore.showAll();
		assertEquals(searchResults[0].getAuthor(), "Average Swede");
	}

}
