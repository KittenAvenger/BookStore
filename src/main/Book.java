package main;

import java.math.BigDecimal;

public class Book {
	private String title;
    private String author;
    private BigDecimal price;
    private int quantity;
    
    public Book (){
    	this.title = "empty";
    	this.author = "nobody";
    	this.price = new BigDecimal(0);
    	this.quantity = 0;
    }
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
