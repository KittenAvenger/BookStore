package main;

import java.util.ArrayList;

public interface BookList {
	public Book[] list(String searchString);
	public boolean add(Book book, int quantity);
    public int[] buy(ArrayList<Book> books);
}
