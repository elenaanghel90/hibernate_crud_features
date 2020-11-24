package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		//create session factory

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();

		try {
			//start a transaction
			session.beginTransaction();

			//query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			//display the students
			System.out.println("The all students from the list: ");
			displayStudents(theStudents);

			//query the students: lastName="Chiprian"
			theStudents = session.createQuery("from Student s where s.lastName='Chiprian'").getResultList();

			//display the students with the lastName: Chiprian
			System.out.println("\n\nStudents who have lastName of Chiprian: ");
			displayStudents(theStudents);
			
			//query students: lastName="Ciuca" OR firstName: Emanuel"
			theStudents = session.createQuery("from Student s where s.lastName='Ciuca' OR "
					+ "s.firstName='Emanuel'").getResultList();
			System.out.println("\n\nStudents who have lastName of Ciuca OR firstName Emanuel: ");
			displayStudents(theStudents);
			
			//query students where email LIKE 'emanuel.anghel@zentiva.com'
			theStudents =  session.createQuery("from Student s where"
					+ " s.email LIKE '%zentiva.com'").getResultList();
			System.out.println("\n\nStudents whose have email LIKE '%zentiva.com': ");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);

		}
	}

}
