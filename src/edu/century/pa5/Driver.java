package edu.century.pa5;

import java.util.ArrayList;

import java.util.Collections;

public class Driver {

	public static void main(String[] args) {
		ArrayList<Product>productList= new ArrayList<Product>();
		Product p1= new Product("Iphone", 1, "Electronics", 400.990);
		Product p2= new Product("HP", 2, "Electronics", 400.22);
		Product p3= new Product("LG", 0, "Electronics", 89.99);
		productList.add(p1);
		productList.add(p2);
		productList.add(p3);
		Collections.sort(productList,Product.CompareByPrice);
		for (Product products : productList) {
			
			System.out.println(products);
		}
		

	}

}