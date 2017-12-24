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
@Table(name = "operator", uniqueConstraints = { @UniqueConstraint(columnNames = { "operatorid" }) })
public class Operator implements Serializable {

	private static final long serialVersionUID = 8006120004530718792L;

	@NotNull
	@Min(21)
	@Max(99)
	private int operatorId;

	@NotNull
	@Size(min = 2, max = 15)
	private String operatorName;

	@Size(min = 2, max = 15)
	private String operatorCountry;

	@OneToMany(mappedBy = "operator")
	private List<TelecomService> telecomServices;

	public Operator() {
	}

	public Operator(int operatorId, String operatorName, String operatorCountry) {
		super();
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.operatorCountry = operatorCountry;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operatoridseq")
	@SequenceGenerator(name = "operatoridseq", sequenceName = "operatoridseq", allocationSize = 15)
	@Column(name = "operatorid", updatable = false, nullable = false)
	public int getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(int operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "operatorname", nullable = false)
	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	@Column(name = "operatorcountry")
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
