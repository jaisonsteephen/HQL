create table EMPLOYEE2 (
		   empid INT NOT NULL auto_increment,
		   first_name VARCHAR(20) default NULL,
		   last_name  VARCHAR(20) default NULL,
		   salary     INT  default NULL,
		   PRIMARY KEY (empid)
		);
create table employee_address (
			ADDID INT NOT NULL auto_increment,
			ADDRESS  VARCHAR(20) default NULL,
			EMPID_FK     INT  default NULL,
			PRIMARY KEY (ADDID)
);