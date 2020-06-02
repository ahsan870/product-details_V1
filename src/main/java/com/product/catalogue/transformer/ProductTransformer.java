package com.product.catalogue.transformer;

import org.springframework.stereotype.Component;

import com.product.catalogue.entity.ProductsEntity;
import com.product.catalogue.model.ProductRequest;

@Component
public class ProductTransformer {

	public ProductsEntity transformProductRequest(ProductRequest productRequest) {

		ProductsEntity productsEntity = new ProductsEntity();
		productsEntity.setProductName(productRequest.getProductName());
		productsEntity.setProductCode(productRequest.getProductCode());
		productsEntity.setDescriptions(productRequest.getDescriptions());
		productsEntity.setBrand(productRequest.getBrand());
		productsEntity.setModel(productRequest.getModel());
		productsEntity.setQuantity(productRequest.getQuantity());
		productsEntity.setPrice(productRequest.getPrice());
		return productsEntity;
	}

}
