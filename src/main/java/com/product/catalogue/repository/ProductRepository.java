package com.product.catalogue.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.product.catalogue.entity.ProductsEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductsEntity, Long> {

	@Query(value = "SELECT c FROM ProductsEntity c WHERE c.productCode = :productCode")
	@Transactional
	Optional<ProductsEntity> findByProductCode(@Param("productCode") String productCode);

	@Query(value = "Delete FROM ProductsEntity c WHERE c.productCode = :productCode")
	@Modifying
	@Transactional
	void deleteByProductCode(@Param("productCode") String productCode);

	public int countByProductCode(String productCode);

	@Query(value = "Update ProductsEntity c set c.quantity=:quantity WHERE c.productCode = :productCode")
	@Modifying
	@Transactional
	void updateQuantity(@Param("productCode") String productCode, @Param("quantity") int quantity);
}
