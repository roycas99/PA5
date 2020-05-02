package edu.century.pa5;

import java.util.ArrayList;

import java.util.Collections;

public class Driver {

	public static void main(String[] args) {
		ArrayList<Product>productList= new ArrayList<Product>();
		Product p1= new Product("Iphone", 1, "Electronics", 41.55);
		Product p2= new Product("HP", 2, "Electronics", 42.22);
		Product p3= new Product("LG", 0, "Electronics", 42.00);
		Product p4= new Product("Aokia", 3,"Elect",555.5);
		productList.add(p1);
		productList.add(p2);
		productList.add(p3);
		productList.add(p4);
		//Collections.sort(productList,Product.CompareByName);
		Collections.sort(productList,Product.CompareByPrice);
		for (Product products : productList) {
			
			System.out.println(products);
		}
		

	}

}
