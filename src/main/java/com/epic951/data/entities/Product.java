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
@Table(name = "product", uniqueConstraints = { @UniqueConstraint(columnNames = { "productid" }) })
public class Product implements Serializable {

	private static final long serialVersionUID = -2830047822179023846L;
	private int productId;
	private String productName;
	private String productDescription;
	private int minPrice;
	private int maxPrice;

	public Product() {
	}

	public Product(int productId, String productName, String productDescription, int minPrice, int maxPrice) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	// @SequenceGenerator(name = "sequence_Generator", sequenceName =
	// "sequence_Generator", allocationSize = 1)
	// @ColumnDefault(value = "-1")

	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identifier")
	// @GeneratedValue(generator = "UUID")
	// @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productId_seq")
	@SequenceGenerator(name = "productId_seq", sequenceName = "productId_seq", allocationSize = 5)
	@Column(name = "productid", updatable = false, nullable = false)
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Column(name = "productname", nullable = false)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "productdescription")
	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	@Column(name = "minprice")
	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	@Column(name = "maxprice")
	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maxPrice;
		result = prime * result + minPrice;
		result = prime * result + ((productDescription == null) ? 0 : productDescription.hashCode());
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
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
		if (maxPrice != other.maxPrice)
			return false;
		if (minPrice != other.minPrice)
			return false;
		if (productDescription == null) {
			if (other.productDescription != null)
				return false;
		} else if (!productDescription.equals(other.productDescription))
			return false;
		if (productId != other.productId)
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		return true;
	}
}
