package model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.DBUtil;

public class DBUtil {
	private static final EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("DatabaseLoopingServlet"); 
			 public static EntityManagerFactory getEmFactory() {   return emf;
			 
			 }
			

			 }

