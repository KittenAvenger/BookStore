package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		BookStore bookStore = new BookStore();
		boolean isRunning = true;
		Scanner scan = new Scanner(System.in);
		System.out.println("Hello and welcome to the Book Store! Choose an option in the list.\n");
		while(isRunning){
			String choice = "";
			System.out.printf("\n");
			System.out.println("1. Show all books in store");
			System.out.println("2. Search book after title or author");
			System.out.println("3. Add book to cart");
			System.out.println("4. Remove book from cart");
			System.out.println("5. Checkout books from cart");
			System.out.println("6. Exit book store");
			System.out.println("7. Add books to the store inventory");
			choice = scan.nextLine();
						
			//TODO check if integer before parsing
			//TODO add view cart
			
			switch (Integer.parseInt(choice)){
				case 1: 
					Book [] fullList = bookStore.showAll();
					System.out.printf("%-20s %5s %25s \n\n", "Title", "Author", "Price");
					
					for(Book book: fullList){
						prettyPrint(book);
					}
					
					break;
					
				case 2:
					System.out.println("Enter author or title you want to search for: ");
					String query = scan.nextLine();
					Book [] searchResults = bookStore.list(query);
					if(searchResults[0] != null){
						System.out.printf("%-20s %5s %25s \n\n", "Title", "Author", "Price");
						
						for(Book book: searchResults){
							if(book != null)
								prettyPrint(book);
						}
					}
					
					else
						System.out.println("No results found");
					
					
					break;
					//TODO confirm that a book was added
				case 3:
					System.out.println("Add the title of the book you want to add to cart: ");
					String title = scan.nextLine();
					Book book = new Book();
					book.setTitle(title);
					bookStore.addBookToCart(book);
					break;
				case 4:
					System.out.println("Remove a book from your cart by writing the title: ");
					String titleRemove = scan.nextLine();
					Book book2 = new Book();
					book2.setTitle(titleRemove);
					bookStore.removeBookFromCart(book2);
					break;
					//TODO add status messages to status codes
					
				case 5:	
					ArrayList <Book> cart = bookStore.cart;
					int [] statusList = bookStore.buy(cart);
					
					for(int i = 0; i < statusList.length; i++){
						System.out.printf("%-20.20s %20d \n", cart.get(i).getTitle(), statusList[i]);
					}
					break;
					
				case 6:
					System.out.println("Book store exited");
					isRunning = false;
					break;
				case 7:
					System.out.println("Enter title, author, price and quantity: ");
					String titleAdd = scan.nextLine();
					String author = scan.nextLine();
					String price = scan.nextLine();
					String quantity = scan.nextLine();
					Book book3 = new Book();
					
					book3.setTitle(titleAdd);
					book3.setAuthor(author);
					book3.setPrice(new BigDecimal(price.replace(",", "")));
					bookStore.add(book3, Integer.parseInt(quantity));
					break;
				default:
					break;					
			}
		}
		
		scan.close();
	}
	
	public static void prettyPrint(Book book){
		System.out.printf("%-20.20s %-20.20s %10d \n", book.getTitle(), book.getAuthor(), book.getPrice().intValue());
	}

}
