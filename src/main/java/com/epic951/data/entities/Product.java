package com.epic951.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "product", uniqueConstraints = { @UniqueConstraint(columnNames = { "product_id" }) })
public class Product implements Serializable {

	private static final long serialVersionUID = -2830047822179023846L;
	private int product_id;
	private String product_name;
	private String product_description;
	private int min_price;
	private int max_price;

	public Product() {
	}

	public Product(int product_id, String product_name, String product_description, int min_price, int max_price) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_description = product_description;
		this.min_price = min_price;
		this.max_price = max_price;
	}

	@Id
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public int getMin_price() {
		return min_price;
	}

	public void setMin_price(int min_price) {
		this.min_price = min_price;
	}

	public int getMax_price() {
		return max_price;
	}

	public void setMax_price(int max_price) {
		this.max_price = max_price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_description="
				+ product_description + ", min_price=" + min_price + ", max_price=" + max_price + "]";
	}
}
