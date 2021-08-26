package com.revature.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.dto.SubmissionDTO;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.util.SessionFactorySingleton;

public class ReimbursementDAO {

	public List<Reimbursement> getAllReimbursementsFromUserId(int id) {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Reimbursement> reimbursements = session.createQuery("SELECT r FROM Reimbursement r JOIN r.author u WHERE u.userId = :userid").setParameter("userid", id).getResultList();
		return reimbursements;
	}
	
	public Reimbursement addSubmission(int id, SubmissionDTO submissionDto) {
		
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		Date date = new Date();
		Timestamp submissionTime = new Timestamp(date.getTime());
		
		Reimbursement reimbursementToAdd = new Reimbursement();
		
		double reimbAmount = Double.parseDouble(submissionDto.getReimbAmount());
		int type = Integer.parseInt(submissionDto.getType());
		
		reimbursementToAdd.setResolver(null);
		reimbursementToAdd.setTimeResolved(null);
		reimbursementToAdd.setAuthor(session.get(User.class, id));
		reimbursementToAdd.setDescription(submissionDto.getReimbDescription());
		reimbursementToAdd.setReimbAmount(reimbAmount);
		reimbursementToAdd.setStatus(session.get(ReimbursementStatus.class, 1));
		reimbursementToAdd.setTimeSubmitted(submissionTime);
		reimbursementToAdd.setType(session.get(ReimbursementType.class, type));
		
		session.persist(reimbursementToAdd);
		tx.commit();
		session.close();
	
		return reimbursementToAdd;
		// session.persist(reimbursementToAdd);
		// tx.commit();
		
		
		// return reimbursementToAdd;
	}
}
