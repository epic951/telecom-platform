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
@Table(name = "operator", uniqueConstraints = { @UniqueConstraint(columnNames = { "operatorId" }) })
public class Operator implements Serializable {

	private static final long serialVersionUID = 8006120004530718792L;
	private int operatorId;
	private String operatorName;
	private String operatorCountry;

	public Operator() {
	}

	public Operator(int operatorId, String operatorName, String operatorCountry) {
		super();
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.operatorCountry = operatorCountry;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operatorId_seq")
	@SequenceGenerator(name = "operatorId_seq", sequenceName = "operatorId_seq", allocationSize = 5)
	@Column(name = "operatorId", updatable = false, nullable = false)
	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "operatorName", nullable = false)
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	@Column(name = "operatorCountry")
	public String getOperatorCountry() {
		return operatorCountry;
	}

	public void setOperatorCountry(String operatorCountry) {
		this.operatorCountry = operatorCountry;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Operator [operatorId=" + operatorId + ", operatorName=" + operatorName + ", operatorCountry="
				+ operatorCountry + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((operatorCountry == null) ? 0 : operatorCountry.hashCode());
		result = prime * result + operatorId;
		result = prime * result + ((operatorName == null) ? 0 : operatorName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Operator))
			return false;
		Operator other = (Operator) obj;
		if (operatorCountry == null) {
			if (other.operatorCountry != null)
				return false;
		} else if (!operatorCountry.equals(other.operatorCountry))
			return false;
		if (operatorId != other.operatorId)
			return false;
		if (operatorName == null) {
			if (other.operatorName != null)
				return false;
		} else if (!operatorName.equals(other.operatorName))
			return false;
		return true;
	}
}
