package com.revature.service;

import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dto.SubmissionDTO;
import com.revature.model.Reimbursement;

public class ReimbursementService {

	private ReimbursementDAO reimbursementDao;
	
	public ReimbursementService() {
		this.reimbursementDao = new ReimbursementDAO();
	}
	
	public List<Reimbursement> getAllReimbursementsFromUserId(String userId){
		int id = Integer.parseInt(userId);
		
		List<Reimbursement> reimbursements = reimbursementDao.getAllReimbursementsFromUserId(id);
		
		return reimbursements;
	}
	
	public Reimbursement addSubmission(String userId, SubmissionDTO reimbursement) {
		int id = Integer.parseInt(userId);
		
		Reimbursement reimbToAdd = reimbursementDao.addSubmission(id, reimbursement);
		
		return reimbToAdd;
	}
}
