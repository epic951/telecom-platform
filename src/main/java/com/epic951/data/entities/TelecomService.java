package com.epic951.data.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "telecomservice", uniqueConstraints = { @UniqueConstraint(columnNames = { "telecomserviceid" }) })
public class TelecomService implements Serializable {

	private static final long serialVersionUID = 5747837806045222851L;

	@NotNull
	@Min(1)
	@Max(9999)
	private int telecomServiceId;

	@NotNull
	@Size(min = 3, max = 30)
	private String telecomServiceName;

	private boolean telecomServiceType;

	@Min(1)
	@Max(9999)
	private int operatorId;

	@NotNull
	@Size(min = 3, max = 30)
	private String operatorName;

	@Min(0)
	@Max(9999)
	private int operatorServiceId;

	@Min(0)
	@Max(9999)
	private int operatorPackageId;

	private String imageUrl;

	@Min(1)
	@Max(5)
	private float rating;

	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;

	public TelecomService() {
	}

	public TelecomService(int telecomserviceId, String telecomserviceName, boolean telecomserviceType, int operatorId,
			String operatorName, int operatorServiceId, int operatorPackageId, String imageUrl, float rating) {
		super();
		this.telecomServiceId = telecomserviceId;
		this.telecomServiceName = telecomserviceName;
		this.telecomServiceType = telecomserviceType;
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.operatorServiceId = operatorServiceId;
		this.operatorPackageId = operatorPackageId;
		this.imageUrl = imageUrl;
		this.rating = rating;
	}

	@Id
	@SequenceGenerator(name = "serviceidseq", sequenceName = "serviceidseq", allocationSize = 25)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serviceidseq")
	@Column(name = "telecomserviceid", updatable = false, nullable = false)
	public int getTelecomServiceId() {
		return telecomServiceId;
	}

	public void setTelecomServiceId(int serviceId) {
		this.telecomServiceId = serviceId;
	}

	@Column(name = "telecomservicename", nullable = false)
	public String getTelecomServiceName() {
		return telecomServiceName;
	}

	public void setTelecomServiceName(String serviceName) {
		this.telecomServiceName = serviceName;
	}

	@Column(name = "telecomservicetype")
	public boolean isTelecomServiceType() {
		return telecomServiceType;
	}

	public void setTelecomServiceType(boolean serviceType) {
		this.telecomServiceType = serviceType;
	}

	@Column(name = "operatorid")
	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "operatorserviceid")
	public int getOperatorServiceId() {
		return operatorServiceId;
	}

	public void setOperatorServiceId(int operatorServiceId) {
		this.operatorServiceId = operatorServiceId;
	}

	@Column(name = "operatorname")
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	@Column(name = "operatorpackageid")
	public int getOperatorPackageId() {
		return operatorPackageId;
	}

	public void setOperatorPackageId(int operatorPackageId) {
		this.operatorPackageId = operatorPackageId;
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
		return "TelecomService [telecomServiceId=" + telecomServiceId + ", telecomServiceName=" + telecomServiceName
				+ ", telecomServiceType=" + telecomServiceType + ", operatorId=" + operatorId + ", operatorName="
				+ operatorName + ", operatorServiceId=" + operatorServiceId + ", operatorPackageId=" + operatorPackageId
				+ ", imageUrl=" + imageUrl + ", product=" + product + ", rating=" + rating + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + operatorId;
		result = prime * result + ((operatorName == null) ? 0 : operatorName.hashCode());
		result = prime * result + operatorPackageId;
		result = prime * result + operatorServiceId;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + Float.floatToIntBits(rating);
		result = prime * result + telecomServiceId;
		result = prime * result + ((telecomServiceName == null) ? 0 : telecomServiceName.hashCode());
		result = prime * result + (telecomServiceType ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TelecomService))
			return false;
		TelecomService other = (TelecomService) obj;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (operatorId != other.operatorId)
			return false;
		if (operatorName == null) {
			if (other.operatorName != null)
				return false;
		} else if (!operatorName.equals(other.operatorName))
			return false;
		if (operatorPackageId != other.operatorPackageId)
			return false;
		if (operatorServiceId != other.operatorServiceId)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (Float.floatToIntBits(rating) != Float.floatToIntBits(other.rating))
			return false;
		if (telecomServiceId != other.telecomServiceId)
			return false;
		if (telecomServiceName == null) {
			if (other.telecomServiceName != null)
				return false;
		} else if (!telecomServiceName.equals(other.telecomServiceName))
			return false;
		if (telecomServiceType != other.telecomServiceType)
			return false;
		return true;
	}

}
