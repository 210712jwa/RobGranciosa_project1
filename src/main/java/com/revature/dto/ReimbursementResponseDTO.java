package com.revature.dto;

import java.sql.Timestamp;

import com.revature.model.User;
import com.revature.model.UserRole;

public class ReimbursementResponseDTO {

	//Here I might describe various properties that should be sent to the user as a response. 
	// This DTO prevents ALL information from being received (from a security standpoint). 
	// If the ship were approved by an admin, you would also see the information about the admin,
	// such as his username and password, on the frontend
	
	private double reimbAmount;
	// submitted, description, receipt, type, author
	private Timestamp timeSubmitted;
	private User author;
	private UserRole type;
	
}
