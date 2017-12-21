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
@Table(name = "telecomservice", uniqueConstraints = { @UniqueConstraint(columnNames = { "telecomserviceid" }) })
public class TelecomService implements Serializable {

	private static final long serialVersionUID = 5747837806045222851L;
	private int telecomserviceId;
	private String telecomserviceName;
	private boolean telecomserviceType;

	private int operatorId;
	private int operatorServiceId;
	private int operatorPackageId;

	public TelecomService() {
	}

	public TelecomService(int telecomserviceId, String telecomserviceName, boolean telecomserviceType, int operatorId,
			int operatorServiceId, int operatorPackageId) {
		super();
		this.telecomserviceId = telecomserviceId;
		this.telecomserviceName = telecomserviceName;
		this.telecomserviceType = telecomserviceType;
		this.operatorId = operatorId;
		this.operatorServiceId = operatorServiceId;
		this.operatorPackageId = operatorPackageId;
	}

	@Id
	@SequenceGenerator(name = "serviceidseq", sequenceName = "serviceidseq", allocationSize = 5)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serviceidseq")
	@Column(name = "telecomserviceid", updatable = false, nullable = false)
	public int getTelecomServiceId() {
		return telecomserviceId;
	}

	public void setTelecomServiceId(int serviceId) {
		this.telecomserviceId = serviceId;
	}

	@Column(name = "telecomservicename", nullable = false)
	public String getTelecomServiceName() {
		return telecomserviceName;
	}

	public void setTelecomServiceName(String serviceName) {
		this.telecomserviceName = serviceName;
	}

	@Column(name = "telecomservicetype")
	public boolean isTelecomServiceType() {
		return telecomserviceType;
	}

	public void setTelecomServiceType(boolean serviceType) {
		this.telecomserviceType = serviceType;
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

	@Column(name = "operatorpackageid")
	public int getOperatorPackageId() {
		return operatorPackageId;
	}

	public void setOperatorPackageId(int operatorPackageId) {
		this.operatorPackageId = operatorPackageId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + operatorId;
		result = prime * result + operatorPackageId;
		result = prime * result + operatorServiceId;
		result = prime * result + telecomserviceId;
		result = prime * result + ((telecomserviceName == null) ? 0 : telecomserviceName.hashCode());
		result = prime * result + (telecomserviceType ? 1231 : 1237);
		return result;
	}

	@Override
	public String toString() {
		return "Service [serviceId=" + telecomserviceId + ", serviceName=" + telecomserviceName + ", serviceType="
				+ telecomserviceType + ", operatorId=" + operatorId + ", operatorServiceId=" + operatorServiceId
				+ ", operatorPackageId=" + operatorPackageId + "]";
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
		if (operatorId != other.operatorId)
			return false;
		if (operatorPackageId != other.operatorPackageId)
			return false;
		if (operatorServiceId != other.operatorServiceId)
			return false;
		if (telecomserviceId != other.telecomserviceId)
			return false;
		if (telecomserviceName == null) {
			if (other.telecomserviceName != null)
				return false;
		} else if (!telecomserviceName.equals(other.telecomserviceName))
			return false;
		if (telecomserviceType != other.telecomserviceType)
			return false;
		return true;
	}
}
