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
@Table(name = "telecomservice", uniqueConstraints = { @UniqueConstraint(columnNames = { "service_id" }) })
public class TelecomService implements Serializable {

	private static final long serialVersionUID = 5747837806045222851L;
	private int telecomservice_id;
	private String telecomservice_name;
	private boolean telecomservice_type;

	private int operator_id;
	private int operator_service_id;
	private int operator_package_id;

	public TelecomService() {
	}

	public TelecomService(int service_id, String service_name, boolean service_type, int operator_id, int operator_service_id,
			int operator_package_id) {
		super();
		this.telecomservice_id = service_id;
		this.telecomservice_name = service_name;
		this.telecomservice_type = service_type;
		this.operator_id = operator_id;
		this.operator_service_id = operator_service_id;
		this.operator_package_id = operator_package_id;
	}

	@Id
	@SequenceGenerator(name = "service_id_seq", sequenceName = "service_id_seq", allocationSize = 5)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_id_seq")
	@Column(name = "service_id", updatable = false, nullable = false)
	public int getTelecomService_id() {
		return telecomservice_id;
	}

	public void setTelecomService_id(int service_id) {
		this.telecomservice_id = service_id;
	}

	@Column(name = "service_name", nullable = false)
	public String getTelecomService_name() {
		return telecomservice_name;
	}

	public void setTelecomService_name(String service_name) {
		this.telecomservice_name = service_name;
	}

	@Column(name = "service_type")
	public boolean isTelecomService_type() {
		return telecomservice_type;
	}

	public void setTelecomService_type(boolean service_type) {
		this.telecomservice_type = service_type;
	}

	@Column(name = "operator_id")
	public int getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(int operator_id) {
		this.operator_id = operator_id;
	}

	@Column(name = "operator_service_id")
	public int getOperator_service_id() {
		return operator_service_id;
	}

	public void setOperator_service_id(int operator_service_id) {
		this.operator_service_id = operator_service_id;
	}

	@Column(name = "operator_package_id")
	public int getOperator_package_id() {
		return operator_package_id;
	}

	public void setOperator_package_id(int operator_package_id) {
		this.operator_package_id = operator_package_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + operator_id;
		result = prime * result + operator_package_id;
		result = prime * result + operator_service_id;
		result = prime * result + telecomservice_id;
		result = prime * result + ((telecomservice_name == null) ? 0 : telecomservice_name.hashCode());
		result = prime * result + (telecomservice_type ? 1231 : 1237);
		return result;
	}

	@Override
	public String toString() {
		return "Service [service_id=" + telecomservice_id + ", service_name=" + telecomservice_name + ", service_type=" + telecomservice_type
				+ ", operator_id=" + operator_id + ", operator_service_id=" + operator_service_id
				+ ", operator_package_id=" + operator_package_id + "]";
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
		if (operator_id != other.operator_id)
			return false;
		if (operator_package_id != other.operator_package_id)
			return false;
		if (operator_service_id != other.operator_service_id)
			return false;
		if (telecomservice_id != other.telecomservice_id)
			return false;
		if (telecomservice_name == null) {
			if (other.telecomservice_name != null)
				return false;
		} else if (!telecomservice_name.equals(other.telecomservice_name))
			return false;
		if (telecomservice_type != other.telecomservice_type)
			return false;
		return true;
	}
}
