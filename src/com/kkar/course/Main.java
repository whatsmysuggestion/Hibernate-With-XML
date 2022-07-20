package com.kkar.course;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.kkar.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		Main obj = new Main();
		
		
		
		  Long courseId1 = obj.saveCourse("Physics1"); 
		  System.out.println(courseId1);
		  
		  Long courseId2 = obj.saveCourse("Chemistry1"); 
		  Long courseId3 = obj.saveCourse("Maths1");
		 
		 

		//obj.deleteCourse(new Long(3));

		// obj.updateCourse(new Long(3),"Mathematics2");

		/*
		 * obj.listCourse(); obj.deleteCourse(courseId2); obj.listCourse();
		 */

		// obj.updateCourse(new Long(3), "Physics");
			
		
		//	obj.listCourse();

	}

	public Long saveCourse(String courseName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long courseId = null;
		try {
			transaction = session.beginTransaction();
			Course course = new Course();
			course.setCourseName(courseName);
			courseId = (Long) session.save(course);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return courseId;
	}
	
	

	public void deleteCourse(Long courseId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Course course = (Course) session.get(Course.class, courseId);
			session.delete(course);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void updateCourse(Long courseId, String courseName) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Course course = (Course) session.get(Course.class, courseId);
			course.setCourseName(courseName);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void listCourse() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();	
			
			
			/*
			 * Criteria cr=session.createCriteria(Course.class);
			 * cr.addOrder(Order.desc("courseId")); cr.add(Restrictions.eq("courseId",new
			 * Long(2))); //cr.setMaxResults(1); //
			 * //cr.setProjection(Projections.countDistinct("courseName")); List<Course>
			 * rows = cr.list(); for(Course row : rows)
			 * System.out.println(row.getCourseId()+"   "+row.getCourseName());
			 */
			  
			 
			/*
			 * 
			 * Query qry=session.
			 * createQuery("from Course C where C.courseId<:a order by C.courseId desc");
			 * qry.setParameter("a", new Long(3));
			 * 
			 * // qry.setMaxResults(3);
			 * 
			 * List<Course> rows = qry.list(); for(Course row : rows)
			 * System.out.println(row.getCourseId()+"   "+row.getCourseName());
			 */
			  
			 
			
			
			  SQLQuery query = session.createSQLQuery("select * from COURSES"); 
			  List rows =  query.list(); 
			  
			  for(Object row : rows)
			  {			  
			  Course course=(Course)row;
			  System.out.println(course.getCourseId()+"  "+course.getCourseName());	  
			  
			  }
			 
			 

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
