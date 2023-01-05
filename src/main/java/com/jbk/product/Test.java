package com.jbk.product;

import java.util.List;
import java.util.Scanner;

import com.jbk.product.entity.Product;
import com.jbk.product.service.ProductService;
import com.jbk.product.utility.ProductUtility;

public class Test {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int choice;
		char ch;
		int productId;
		ProductService service = new ProductService();
		System.out.println("*****In Controller*****");
		do {
			System.out.println("Press 1: Save Product\nPress 2: Get Product ");
			System.out.println("Press 3: Delete Product\nPress 4: Update Product");
			System.out.println("Press 5: GetAll Product(Criteria)\nPress 6: RestrictionEx");

			choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				Product product = ProductUtility.getProduct(scanner);
				String msg = service.saveProduct(product);
				System.out.println(msg);
				break;
			}

			case 2: {
				System.out.println("Enter product Id");
				productId = scanner.nextInt();
				Product product = service.getProduct(productId);
				System.out.println(product);
				break;
			}
			case 3: {
				System.out.println("Enter product Id");
				productId = scanner.nextInt();
				String msg = service.deleteProduct(productId);
				System.out.println(msg);

				break;
			}

			case 4: {
				System.out.println("Enter product Id");
				productId = scanner.nextInt();
				Product product = service.getProduct(productId);
				System.out.println(product);
				System.out.println("enter new product name");
				String name = scanner.next();
				String msg = service.updateProduct(productId, name);
				System.out.println(msg);

				break;
			}
			case 5: {
				System.out.println("1.By Ascending on ProductID 2.By Descending on ProductID");
				System.out.println("3.By Ascending on Productprice 4.By Descending on Productprice");

				int key = scanner.nextInt();
				if (key <= 4) {
					List<Product> list = service.getAllProduct(key);
					if (list != null) {
						list.forEach(product -> System.out.println(product));
					} else {
						System.out.println("No records founds");
					}
				} else {
					System.out.println("enter valid key");
				}

			}

			case 6: {

				break;
			}

			default: {
				System.out.println("Invalid Input");
				break;
			}

			}

			System.out.println("Do you want to continue Y/N");
			ch = scanner.next().charAt(0);

		} while (ch == 'y' || ch == 'Y');
		System.out.println("Application terminated");
		System.exit(0);

	}

}
