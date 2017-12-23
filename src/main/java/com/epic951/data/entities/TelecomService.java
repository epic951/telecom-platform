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
	private int telecomServiceId;
	private String telecomServiceName;
	private boolean telecomServiceType;

	private int operatorId;
	private String operatorName;
	private int operatorServiceId;
	private int operatorPackageId;

	public TelecomService() {
	}

	public TelecomService(int telecomserviceId, String telecomserviceName, boolean telecomserviceType, int operatorId,
			String operatorName, int operatorServiceId, int operatorPackageId) {
		super();
		this.telecomServiceId = telecomserviceId;
		this.telecomServiceName = telecomserviceName;
		this.telecomServiceType = telecomserviceType;
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.operatorServiceId = operatorServiceId;
		this.operatorPackageId = operatorPackageId;
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
	
	@Column(name="operatorname")
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TelecomService [telecomServiceId=" + telecomServiceId + ", telecomServiceName=" + telecomServiceName
				+ ", telecomServiceType=" + telecomServiceType + ", operatorId=" + operatorId + ", operatorName="
				+ operatorName + ", operatorServiceId=" + operatorServiceId + ", operatorPackageId=" + operatorPackageId
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + operatorId;
		result = prime * result + ((operatorName == null) ? 0 : operatorName.hashCode());
		result = prime * result + operatorPackageId;
		result = prime * result + operatorServiceId;
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
