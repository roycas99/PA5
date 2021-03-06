/*
 * Program description: this program allow user to choice file,then sorted it based on their id,price,name etc and after that user will save it into new file
 * Author: your name
 * Assignment: PA5
 * Date: May, 01 2020
 * Class: CSCI 1082 
 */
package edu.century.pa5;

import java.util.Comparator;

public class Product {
	private String name;
	private int id;
	private String description;
	private Double price;

	public String getName() {
		return name;
	}

	// Constructor of the class
	public Product(String name, int id, String description, Double price) {
		super();
		this.name = name;
		this.id = id;
		this.description = description;
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	// use to compare by id
	public static Comparator<Product> CompareById = new Comparator<Product>() {

		@Override
		public int compare(Product p1, Product p2) {

			return p1.getId() - p2.getId();
		}
	};
	// there is bug in this. is not working when u call from GUI
	public static Comparator<Product> CompareByPrice = new Comparator<Product>() {

		@Override
		public int compare(Product p1, Product p2) {

			return Double.compare(p1.price, p2.price);
		}
	};

	public static Comparator<Product> CompareByName = new Comparator<Product>() {

		@Override
		public int compare(Product p1, Product p2) {
			Product prd1 = (Product) p1;
			Product prd2 = (Product) p2;

			return prd1.getName().compareTo(prd2.getName());
		}
	};
	public static Comparator<Product> CompareByDescription = new Comparator<Product>() {

		@Override
		public int compare(Product p1, Product p2) {
			Product prd1 = (Product) p1;
			Product prd2 = (Product) p2;
			return prd1.description.compareTo(prd2.getDescription());
		}

	};

	@Override

	public String toString() {
		return "Product [name=" + name + ", id=" + id + ", description=" + description + ", price=" + price + "]"
				+ "\n";
	}

}// end of Product Class
