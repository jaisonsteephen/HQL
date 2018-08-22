package org.hql.annotation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee_address")
public class Address {

@Id
@Column(name="ADDID")
private int id;

private String address;

@ManyToOne
@JoinColumns({@JoinColumn(name="EMPID_FK",referencedColumnName="EMPID")})
private Employee emp;

public Address(){
	
}
public Address(int id,String address){
	this.id=id;
	this.address=address;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public Employee getEmp() {
	return emp;
}
public void setEmp(Employee emp) {
	this.emp = emp;
}

}
