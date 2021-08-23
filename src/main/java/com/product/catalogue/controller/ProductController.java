package com.product.catalogue.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.product.catalogue.entity.ProductsEntity;
import com.product.catalogue.model.ProductRequest;
import com.product.catalogue.model.ProductResponse;
import com.product.catalogue.service.ProductService;
import com.product.catalogue.transformer.ProductTransformer;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/products")
@Api(value = "/products")
@CrossOrigin(origins="http://localhost:4200")
public class ProductController {

	private static final String MESSAGE = "Product requested With ";

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductTransformer productTransformer;
	
	private byte[] bytes;

	@PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest) {
		//productRequest.setPicByte(this.bytes);
		productService.saveProduct(productTransformer.transformProductRequest(productRequest));
		this.bytes = null;
		ProductResponse response = new ProductResponse(
				MESSAGE + productRequest.getProductName() + " added Successfully");
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping(path = "get/{productCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductsEntity> getProduct(
			@PathVariable(name = "productCode", required = true) final String productCode) {

		Optional<ProductsEntity> productDetails = productService.getProductDetails(productCode);
		if (productDetails.isPresent()) {
			return new ResponseEntity<>(productDetails.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<ProductsEntity>> fetchAllProduct() {
		return new ResponseEntity<>(productService.fetchAllProductDetails(), HttpStatus.OK);

	}

	@DeleteMapping(path = "/delete/{productCode}")
	public ResponseEntity<ProductResponse> deleteProduct(@PathVariable(name = "productCode") final String productCode) {

		productService.deleteProductByProductCode(productCode);
		ProductResponse response = new ProductResponse(MESSAGE + productCode + " Delete Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/update/{productCode}")
	public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody ProductRequest productRequest,
			@PathVariable(name = "productCode") final String productCode) {

		productService.updateProduct(productTransformer.transformProductRequest(productRequest), productCode);
		ProductResponse response = new ProductResponse(MESSAGE + productCode + " updated Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/check/{productCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isProductExist(
			@PathVariable(value = "productCode", required = true) final String productCode) {

		boolean productExists = productService.isProductExists(productCode);
		return new ResponseEntity<>(productExists, HttpStatus.OK);

	}

	@GetMapping(value = "/checks/{productCode}/{quantityOrdered}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> isQuantityAvailable(
			@PathVariable(value = "productCode", required = true) final String productCode,
			@PathVariable(value = "quantityOrdered", required = true) final String quantityOrdered) {

		boolean quantityAvailable = productService.isQuantityAvailable(productCode, Integer.valueOf(quantityOrdered));
		return new ResponseEntity<>(quantityAvailable, HttpStatus.OK);

	}

	@PutMapping("/update/quantity/{productCode}/{quantityOrdered}")
	public ResponseEntity<ProductResponse> updateQuantity(@PathVariable(name = "productCode") final String productCode,
			@PathVariable(value = "quantityOrdered", required = true) final String quantityOrdered) {

		productService.updateQuantity(productCode, Integer.valueOf(quantityOrdered));
		ProductResponse response = new ProductResponse(MESSAGE + productCode + " updated Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	 
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.bytes = file.getBytes();
	}
}
