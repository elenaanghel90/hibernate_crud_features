package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		//create session factory
		
				SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Student.class)
						.buildSessionFactory();
				
				//create session
				Session session = factory.getCurrentSession();
				
				try {
					//use the session object to save Java object
					
					//create 3 students object
					System.out.println("Creating 3 students  object..");
					Student tempStudent = new Student("Paul", "Dumitrascu","paul.dumitrascu@zentiva.com");
					Student tempStudent1 = new Student("May", "Bonia","may.bonia@zentiva.com");
					Student tempStudent2 = new Student("Aurelian", "Chirnogea","aurelian.chirnogea@zentiva.com");
					Student tempStudent3 = new Student("Emanuel", "Ciuca","emanuel.ciuca@zentiva.com");
					Student tempStudent4 = new Student("Emanuel", "Anghel","emanuel.anghel@zentiva.com");
					
					//start a transaction
					session.beginTransaction();
					
					//save the student object
					System.out.println("Saving the student..");
					session.save(tempStudent);
					session.save(tempStudent1);
					session.save(tempStudent2);
					session.save(tempStudent3);
					session.save(tempStudent4);
					
					//commit transaction
					session.getTransaction().commit();
					
					System.out.println("Done!");
					}
				finally {
					factory.close();
				}
			}


	}


