package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;

import main.Book;
import main.BookParser;

public class BookParserTest {
	
	@Test
	public void testBookProperties(){
		
		ArrayList<Book> bookList = BookParser.getBookList();
		
		assertEquals(bookList.get(0).getTitle(), "Mastering едц");
		assertEquals(bookList.get(0).getAuthor(), "Average Swede");
		assertEquals(bookList.get(0).getPrice(), new BigDecimal("762.00"));
		assertEquals(bookList.get(0).getQuantity(), 15);
		
		assertEquals(bookList.get(1).getTitle(), "How To Spend Money");
		assertEquals(bookList.get(1).getAuthor(), "Rich Bloke");
		assertEquals(bookList.get(1).getPrice(), new BigDecimal("1000000.00"));
		assertEquals(bookList.get(1).getQuantity(), 1);

	}

}
