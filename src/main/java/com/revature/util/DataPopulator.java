package com.revature.util;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.model.UserRole;

public class DataPopulator {

	public static void main(String[] args) {
		
		populateReimbStatus();
		populateReimbType();
		populateUserRole();
		addSampleUsers();
		addSampleReimbursement();
	}

	private static void populateReimbStatus() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		ReimbursementStatus pending = new ReimbursementStatus("pending");
		ReimbursementStatus approved = new ReimbursementStatus("approved");
		ReimbursementStatus denied = new ReimbursementStatus("denied");
		session.persist(pending);
		session.persist(approved);
		session.persist(denied);
				
		tx.commit();
		session.close();
	}
	
	private static void populateReimbType() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		ReimbursementType lodging = new ReimbursementType("lodging");
		ReimbursementType travel = new ReimbursementType("travel");
		ReimbursementType food = new ReimbursementType("food");
		ReimbursementType other = new ReimbursementType("other");
		
		session.persist(lodging);
		session.persist(travel);
		session.persist(food);
		session.persist(other);
		
		tx.commit();
		session.close();
	}
	
	private static void populateUserRole() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		UserRole employee = new UserRole("employee");
		UserRole financeManager = new UserRole("finance_manager");
		
		session.persist(employee);
		session.persist(financeManager);
		
		tx.commit();
		session.close();
	}
	
	private static void addSampleUsers() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		User FMUser1 = new User("robgranciosa", "12345", "Rob", "Granciosa", "rgranciosa@outlook.com");
		UserRole financeManager = (UserRole) session.createQuery("FROM UserRole ur WHERE ur.role = 'finance_manager'").getSingleResult();
		FMUser1.setUserRole(financeManager);
		
		session.persist(FMUser1);
		
		UserRole employee = (UserRole) session.createQuery("FROM UserRole ur WHERE ur.role = 'employee'").getSingleResult();
		
		User employee1 = new User("lpelekai", "ohana", "Lilo", "Pelekai", "elvisfan808@yahoo.com");
		employee1.setUserRole(employee);
		
		User employee2 = new User("ebennet", "prideandprejudice", "Elizabeth", "Bennet", "msbennet@gmail.com");
		employee2.setUserRole(employee);
		
		session.persist(employee1);
		session.persist(employee2);
		
		tx.commit();
		session.close();
	}
	
	private static void addSampleReimbursement() {
		SessionFactory sf = SessionFactorySingleton.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		// Timestamp instant = Timestamp.from(Instant.now());
		
		long instant = System.currentTimeMillis();
		Timestamp ts = new Timestamp(instant);
		
		
		// add foreign keys: Author (User), Resolver (User), ReimbursementStatus, ReimbursementType
		
		ReimbursementType lodging = (ReimbursementType) session.createQuery("FROM ReimbursementType rt WHERE rt.type = 'lodging'").getSingleResult();
		ReimbursementType travel = (ReimbursementType) session.createQuery("FROM ReimbursementType rt WHERE rt.type = 'travel'").getSingleResult();
		ReimbursementType food = (ReimbursementType) session.createQuery("FROM ReimbursementType rt WHERE rt.type = 'food'").getSingleResult();
		ReimbursementType other = (ReimbursementType) session.createQuery("FROM ReimbursementType rt WHERE rt.type = 'other'").getSingleResult();
		
		User lpelekai = (User) session.createQuery("FROM User u WHERE u.username = 'lpelekai'").getSingleResult();
		User ebennet = (User) session.createQuery("FROM User u WHERE u.username = 'ebennet'").getSingleResult();
		
		ReimbursementStatus pending = (ReimbursementStatus) session.createQuery("FROM ReimbursementStatus rs WHERE rs.status = 'pending'").getSingleResult();

		Reimbursement lodging1 = new Reimbursement(10.0, ts, "Holiday Inn 2021 United Galactic Federation Conference");
		
		lodging1.setAuthor(lpelekai);
		lodging1.setStatus(pending);
		lodging1.setType(lodging);
		
		Reimbursement travel1 = new Reimbursement(100.0, ts, "Visit to Mr. Darcy");
		travel1.setAuthor(ebennet);
		travel1.setStatus(pending);
		travel1.setType(travel);
		
		Reimbursement food1 = new Reimbursement(10.0, ts, "2021 Annual Spam Festival");
		food1.setAuthor(ebennet);
		food1.setStatus(pending);
		food1.setType(food);
		
		Reimbursement other1 = new Reimbursement(25.0, ts, "Marriage Counseling");
		other1.setAuthor(ebennet);
		other1.setStatus(pending);
		other1.setType(other);
		
		session.persist(lodging1);
		session.persist(travel1);
		session.persist(food1);
		session.persist(other1);
		
		tx.commit();
		session.close();
	}
}
