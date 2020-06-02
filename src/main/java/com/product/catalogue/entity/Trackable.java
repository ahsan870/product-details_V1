package com.product.catalogue.entity;

import java.sql.Timestamp;

public interface Trackable {

	Timestamp getCreatedDate();

	void setCreatedDate(Timestamp createdDate);

	Timestamp getModifiedDate();

	void setModifiedDate(Timestamp updatedDate);

}