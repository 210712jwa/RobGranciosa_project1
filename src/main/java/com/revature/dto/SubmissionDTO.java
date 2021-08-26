package com.revature.dto;

import java.util.Objects;

public class SubmissionDTO {

	private String reimbAmount;
	private String type;
	private String reimbDescription;
	
	public SubmissionDTO() {
		super();
	}

	public SubmissionDTO(String reimbAmount, String type, String reimbDescription) {
		super();
		this.reimbAmount = reimbAmount;
		this.type = type;
		this.reimbDescription = reimbDescription;
	}

	public String getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(String reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}



	@Override
	public int hashCode() {
		return Objects.hash(reimbAmount, reimbDescription, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubmissionDTO other = (SubmissionDTO) obj;
		return Objects.equals(reimbAmount, other.reimbAmount)
				&& Objects.equals(reimbDescription, other.reimbDescription) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "SubmissionDTO [reimbAmount=" + reimbAmount + ", type=" + type + ", reimbDescription=" + reimbDescription
				+ "]";
	}
	
	
}
