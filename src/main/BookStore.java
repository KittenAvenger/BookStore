package main;

import java.util.ArrayList;

public class BookStore implements BookList {

	ArrayList<Book> bookList;
	public ArrayList<Book> cart;
	
	public BookStore(){
		this.bookList = BookParser.getBookList();
		this.cart = new ArrayList<Book>();
	}
	
	public Book[] list(String searchString) {
		Book [] searchResults = new Book [bookList.size()];
		int i = 0;
		
		for(Book book: bookList){
			if(book.getTitle().toLowerCase().startsWith(searchString.toLowerCase()) 
					|| book.getAuthor().toLowerCase().startsWith(searchString.toLowerCase())){
				searchResults[i] = book;
				i++;
			}
		}
		return searchResults;
	}
	
	public Book [] showAll(){
		Book [] fullBookList = new Book [bookList.size()];
		int i = 0;
		
		for(Book book: bookList){
			fullBookList[i] = book;
			i++;
		}
		
		return fullBookList;
	}

	//TODO check if author also matches
	@Override
	public boolean add(Book book, int quantity) {
		for(Book book2: bookList){
			if(book.getTitle().equalsIgnoreCase(book2.getTitle())){
				book2.setQuantity(book2.getQuantity() + quantity);
				System.out.println("Your book was added to the inventory");
				return true;
			}	
		}
		
		book.setQuantity(quantity);
		bookList.add(book);
		System.out.println("Your book was added to the inventory");
		return false;
	}

	@Override
	public int[] buy(ArrayList<Book> cart) {
		int size = cart.size();
		int [] statusList = new int [size];
		int i = 0;
		
		for(Book book: cart){
			if(isInInventory(book)){
				if(isQuantityEnough(book))
					statusList[i] = 0;
				else
					statusList[i] = 1;
			}
			else
				statusList[i] = 2;
			i++;
		}
		
		return statusList;
	}
	
	//TODO book names are ambigious and so are some of the authors, maybe make a choice list?
	
	public void addBookToCart(Book book){
		
		
			for(int i = 0; i < cart.size(); i++){
				if(book.getTitle().equalsIgnoreCase(cart.get(i).getTitle())){
					cart.get(i).setQuantity(cart.get(i).getQuantity() + 1);;
					System.out.println("Book was added to cart");
					return;
				}
			}
		
		book.setQuantity(1);
		cart.add(book);
		System.out.println("Book was added to cart");

	}
	
	public void removeBookFromCart(Book book){
		for(int i = 0; i < cart.size(); i++){
			if(book.getTitle().equalsIgnoreCase(cart.get(i).getTitle())){
				cart.remove(i);
				System.out.println("Book was removed from cart");
				return;
			}
		}
		
		System.out.println("Book was not found in cart");
	}
	
	public boolean isInInventory(Book book){
		for(int i = 0; i < bookList.size(); i++){
			if(book.getTitle().equalsIgnoreCase(bookList.get(i).getTitle())){
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isQuantityEnough(Book book){
		for(int i = 0; i < bookList.size(); i++){
			if(book.getTitle().equalsIgnoreCase(bookList.get(i).getTitle()) 
					&& book.getQuantity() <= bookList.get(i).getQuantity()){
				return true;
			}
		}
		
		return false;
	}
}
