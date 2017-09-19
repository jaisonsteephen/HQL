import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

/*create table EMPLOYEE2 (
		   id INT NOT NULL auto_increment,
		   first_name VARCHAR(20) default NULL,
		   last_name  VARCHAR(20) default NULL,
		   salary     INT  default NULL,
		   PRIMARY KEY (id)
		);*/

public class ManageEmployee {
   private static SessionFactory factory; 
   public static void main(String[] args) {
      try{
    	  factory = HibernateUtil.getSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      ManageEmployee ME = new ManageEmployee();

/*       Add few employee records in database 
      Integer empID1 = ME.addEmployee("Zara", "Ali", 2000);
      Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
      Integer empID3 = ME.addEmployee("John", "Paul", 5000);
      Integer empID4 = ME.addEmployee("Mohd", "Yasee", 3000);

       List down all the employees 
      ME.listEmployees();

       Print Total employee's count 
      ME.countEmployee();

       Print Toatl salary 
      ME.totalSalary();*/
      ME.hqlExamples();
   }
   
   public void hqlExamples(){
	   Session session = factory.openSession();
/*	   Query qry=session.createQuery("select e from Employee e");
	   List <Employee>eLt=qry.list();
	   for(Employee e:eLt){
		   System.out.println(e.getFirstName());
	   }*/
	   
	   
/*	   Transaction tx=session.beginTransaction();
	   Query qry=session.createQuery("update Employee set firstName=:f where id=:i"); 
	   qry.setParameter("f", "John");
	   qry.setParameter("i",3);
	   int st=qry.executeUpdate();
	   tx.commit();
	   System.out.println(st);*/
	   
	   
/*	   Transaction tx=session.beginTransaction();
	   Query qry=session.createQuery("delete from c where id=:i"); 
	   qry.setParameter("i",3);
	   int st=qry.executeUpdate();
	   tx.commit();
	   System.out.println(st);
	   session.close();*/
	   
/*	   Query q=session.createQuery("select sum(salary) from Employee");  
	   List<Integer> list=q.list();  
	   System.out.println(list.get(0)); */
	   
	   
/*	   Criteria c=session.createCriteria(Employee.class); 
	   List<Employee> list=c.list();
	   for(Employee e:list){
		   System.out.println(e.getFirstName());
	   }*/
	   
	   
/*	   Criteria c=session.createCriteria(Employee.class); 
	   c.add(Restrictions.gt("salary", 3000));
	   List<Employee> list=c.list();
	   for(Employee e:list){
		   System.out.println(e.getFirstName());
	   }*/
	   
	   
/*	   Criteria c=session.createCriteria(Employee.class); 
	   c.addOrder(Order.asc("firstName"));
	   List<Employee> list=c.list();
	   for(Employee e:list){
		   System.out.println(e.getFirstName());
	   }*/
	   
	   
/*	   Criteria c=session.createCriteria(Employee.class);
	   ProjectionList lt=Projections.projectionList();
	   lt.add(Projections.property("firstName"));
	   lt.add(Projections.property("lastName"));
	   c.setProjection(lt);
	   List list=c.list();  */
	   
	   
	   
	   
/*	   CREATE TABLE customer (
			   id INT NOT NULL AUTO_INCREMENT,
			   firstname varchar(50) NOT NULL,
			   lastname varchar(50) NOT NULL,
			   PRIMARY KEY (id)
			 ) ENGINE=INNODB;
			  
			 CREATE TABLE contact (
			   id INT,
			   customer_id INT,
			   info varchar(50) NOT NULL,
			   type varchar(50) NOT NULL,
			   INDEX par_ind (customer_id),
			   CONSTRAINT fk_customer FOREIGN KEY (customer_id)
			   REFERENCES customer(id)
			   ON DELETE CASCADE
			   ON UPDATE CASCADE
			 ) ENGINE=INNODB;*/
	   
//	   Query q=session.createQuery("from Customer cu join cu.contactLt ct where cu.firstName='a1'");
	   Query q=session.createQuery("from Customer cu,Contact ct where cu.firstName='a1' and cu.id=ct.customerFkid");
	/*   Query q=session.createQuery("from Customer cu join Contact ct on cu.id=ct.customerFkid and cu.firstName='a1'");*/
	   List list=q.list(); 
	  
	   
	   EntityManager e;
   }
   
   
   
   
/*    Method to CREATE an employee in the database 
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

    Method to  READ all the employees having salary more than 2000 
   public void listEmployees( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Criteria cr = session.createCriteria(Employee.class);
         // Add restriction.
         cr.add(Restrictions.gt("salary", 2000));
         List employees = cr.list();

         for (Iterator iterator = 
                           employees.iterator(); iterator.hasNext();){
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
    Method to print total number of records 
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
   Method to print sum of salaries 
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
   }*/
}