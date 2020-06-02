package com.product.catalogue.service;

import java.util.List;
import java.util.Optional;

import com.product.catalogue.entity.ProductsEntity;

public interface ProductService {

	public void saveProduct(ProductsEntity productsEntity);

	public Optional<ProductsEntity> getProductDetails(String productCode);

	public List<ProductsEntity> fetchAllProductDetails();

	public void updateProduct(ProductsEntity productsEntity, String productCode);

	public void deleteProductByProductCode(String productCode);

	public boolean isProductExists(String productCode);

	public boolean isQuantityAvailable(String productCode, int quantityOrdered);

	public void updateQuantity(String productCode, int quantityOrdered);

}
