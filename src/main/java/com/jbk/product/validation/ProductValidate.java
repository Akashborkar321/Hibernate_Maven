package com.jbk.product.validation;

import com.jbk.product.entity.Product;

public class ProductValidate {

	public static boolean validateProduct(Product product) {
		boolean flag = false;
		if ((product.getProductId() > 0 && product.getProductPrice() > 0) && (product.getProductPrice() > 0)) {
			flag = true;
		}

		return flag;

	}

}
