/*
 * Program description:this program allow user to choice file,then sorted it based on their id,price,name etc and after that user will save it into new file
 * Author: your name
 * Assignment: PA5
 * Date: May, 01 2020
 * Class: CSCI 1082 
 */
package edu.century.pa5;

import java.awt.*;
import java.io.*;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.awt.FlowLayout;

public class ShoppingCartGui extends JFrame {
	// instance Variable
	private JPanel contentPane;
	private JPanel originalPanel;
	private JPanel sortPanel;
	private JFileChooser fileChoser;
	private JButton browse;
	private JButton sort;
	// private JComboBox<Product> box;
	private JTextArea originalArea;
	private JTextArea sortArea;
	private JComboBox<String> select;
	private JTextField fileName;
	private ArrayList<Product> productList = new ArrayList<Product>();
	private PrintWriter custOutput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShoppingCartGui frame = new ShoppingCartGui();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();

				}
			}

		});
	} // the end of Main Function

	/**
	 * Create the frame.
	 */
	public ShoppingCartGui() {
		// set up for JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		// setting the textField
		fileName = new JTextField();
		fileName.setBounds(69, 11, 112, 20);
		contentPane.add(fileName);
		fileName.setColumns(13);
		// button browse
		browse = new JButton("Browse");
		browse.setBounds(203, 10, 78, 23);
		contentPane.add(browse);
		browse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fileChoser = new JFileChooser("C:\\Users\\abshi\\Desktop\\wow\\PA5");
				FileFilter filter = new FileNameExtensionFilter("CSV File", "csv");
				fileChoser.setFileFilter(filter);
				int value = fileChoser.showOpenDialog(null);
				// if OK button was clicked
				if (value == JFileChooser.APPROVE_OPTION) {
					File file = fileChoser.getSelectedFile();
					fileName.setText(file.getName());
					ArrayList<Product> product = readFile(file);
					displayProducts(product);
				}
			}
		});

		// JCOmbox setting up
		select = new JComboBox<String>(new String[] { "ID", "NAME", "DESCRIPTION", "PRICE" });
		select.setBounds(291, 11, 98, 20);
		contentPane.add(select);

		// button sort click event
		sort = new JButton("Sort");
		sort.setBounds(399, 10, 60, 23);
		contentPane.add(sort);
		sort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String sortProduct = "";

				if (select.getSelectedItem().equals("ID")) {
					productList.sort(Product.CompareById);
				} else if (select.getSelectedItem().equals("PRICE")) {
					productList.sort(Product.CompareByPrice);

				} else if (select.getSelectedItem().equals("NAME")) {
					productList.sort(Product.CompareByName);
				} else if (select.getSelectedItem().equals("DESCRIPTION")) {
					productList.sort(Product.CompareByDescription);
				}
				for (Product items : productList) {
					sortProduct += items.toString() + "";

				}
				sortArea.setText(sortProduct);
				custOutput = createFile("sortedProducts.csv");

				for (Product items : productList) {
					createCustomers(items, custOutput);

				}
				custOutput.flush();
				custOutput.close();
			}

			private PrintWriter createFile(String filePath) {
				try {
					FileWriter productList = new FileWriter(filePath);
					BufferedWriter bw = new BufferedWriter(productList);
					PrintWriter infoToWrite = new PrintWriter(bw);
					return infoToWrite;
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
				return null;
			}

			private void createCustomers(Product items, PrintWriter custOutput) {

				String info = items.getName() + "," + items.getDescription() + ",";
				info += Integer.toString(items.getId()) + "," + Double.toString(items.getPrice()) + "\n";
				custOutput.println(info);

			}
		});

		// making panel for original
		originalPanel = new JPanel();
		originalPanel.setBorder(
				new TitledBorder(null, "Original", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		originalPanel.setBounds(10, 64, 590, 600);
		contentPane.add(originalPanel);
		originalPanel.setLayout(null);
		// Original Text Area
		originalArea = new JTextArea();
		originalArea.setBounds(10, 21, 590, 600);
		originalPanel.add(originalArea);
		// Panel for Sort
		sortPanel = new JPanel();
		sortPanel.setBorder(new TitledBorder(null, "Sorted", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		sortPanel.setBounds(640, 64, 600, 600);
		contentPane.add(sortPanel);
		sortPanel.setLayout(null);
		// Sort Text Area
		sortArea = new JTextArea();
		sortArea.setBounds(10, 22, 600, 600);
		sortPanel.add(sortArea);
		// this will prevent to edit or Modify
		originalArea.setEditable(false);
		sortArea.setEditable(false);

	} // end of GUI Constructor

	// method will read the original file
	private ArrayList<Product> readFile(File file) {
		productList = new ArrayList<>();
		Scanner scan = null;
		try {
			scan = new Scanner(file);
			String element = "";
			while (scan.hasNextLine()) {
				element = scan.nextLine();
				String[] elementsOfProduct = element.split(",");
				int id = Integer.parseInt(elementsOfProduct[0]);
				double price = Double.parseDouble(elementsOfProduct[1]);
				String name = elementsOfProduct[2];
				String des = elementsOfProduct[3];

				Product p = new Product(name, id, des, price);
				productList.add(p);
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return productList;
	}

	// method that will display the original File
	private void displayProducts(ArrayList<Product> product) {
		String str = "";
		for (Product items : product) {
			str += items.toString();
		}
		originalArea.setText(str);
	}
}
