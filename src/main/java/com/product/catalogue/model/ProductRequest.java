package com.product.catalogue.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductRequest {

	@NotNull(message = "Please provide productCode")
	@NotEmpty(message = "Please provide valid productCode")
	private String productCode;

	@NotNull(message = "Please provide productName")
	@NotEmpty(message = "Please provide valid productName")
	private String productName;

	@NotNull(message = "Please provide descriptions")
	@NotEmpty(message = "Please provide valid descriptions")
	private String descriptions;

	@NotNull(message = "Please provide quantity")
	private int quantity;

	@NotNull(message = "Please provide price")
	private double price;
	
	@NotNull(message = "Please provide model")
	@NotEmpty(message = "Please provide valid model")
	private String model;

	@NotNull(message = "Please provide brand")
	@NotEmpty(message = "Please provide valid brand")
	private String brand;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
