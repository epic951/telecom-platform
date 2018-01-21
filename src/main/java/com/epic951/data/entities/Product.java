package com.epic951.data.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product", uniqueConstraints = { @UniqueConstraint(columnNames = { "productid" }) })
public class Product implements Serializable {

	private static final long serialVersionUID = -2830047822179023846L;

	@NotNull
	@Min(1)
	@Max(9999)
	private int productId;

	@NotNull
	@Size(min = 3, max = 30)
	private String productName;

	@Size(min = 2, max = 30)
	private String productDescription;

	@Min(1)
	@Max(9999)
	private int minPrice;

	@Min(1)
	@Max(9999)
	private int maxPrice;

	private String imageUrl;
	
	@Min(1)
	@Max(5)
	private float rating;

	@OneToMany(mappedBy = "product")
	private List<TelecomService> telecomServices;

	public Product() {
	}

	public Product(int productId, String productName, String productDescription, int minPrice, int maxPrice,
			String imageUrl, float rating) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.imageUrl = imageUrl;
		this.rating = rating;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productidseq")
	@SequenceGenerator(name = "productidseq", sequenceName = "productidseq", allocationSize = 5)
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

	@Column(name = "imageurl")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "rating")
	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productDescription="
				+ productDescription + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", imageUrl=" + imageUrl
				+ ", rating=" + rating + ", telecomServices=" + telecomServices + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + maxPrice;
		result = prime * result + minPrice;
		result = prime * result + ((productDescription == null) ? 0 : productDescription.hashCode());
		result = prime * result + productId;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + Float.floatToIntBits(rating);
		result = prime * result + ((telecomServices == null) ? 0 : telecomServices.hashCode());
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
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
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
		if (Float.floatToIntBits(rating) != Float.floatToIntBits(other.rating))
			return false;
		if (telecomServices == null) {
			if (other.telecomServices != null)
				return false;
		} else if (!telecomServices.equals(other.telecomServices))
			return false;
		return true;
	}

}
