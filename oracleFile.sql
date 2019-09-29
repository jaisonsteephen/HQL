create table EMPLOYEE2 ( 	empid INT NOT NULL ,
                         	first_name VARCHAR(20) default NULL,
                         	last_name VARCHAR(20) default NULL,
                         	salary INT default NULL,
                         	PRIMARY KEY (empid));
                         
create table employee_address (
							ADDID INT NOT NULL,
							ADDRESS  VARCHAR(20) default NULL,
							EMPID_FK     INT  default NULL,
							PRIMARY KEY (ADDID)
							);
CREATE OR REPLACE TRIGGER EMPLOYEE2IDTRIGR
BEFORE
INSERT ON EMPLOYEE2
FOR EACH ROW
BEGIN
	SELECT EMP_ID_SEQ.NEXTVAL
	INTO :new.empid
	FROM dual;
END;
/   -- this / should be in the next line,  to execute procedure created

CREATE OR REPLACE TRIGGER EMPLOYEE_ADDRESS_TRIGR 
BEFORE INSERT ON EMPLOYEE_ADDRESS
FOR EACH ROW
BEGIN
	SELECT EMP_ID_SEQ.NEXTVAL
	INTO:new.ADDID
	FROM dual;
END;
/	-- this / should be in the next line,  to execute procedure created