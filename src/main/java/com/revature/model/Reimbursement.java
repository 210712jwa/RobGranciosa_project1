package com.revature.model;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
// @Table(name = "ERS_reimbursement")
public class Reimbursement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimb_id")
	private int reimbId;
	
	@Column(name = "reimb_amount")
	private double reimbAmount;
	
	@Column(name = "reimb_submitted")
	private Timestamp timeSubmitted;

	@Column(name = "reimb_resolved")
	private Timestamp timeResolved;
	
	@Column(name = "reimb_description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private User author;
	
	@ManyToOne
	@JoinColumn(name = "resolver_id")
	private User resolver;
	
	@ManyToOne
	@JoinColumn(name = "reimb_status_id")
	private ReimbursementStatus status;
	
	@ManyToOne
	@JoinColumn(name = "reimb_type_id")
	private ReimbursementType type;

//	@Lob
//	@Column(name= "reimb_receipt", columnDefinition = "BLOB")
//	private byte[] receipt;

	public Reimbursement() {
		super();
	}

	public Reimbursement(double reimbAmount, Timestamp timeSubmitted, String description) {
		super();
		this.reimbAmount = reimbAmount;
		this.timeSubmitted = timeSubmitted;
		this.description = description;
	}
	
	public Reimbursement(double reimbAmount, String description) {
		super();
		this.reimbAmount = reimbAmount;
		this.description = description;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public Timestamp getTimeSubmitted() {
		return timeSubmitted;
	}

	public void setTimeSubmitted(Timestamp timeSubmitted) {
		this.timeSubmitted = timeSubmitted;
	}

	public Date getTimeResolved() {
		return timeResolved;
	}

	public void setTimeResolved(Timestamp timeResolved) {
		this.timeResolved = timeResolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getResolver() {
		return resolver;
	}

	public void setResolver(User resolver) {
		this.resolver = resolver;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, description, reimbAmount, reimbId, resolver, status, timeResolved, timeSubmitted,
				type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Objects.equals(author, other.author) && Objects.equals(description, other.description)
				&& Double.doubleToLongBits(reimbAmount) == Double.doubleToLongBits(other.reimbAmount)
				&& Objects.equals(reimbId, other.reimbId) && Objects.equals(resolver, other.resolver)
				&& Objects.equals(status, other.status) && Objects.equals(timeResolved, other.timeResolved)
				&& Objects.equals(timeSubmitted, other.timeSubmitted) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", timeSubmitted=" + timeSubmitted
				+ ", timeResolved=" + timeResolved + ", description=" + description + ", author=" + author
				+ ", resolver=" + resolver + ", status=" + status + ", type=" + type + "]";
	}
	
	

	
}
