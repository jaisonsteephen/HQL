package org.hql.annotation;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class ManageEmployee {
   private static SessionFactory factory; 

	public static void main(String[] args) {
		ManageEmployee me = new ManageEmployee();
		try {factory = HibernateUtil.getSessionFactory();} catch (Throwable ex) {}
		//me.saveFewData();
		//me.hqlExamples();
		me.executeHQLSamples3();
		
	}
	
	void executeHQLSamples() {
		String hql="from Employee";
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Query qry=session.createQuery(hql);
		List<Employee> empLt=qry.list();
		for(Employee employee:empLt) {
			System.out.println(employee.getFirstName());
		}
		
	}

	void executeHQLSamples3() {
		String cc="";
		String hql="from Employee e join e.address";
		System.out.println("podeka");
		Session session=factory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Query qry=session.createQuery(hql);
		List empLt=qry.list();
		Object[]lt;
		for(Object emp:empLt) {
			lt=(Object[]) emp;
			Employee e=(Employee) lt[0];
			Address a=(Address) lt[1];
			
			System.out.println(e.getFirstName());
			System.out.println(a.getAddress());
			
			//Testing of join operation one-to-many
			Set<Address> addrs=e.getAddress();
			for(Address ad:addrs) {
				System.out.println(ad.getAddress());
			}
			
			//Testing of join operation many-to-one
			System.out.println(a.getEmp().getFirstName());
		}
	}
	void saveFewData() {

		Session session = factory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.persist(new Employee("Zara", "Ali", 2000));
		session.persist(new Employee("Daisy", "Das", 5000));
		session.persist(new Employee("John", "Paul", 5000));
		session.persist(new Employee("Mohd", "Yasee", 3000));
		tx.commit();
	}
   public void hqlExamples(){
	   Session session = factory.openSession();
	   Query qry=null;
	   Criteria crty;
	   
/*	   qry=session.createQuery("from Employee e");
	   List <Employee>eLt=qry.list();
	   for(Employee e:eLt){
		   System.out.println(e.getFirstName());
	   }*/
	   
	   
/*	   Transaction tx=session.beginTransaction();
	   qry=session.createQuery("update Employee set firstName=:f where id=:i"); 
	   qry.setParameter("f", "John");
	   qry.setParameter("i",3);
	   int st=qry.executeUpdate();
	   tx.commit();
	   System.out.println(st);*/
	   
	   
/*	   Transaction tx=session.beginTransaction();
	   qry=session.createQuery("delete from Employee where id=:i"); 
	   qry.setParameter("i",3);
	   int st=qry.executeUpdate();
	   tx.commit();
	   System.out.println(st);
	   session.close();*/
	   
	   
/*	   qry=session.createQuery("select sum(salary) from Employee");  
	   List<Integer> list=qry.list();  
	   System.out.println(list.get(0));*/ 
	   
	   
	   
/*	   crty=session.createCriteria(Employee.class); 
	   List<Employee> list=crty.list();
	   for(Employee e:list){
		   System.out.println(e.getFirstName());
	   }*/
	   
	   
/*	   crty=session.createCriteria(Employee.class); 
	   crty.add(Restrictions.gt("salary", 3000));
	   List<Employee> list=crty.list();
	   for(Employee e:list){
		   System.out.println(e.getFirstName());
	   }*/
	   
	   
/*	   crty=session.createCriteria(Employee.class); 
	   crty.addOrder(Order.asc("firstName"));
	   List<Employee> list=crty.list();
	   for(Employee e:list){
		   System.out.println(e.getFirstName());
	   }*/
	   
	   
/*	   crty=session.createCriteria(Employee.class);
	   ProjectionList lt=Projections.projectionList();
	   lt.add(Projections.property("firstName"));
	   lt.add(Projections.property("lastName"));
	   crty.setProjection(lt);
	   List list=crty.list(); */ 
	   
	   
   }
   
   
   //Method to CREATE an employee in the database 
   public Integer addEmployee(String fname, String lname, int salary){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer employeeID = null;
      try{
         tx = session.beginTransaction();
         Employee employee = new Employee(fname, lname, salary);
         employeeID = (Integer) session.save(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
      return employeeID;
   }

   //Method to  READ all the employees having salary more than 2000 
   public void listEmployees( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Criteria cr = session.createCriteria(Employee.class);
         // Add restriction.
         cr.add(Restrictions.gt("salary", 2000));
         List employees = cr.list();

         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
            Employee employee = (Employee) iterator.next(); 
            System.out.print("First Name: " + employee.getFirstName()); 
            System.out.print("  Last Name: " + employee.getLastName()); 
            System.out.println("  Salary: " + employee.getSalary()); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   //Method to print total number of records 
   public void countEmployee(){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Criteria cr = session.createCriteria(Employee.class);

         // To get total row count.
         cr.setProjection(Projections.rowCount());
         List rowCount = cr.list();

         System.out.println("Total Coint: " + rowCount.get(0) );
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
   //Method to print sum of salaries 
   public void totalSalary(){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Criteria cr = session.createCriteria(Employee.class);

         // To get total salary.
         cr.setProjection(Projections.sum("salary"));
         List totalSalary = cr.list();

         System.out.println("Total Salary: " + totalSalary.get(0) );
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
}