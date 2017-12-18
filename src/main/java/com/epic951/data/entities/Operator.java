package com.epic951.data.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "operator", uniqueConstraints = { @UniqueConstraint(columnNames = { "operator_id" }) })
public class Operator implements Serializable {

	private static final long serialVersionUID = 8006120004530718792L;
	private int operator_id;
	private String operator_name;
	private String operator_country;

	public Operator() {
	}

	public Operator(int operator_id, String operator_name, String operator_country) {
		super();
		this.operator_id = operator_id;
		this.operator_name = operator_name;
		this.operator_country = operator_country;
	}

	@Override
	public String toString() {
		return "Operator [operator_id=" + operator_id + ", operator_name=" + operator_name + ", operator_country="
				+ operator_country + "]";
	}

	@Id
	public int getOperator_id() {
		return operator_id;
	}

	public void setOperator_id(int operator_id) {
		this.operator_id = operator_id;
	}

	public String getOperator_name() {
		return operator_name;
	}

	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}

	public String getOperator_country() {
		return operator_country;
	}

	public void setOperator_country(String operator_country) {
		this.operator_country = operator_country;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
