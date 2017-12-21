package com.epic951.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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

	// @SequenceGenerator(name = "sequence_Generator", sequenceName =
	// "sequence_Generator", allocationSize = 1)
	// @ColumnDefault(value = "-1")

	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
	// @GeneratedValue(generator = "UUID")
	// @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
	@SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 5)
	@Column(name = "product_id", updatable = false, nullable = false)
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	@Column(name = "product_name", nullable = false)
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	@Column(name = "product_description")
	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	@Column(name = "min_price")
	public int getMin_price() {
		return min_price;
	}

	public void setMin_price(int min_price) {
		this.min_price = min_price;
	}

	@Column(name = "max_price")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + max_price;
		result = prime * result + min_price;
		result = prime * result + ((product_description == null) ? 0 : product_description.hashCode());
		result = prime * result + product_id;
		result = prime * result + ((product_name == null) ? 0 : product_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Product))
			return false;
		Product other = (Product) obj;
		if (max_price != other.max_price)
			return false;
		if (min_price != other.min_price)
			return false;
		if (product_description == null) {
			if (other.product_description != null)
				return false;
		} else if (!product_description.equals(other.product_description))
			return false;
		if (product_id != other.product_id)
			return false;
		if (product_name == null) {
			if (other.product_name != null)
				return false;
		} else if (!product_name.equals(other.product_name))
			return false;
		return true;
	}
}
