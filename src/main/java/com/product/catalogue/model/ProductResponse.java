package com.product.catalogue.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductResponse {

	private String message = "Success";

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private Date timestamp = new Date();

	public ProductResponse() {
	}

	public ProductResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
