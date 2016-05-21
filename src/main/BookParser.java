package main;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class BookParser {
	
	/*
	 *	Parse the URL text file and return the list of books
	 */

	public static ArrayList <Book> getBookList(){
		Scanner scan = null;
		try {
			URL url = new URL("http://www.contribe.se/bookstoredata/bookstoredata.txt");
			scan = new Scanner(url.openStream(), "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String parserCurrentLine = "";
		String bookItem [];
		ArrayList <Book> bookList = new ArrayList<Book>();
		
		while(scan.hasNextLine()){
			parserCurrentLine += scan.nextLine();
			bookItem = parserCurrentLine.split(";");
			
			Book book = new Book();
			book.setTitle(bookItem[0]);
			book.setAuthor(bookItem[1]);
			book.setPrice(new BigDecimal(bookItem[2].replaceAll(",", "")));
			book.setQuantity(Integer.parseInt(bookItem[3]));
			bookList.add(book);
			bookItem = null;
			parserCurrentLine = "";
		}
		
		scan.close();
		
		return bookList;
		
	}
}
