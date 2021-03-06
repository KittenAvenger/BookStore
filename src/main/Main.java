package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static BookStore bookStore;
	private static ArrayList <Book> finalCart;
	
	public static void main(String[] args) {
		bookStore = new BookStore();		//	Create book inventory and populate from the text file
		boolean isRunning = true, validNumber = false;
		Scanner scan = new Scanner(System.in);
		System.out.println("Hello and welcome to the Book Store! Choose an option in the list.\n");
		
		while(isRunning){
			
			String input = "";
			System.out.printf("\n");
			System.out.println("1. Show all books in store");
			System.out.println("2. Search book after title or author");
			System.out.println("3. Add book to cart");
			System.out.println("4. Remove book from cart");
			System.out.println("5. Checkout books from cart");
			System.out.println("6. Exit book store");
			System.out.println("7. Add books to the store inventory");
			input = scan.nextLine();
						
						
			int choice = 0;
			
			try{
				 choice = Integer.parseInt(input);
				 validNumber = true;
			}
			catch (NumberFormatException e){
				validNumber = false;
				System.out.println("Please input a number");	//	Check if the input is a number from 1-7 otherwise ask for input again
			}
			
			while(validNumber){
				
				/*
				 *	Every number choice corresponds to a case statement that enables the user to
				 *	perform different functions such as search for books, add a book to cart, 
				 *	checkout, exit etc
				 */
				
				switch (choice){
				case 1: 
					
					Book [] fullList = bookStore.showAll();
					System.out.printf("%-20s %5s %25s \n\n", "Title", "Author", "Price");
					
					for(Book book: fullList){
						prettyPrint(book);
					}
					validNumber = false;
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
					
					validNumber = false;
					break;
					
				case 3:
					
					System.out.println("Add the title of the book you want to add to cart: ");
					String title = scan.nextLine();
					Book book = new Book();
					if(title.length() > 0){
						book.setTitle(title);
						bookStore.addBookToCart(book);
						validNumber = false;
						break;
					}
					else
						System.out.println("Title must have at least one letter!");
						break;	
						
				case 4:
					
					System.out.println("Remove a book from your cart by writing the title: ");
					String titleRemove = scan.nextLine();
					Book book2 = new Book();
					book2.setTitle(titleRemove);
					bookStore.removeBookFromCart(book2);
					validNumber = false;
					break;
					
				case 5:	
					
					ArrayList <Book> cart = bookStore.cart;
					finalCart = new ArrayList<Book>();
					int [] statusList = bookStore.buy(cart);
					
					String OK = "OK";
					String NOT_IN_STOCK = "NOT_IN_STOCK";
					String DOES_NOT_EXIST = "DOES_NOT_EXIST";
					String status = "";
					
					if(statusList.length == 0){
						System.out.println("Your cart is empty");
						validNumber = false;
						break;
					}
					
					for(int i = 0; i < statusList.length; i++){
						if(statusList[i] == 0){
							status = OK;
							finalCart.add(findBookInInventory(cart.get(i)));
						}
						else if(statusList[i] == 1)
							status = NOT_IN_STOCK;
						else if(statusList[i] == 2)
							status = DOES_NOT_EXIST;
						System.out.printf("%-20.20s %20s \n", cart.get(i).getTitle(), status);
					}
					
					int sum = 0;
					if(finalCart != null){
						
						for(Book validBook: finalCart){
							sum += validBook.getPrice().intValue() * validBook.getQuantity();
						}
						
						System.out.printf("Sum is: %d", sum);
						
						validNumber = false;
						break;
					}
					
					validNumber = false;
					break;
									
				case 6:
					
					System.out.println("Book store exited");
					isRunning = false;
					validNumber = false;
					break;
					
				case 7:
					
					System.out.println("Enter title, author, price and quantity: ");
					String titleAdd = scan.nextLine();
					String author = scan.nextLine();
					String price = scan.nextLine();
					String quantity = scan.nextLine();
					Book book3 = new Book();
					int quantityVal = 0;
					BigDecimal priceVal;
					
					try{
						quantityVal = Integer.parseInt(quantity);
						priceVal = new BigDecimal(price.replace(",", ""));
					}
					catch (NumberFormatException e){
						System.out.println("Input valid price and/or quantity");
						break;
					}
					
					book3.setTitle(titleAdd);
					book3.setAuthor(author);
					book3.setPrice(priceVal);
					bookStore.add(book3, quantityVal);
					validNumber = false;
					break;
					
				default:
					
					System.out.println("Please input a number between 1-7");
					validNumber = false;
					break;					
				}
			}
			
		}
		
		scan.close();
	}
	
	/*
	 *	Print a nicely formatted string of each book 
	 */
	
	public static void prettyPrint(Book book){
		System.out.printf("%-20.20s %-20.20s %10d \n", book.getTitle(), book.getAuthor(), book.getPrice().intValue());
	}
	
	public static Book findBookInInventory(Book book){
		Book finalBook = new Book();
		for(Book book2: bookStore.getBookList()){
			if(book.getTitle().equalsIgnoreCase(book2.getTitle())){
				finalBook.setTitle(book2.getTitle());
				finalBook.setAuthor(book2.getAuthor());
				finalBook.setPrice(book2.getPrice());
				finalBook.setQuantity(book.getQuantity());
			}
		}
		
		return finalBook;
	}

}
