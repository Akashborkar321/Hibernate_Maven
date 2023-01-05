package com.jbk.product.service;

import java.util.List;

import com.jbk.product.dao.ProductDao;
import com.jbk.product.entity.Product;
import com.jbk.product.validation.ProductValidate;

public class ProductService {
	private ProductDao productDao = new ProductDao();

	public String saveProduct(Product product) {
		boolean flag = ProductValidate.validateProduct(product);
		if (flag) {
			String msg = productDao.saveProduct(product);
			return msg;
		} else {
			return "please enter valid data";
		}

	}

	public Product getProduct(int productId) {
		Product product = productDao.getProduct(productId);
		return product;

	}

	public String deleteProduct(int productId) {
		Product p = getProduct(productId);
		if (p != null) {
			String msg = productDao.deleteProduct(productId);

			return msg;

		} else {
			return "no record exist for product id " + productId;
		}

	}

	public String updateProduct(int productId, String name) {

		String msg = productDao.updateProduct(productId, name);

		return msg;

	}

	public List<Product> getAllProduct(int key) {
		List<Product> list = productDao.getAllProduct(key);
		return list;

	}

}
