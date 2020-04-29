package edu.century.pa5;

import java.util.Comparator;

public class ComapareByID implements Comparator<Product> ,Comparable<Product>{

	@Override
	public int compare(Product p1, Product p2) {
		
		return p1.getId()-p2.getId();
	}

	@Override
	public int compareTo(Product o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
