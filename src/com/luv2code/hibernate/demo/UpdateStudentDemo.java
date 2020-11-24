package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		//create session factory

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {

			int studentId = 1;
			//find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + studentId);

			//now get a new session and start the transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			//retrieve the student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);

			Student myStudent = session.get(Student.class, studentId);

			System.out.println("\nUpdating student with the id: "+ studentId);

			myStudent.setFirstName("Scooby");

			//commit the transaction
			session.getTransaction().commit();
			
			//mass update
			//now get a new session and start the transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			//update emails for all students
			System.out.println("\nUpdate email for all the students");
			
			session.createQuery("update Student set email='demo@ubisoft.com'")
			.executeUpdate();
			 
			//commit the transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
