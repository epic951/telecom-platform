package com.epic951.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "service", uniqueConstraints = { @UniqueConstraint(columnNames = { "service_id" }) })
public class Service implements Serializable {

	private static final long serialVersionUID = 5747837806045222851L;
	private int service_id;
	private String service_name;
	private boolean service_type;

	public Service() {
	}

	public Service(int service_id, String service_name, boolean service_type, int operator_id, int operator_service_id,
			int operator_package_id) {
		super();
		this.service_id = service_id;
		this.service_name = service_name;
		this.service_type = service_type;
		this.operator_id = operator_id;
		this.operator_service_id = operator_service_id;
		this.operator_package_id = operator_package_id;
	}

	private int operator_id;
	private int operator_service_id;
	private int operator_package_id;

	@Override
	public String toString() {
		return "Service [service_id=" + service_id + ", service_name=" + service_name + ", service_type=" + service_type
				+ ", operator_id=" + operator_id + ", operator_service_id=" + operator_service_id
				+ ", operator_package_id=" + operator_package_id + "]";
	}

	@Id
	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public boolean isService_type() {
		return service_type;
	}

	public void setService_type(boolean service_type) {
		this.service_type = service_type;
	}

	public int getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(int operator_id) {
		this.operator_id = operator_id;
	}

	public int getOperator_service_id() {
		return operator_service_id;
	}

	public void setOperator_service_id(int operator_service_id) {
		this.operator_service_id = operator_service_id;
	}

	public int getOperator_package_id() {
		return operator_package_id;
	}

	public void setOperator_package_id(int operator_package_id) {
		this.operator_package_id = operator_package_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
