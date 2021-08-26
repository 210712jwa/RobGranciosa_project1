package com.revature.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.revature.dto.MessageDTO;
import com.revature.dto.SubmissionDTO;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.model.Reimbursement;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class ReimbursementController implements Controller {

	private ReimbursementService rs;
	
	public ReimbursementController() {
		this.rs = new ReimbursementService();
	}
	
	private Handler getReimbsBelogingToSpecificUser = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		
		if (session.getAttribute("currentUser") == null) {
			ctx.json(new MessageDTO("You need to be logged in to perform this action"));
		} else {
			User currentUser = (User) session.getAttribute("currentUser");
			
			String userId = ctx.pathParam("userid");
			
			if (currentUser.getUserId() == Integer.parseInt(userId) ) {
				List<Reimbursement> reimbursements = rs.getAllReimbursementsFromUserId(userId);
				
				ctx.json(reimbursements);
				ctx.status(200);
			} else {
				ctx.json(new MessageDTO("You are not authorized to retrieve reimbursement information"));
				ctx.status(401);
			}
			
		}
	};
	
	// Method allows user to create a reimbursement request. 
	private Handler submissionHandler = (ctx) -> {
		HttpSession session = ctx.req.getSession();
		
		if (session.getAttribute("currentUser") == null) {
			ctx.json(new MessageDTO("Please sign in to submit a reimbursement request"));
			ctx.status(401);
		}
		else {
			User currentUser = (User) session.getAttribute("currentUser");
			SubmissionDTO submissionDto = ctx.bodyAsClass(SubmissionDTO.class);
			
			String userId = ctx.pathParam("userid");
			
			if (currentUser.getUserId() == Integer.parseInt(userId)) {
				Reimbursement reimbToAdd = rs.addSubmission(userId, submissionDto);
				
				ctx.json(reimbToAdd);
				ctx.status(200);
				ctx.json(new MessageDTO("Submission successful"));
			}
			else {
				ctx.json(new MessageDTO("You are not authorized to submit a reimbursement request."));
				ctx.status(401);
			}
		}
	};
	
	@Override
	public void mapEndpoints(Javalin app) {
		app.get("/user/:userid/reimbursement", getReimbsBelogingToSpecificUser);
		app.post("/user/:userid/reimbursement", submissionHandler);
	}

}
