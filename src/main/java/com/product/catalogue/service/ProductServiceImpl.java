package com.product.catalogue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.catalogue.entity.ProductsEntity;
import com.product.catalogue.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void saveProduct(ProductsEntity productsEntity) {
		productRepository.save(productsEntity);

	}

	@Override
	public Optional<ProductsEntity> getProductDetails(String productCode) {
		return productRepository.findByProductCode(productCode);
	}

	@Override
	public List<ProductsEntity> fetchAllProductDetails() {
		return productRepository.findAll();
	}

	@Override
	public void updateProduct(ProductsEntity productsEntity, String productCode) {
		Optional<ProductsEntity> findByProductCode = productRepository.findByProductCode(productCode);
		if (findByProductCode.isPresent()) {
			ProductsEntity entity = fetchProductsEntity(findByProductCode.get(), productsEntity, productCode);
			productRepository.save(entity);
		} else {
			ProductsEntity entity = fetchProductsEntity(new ProductsEntity(), productsEntity, productCode);
			productRepository.save(entity);
		}

	}

	@Override
	public void deleteProductByProductCode(String productCode) {
		productRepository.deleteByProductCode(productCode);

	}

	private ProductsEntity fetchProductsEntity(ProductsEntity entity, ProductsEntity productsEntity,
			String productCode) {
		entity.setBrand(productsEntity.getBrand());
		entity.setDescriptions(productsEntity.getDescriptions());
		entity.setModel(productsEntity.getModel());
		entity.setPrice(productsEntity.getPrice());
		entity.setProductName(productsEntity.getProductName());
		entity.setQuantity(productsEntity.getQuantity());
		entity.setProductName(productsEntity.getProductName());
		entity.setProductCode(productCode);
		return entity;

	}

	@Override
	public boolean isProductExists(String productCode) {
		return productRepository.countByProductCode(productCode) > 0;
	}

	@Override
	public boolean isQuantityAvailable(String productCode, int quantityOrdered) {

		Optional<ProductsEntity> findByProductCode = productRepository.findByProductCode(productCode);
		if (findByProductCode.isPresent()) {
			return findByProductCode.get().getQuantity() >= quantityOrdered;
		}
		return false;

	}

	@Override
	public void updateQuantity(String productCode, int quantityOrdered) {
		Optional<ProductsEntity> findByProductCode = productRepository.findByProductCode(productCode);
		if (findByProductCode.isPresent()) {
			int quantity = findByProductCode.get().getQuantity() - quantityOrdered;
			productRepository.updateQuantity(productCode, quantity);
		}

	}

}
