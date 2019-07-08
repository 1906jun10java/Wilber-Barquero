/*Wilber Barquero*/

--TASK 2.1

--Select all records from the Employee table.
SELECT * FROM EMPLOYEE;
/
--Select all records from the Employee table where last name is King.
SELECT LASTNAME
FROM EMPLOYEE
WHERE LASTNAME='King';
/
--Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT *
FROM EMPLOYEE
WHERE FIRSTNAME='Andrew' AND REPORTSTO IS NULL;
/

--TASK 2.2 ORDER BY
--Select all albums in Album table and sort result set in descending order by title.
SELECT * FROM ALBUM
ORDER BY TITLE DESC;
/
--Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME
FROM CUSTOMER
ORDER BY CITY ASC;
/

--TASK 2.3 INSERT INTO

--Insert two new records into Genre table 
INSERT INTO GENRE VALUES(26,'EDM');
INSERT INTO GENRE VALUES(27,'Progresive House');

--Insert two new records into Employee table
INSERT INTO EMPLOYEE VALUES(9,'Wilber','Barquero','DBA',7,'20-DEC-1991','01-JAN-2011','12702 Bruce B Downs Blvd','Tampa','FL','USA','T4H 5N9','+1 (813) 549-8160','+1 (813) 549-8200','wb@chinookcorp.com');
INSERT INTO EMPLOYEE VALUES(10,'Maodo','Saw','DBA',7,'20-JAN-1991','01-FEB-2011','12708 Bruce B Downs Blvd','Tampa','FL','USA','T4H 5N9','+1 (813) 549-8100','+1 (813) 549-8300','ms@chinookcorp.com');
/
--Insert two new records into Customer table
INSERT INTO CUSTOMER VALUES(60,'Kendrick','Lamar','','3125 Cove Bend Dr','Tampa','FL','USA','33613','+1 (813) 977-8988','','kendrick.lamar@gmail.com',6);
INSERT INTO CUSTOMER VALUES(61,'Steve','Jobs','Sprint','3130 Cove Bend Dr','Tampa','FL','USA','33615','+1 (813) 977-9000','+1 (800) 900-3456','steve.jobs@sprint.com',7);
/

-- TASK 2.4 UPDATE
--Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CUSTOMER
SET FIRSTNAME = 'Robert', LASTNAME = 'Walter'
WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';
/
--Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”
UPDATE ARTIST
SET NAME = 'CCR'
WHERE NAME = 'Creedence Clearwater Revival';
/

--TASK 2.5 LIKE
--Select all invoices with a billing address like “T%” 
SELECT *
FROM INVOICE
WHERE BILLINGADDRESS LIKE 'T%';
/

--TASK 2.6 BETWEEN
--Select all invoices that have a total between 15 and 50
SELECT *
FROM INVOICE
WHERE TOTAL BETWEEN 15 AND 50;
/

--Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT *
FROM EMPLOYEE
WHERE HIREDATE BETWEEN '01-JUN-2003' AND '01-MAR-2004';
/

--TASK 2.7 DELETE
--Delete a record in Customer table where the name is Robert Walter (There may be constraints that rely on this, find out how to resolve them).
--Since we have forign key constraints we need to delete the records from the child tables to be able to deleted from customer

/*
brute force way, not good if you have million of records
DELETE FROM INVOICELINE
WHERE INVOICEID IN (116,245,268,290,342,50,61);

DELETE FROM INVOICELINE
WHERE INVOICEID IN (SELECT INVOCICEID FROM INVOCE WHERE CUSTOMERID=32);

DELETE FROM INVOICE
WHERE CUSTOMERID=32;

DELETE FROM CUSTOMER
WHERE FIRSTNAME='Robert' AND LASTNAME='Walter';
*/

-- REMOVE INVOICES BELONGING TO THE CUSTOMER IN INVOICELINE
DELETE FROM INVOICELINE WHERE INVOICEID IN (
SELECT INVOICEID FROM INVOICE
WHERE CUSTOMERID = (
SELECT CUSTOMERID FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'));
/
-- REMOVE THE CUSTOMER IN INVOICE
DELETE FROM INVOICE     
WHERE CUSTOMERID IN (
SELECT CUSTOMERID FROM CUSTOMER
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'
);
/
-- REMOVE CUSTOMER FROM CUSTOMER
DELETE FROM CUSTOMER    
WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';
/

--TASK 3.1 System Defined Functions
--– Create a function that returns the current time.
CREATE OR REPLACE FUNCTION GET_CURRENT_TIME
RETURN TIMESTAMP IS
CURRENT_TIME TIMESTAMP;
BEGIN
    CURRENT_TIME := CURRENT_TIMESTAMP;
    RETURN(CURRENT_TIME);
END;
/
SELECT GET_CURRENT_TIME() FROM DUAL;
/
/*
SELECT CURRENT_TIMESTAMP
FROM DUAL;
/
You then modified the session time zone with the following ALTER SESSION command:
ALTER SESSION SET TIME_ZONE = '-2:0';
*/

--create a function that returns the length of name in MEDIATYPE table
CREATE OR REPLACE FUNCTION SP_NAME_LENGTH(USER_PARAMETER IN NUMBER)
RETURN NUMBER IS
LEN NUMBER;
BEGIN
SELECT LENGTH(NAME) INTO LEN FROM MEDIATYPE WHERE MEDIATYPEID = USER_PARAMETER;
RETURN(LEN);
END;
/
SELECT SP_NAME_LENGTH(3) FROM DUAL;
/

--TASK 3.2 System Defined Aggregate Functions
--Create a function that returns the average total of all invoices 
CREATE OR REPLACE FUNCTION SP_AVG_INVOICE_TOTAL
RETURN NUMBER IS
TOTAL_AVG NUMBER;
BEGIN
SELECT AVG(TOTAL) INTO TOTAL_AVG FROM INVOICE;
RETURN(TOTAL_AVG);
END;
/
SELECT SP_AVG_INVOICE_TOTAL() FROM DUAL;
/
--Create a function that returns the most expensive track
/*
SELECT MAX(aggregate_expression)
FROM tables
[WHERE conditions];

SELECT MAX(UNITPRICE)
FROM TRACK;
/
*/

CREATE OR REPLACE FUNCTION MOST_EXPENSIVE_TRACK
RETURN NUMBER IS
MAXPRICE_TRACK NUMBER;
BEGIN
SELECT MAX(UNITPRICE) INTO MAXPRICE_TRACK FROM TRACK;
RETURN(MAXPRICE_TRACK);
END;
/
SELECT MOST_EXPENSIVE_TRACK() FROM DUAL;
/

/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
--TASK 3.3 User Defined Scalar Functions
--Create a function that returns the average price of invoice line items in the invoiceline table
/*
The syntax for the AVG function in Oracle/PLSQL is:
SELECT AVG(UNITPRICE)
FROM INVOICELINE;
*/

CREATE OR REPLACE FUNCTION AVG_INVOICELINE_LNITEM_PRICE
RETURN NUMBER IS
AVG_PRICE NUMBER;
BEGIN
SELECT AVG(UNITPRICE) INTO AVG_PRICE FROM INVOICELINE;
RETURN(AVG_PRICE);
END;
/
SELECT AVG_INVOICELINE_LNITEM_PRICE() FROM DUAL;
/

--TASK 3.4 User Defined Table Valued Functions
--Create a function that returns all employees who were born after 1968.
CREATE OR REPLACE FUNCTION EMPLOYEES_BORN_AFTER_1968
RETURN SYS_REFCURSOR IS
EMP_RESULT SYS_REFCURSOR;
BEGIN
OPEN EMP_RESULT FOR
SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE
WHERE BIRTHDATE > '01-JAN-1968';
RETURN EMP_RESULT;
END;
/
SELECT EMPLOYEES_BORN_AFTER_1968 FROM DUAL;
/
/*+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++*/
--4.0 Stored Procedures
--In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures that take input and output parameters.

--TASK 4.1 Basic Stored Procedure
--Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE GET_ALL_EMP_NAMES_LASTS(S OUT SYS_REFCURSOR)
IS
BEGIN
OPEN S FOR
SELECT FIRSTNAME, LASTNAME FROM EMPLOYEE;
END;
/
SET SERVEROUTPUT ON
DECLARE
S SYS_REFCURSOR;
FIRST_NAME EMPLOYEE.FIRSTNAME%TYPE;
LAST_NAME EMPLOYEE.LASTNAME%TYPE;
BEGIN
GET_ALL_EMP_NAMES_LASTS(S);
LOOP
    FETCH S
    INTO FIRST_NAME, LAST_NAME;
    EXIT WHEN S%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(FIRST_NAME || ' ' || LAST_NAME);
END LOOP;
CLOSE S;
END;
/


--TASK 4.2 Stored Procedure Input Parameters
--Create a stored procedure that updates the personal information of an employee.

CREATE OR REPLACE PROCEDURE UPDATE_PERSONAL_EMPLOYEE_INFO(
UPDATED_EMPLOYEEID IN EMPLOYEE.EMPLOYEEID%TYPE,
UPDATED_LASTNAME IN EMPLOYEE.LASTNAME%TYPE,
UPDATED_FIRSTNAME IN EMPLOYEE.FIRSTNAME%TYPE,
UPDATED_ADDRESS IN EMPLOYEE.ADDRESS%TYPE,
UPDATED_CITY IN EMPLOYEE.CITY%TYPE,
UPDATED_COUNTRY IN EMPLOYEE.COUNTRY%TYPE,
UPDATED_POSTALCODE IN EMPLOYEE.POSTALCODE%TYPE,
UPDATED_PHONE IN EMPLOYEE.PHONE%TYPE,
UPDATED_FAX IN EMPLOYEE.FAX%TYPE,
UPDATED_EMAIL IN EMPLOYEE.EMAIL%TYPE)
IS
BEGIN
UPDATE EMPLOYEE SET LASTNAME = UPDATED_LASTNAME WHERE EMPLOYEEID = UPDATED_EMPLOYEEID;
UPDATE EMPLOYEE SET FIRSTNAME = UPDATED_FIRSTNAME WHERE EMPLOYEEID = UPDATED_EMPLOYEEID;
UPDATE EMPLOYEE SET ADDRESS = UPDATED_ADDRESS WHERE EMPLOYEEID = UPDATED_EMPLOYEEID;
UPDATE EMPLOYEE SET CITY = UPDATED_CITY WHERE EMPLOYEEID = UPDATED_EMPLOYEEID;
UPDATE EMPLOYEE SET COUNTRY = UPDATED_COUNTRY WHERE EMPLOYEEID = UPDATED_EMPLOYEEID;
UPDATE EMPLOYEE SET POSTALCODE = UPDATED_POSTALCODE WHERE EMPLOYEEID = UPDATED_EMPLOYEEID;
UPDATE EMPLOYEE SET PHONE = UPDATED_PHONE WHERE EMPLOYEEID = UPDATED_EMPLOYEEID;
UPDATE EMPLOYEE SET FAX = UPDATED_FAX WHERE EMPLOYEEID = UPDATED_EMPLOYEEID;
UPDATE EMPLOYEE SET EMAIL = UPDATED_EMAIL WHERE EMPLOYEEID = UPDATED_EMPLOYEEID;
COMMIT;
END;
/
EXECUTE UPDATE_PERSONAL_EMPLOYEE_INFO(10, 'Brown', 'Chris', '908 Reratul St', 'Tampa', 'US', '33789', '+1 868-780-2738', '+1 868-780-3000', 'browns.chris@yahoo.com');
/

--Create a stored procedure that returns the managers of an employee.

CREATE OR REPLACE PROCEDURE RETURN_EMP_MANAGER(
EMP_EMPLOYEEID IN EMPLOYEE.EMPLOYEEID%TYPE,
EMP_MANAGERID OUT EMPLOYEE.REPORTSTO%TYPE)
IS
BEGIN
SELECT REPORTSTO INTO EMP_MANAGERID
FROM EMPLOYEE WHERE EMPLOYEEID = EMP_EMPLOYEEID;
END;
/
SET SERVEROUTPUT ON
DECLARE
EMP_EMPLOYEEID EMPLOYEE.EMPLOYEEID%TYPE;
EMP_MANAGERID EMPLOYEE.REPORTSTO%TYPE;
BEGIN
RETURN_EMP_MANAGER(9, EMP_MANAGERID);
DBMS_OUTPUT.PUT_LINE(EMP_MANAGERID);
END;
/

--TASK 4.3 Stored Procedure Output Parameters
--Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE GET_CUSTOMER_COMPANY_NAME(
CUSTOMER_ID IN CUSTOMER.CUSTOMERID%TYPE,
CUST_FIRST OUT CUSTOMER.FIRSTNAME%TYPE,
CUST_LAST OUT CUSTOMER.LASTNAME%TYPE,
CUST_COMP OUT CUSTOMER.COMPANY%TYPE)
IS
BEGIN
SELECT FIRSTNAME, LASTNAME, COMPANY
INTO CUST_FIRST, CUST_LAST, CUST_COMP
FROM CUSTOMER WHERE CUSTOMERID = CUSTOMER_ID;
END;
/
SET SERVEROUTPUT ON
DECLARE
CUST_FIRST CUSTOMER.FIRSTNAME%TYPE;
CUST_LAST CUSTOMER.LASTNAME%TYPE;
CUST_COMP CUSTOMER.COMPANY%TYPE;
BEGIN
GET_CUSTOMER_COMPANY_NAME(8, CUST_FIRST, CUST_LAST, CUST_COMP);
DBMS_OUTPUT.PUT_LINE(CUST_FIRST || ' ' || CUST_LAST || ' - ' || CUST_COMP);
END;
/

--TASK 5.0 Transactions
--In this section you will be working with transactions. Transactions are usually nested within a stored procedure.
--Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find out how to resolve them).
CREATE OR REPLACE PROCEDURE DELETE_INVOICE(INVOICE_ID IN INVOICE.INVOICEID%TYPE)
IS
BEGIN
-- DELETE INVOICE FROM INVOICELINE
DELETE FROM INVOICELINE
WHERE INVOICEID = INVOICE_ID;
    
--SINCE WE DELETED INVOICE FROM INVOCELINE WE ARE ABLE TO DELETE INVOICE
DELETE FROM INVOICE
WHERE INVOICEID = INVOICE_ID;
END;
/
EXECUTE DELETE_INVOICE(100);


-- TASK 6.0 Triggers
--In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.
--6.1 AFTER/FOR
--Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE OR REPLACE TRIGGER AFTER_INSERT_EMPLOYEE
AFTER INSERT ON EMPLOYEE FOR EACH ROW
BEGIN
DBMS_OUTPUT.PUT_LINE(:NEW.FIRSTNAME || ' ' || :NEW.LASTNAME || ' created');
END;
/
SET SERVEROUTPUT ON
INSERT INTO EMPLOYEE
VALUES(11, 'Messi', 'Leo', 'DBA', 6, '10-DEC-89', '25-JAN-05', 
'6400 Alder Dr', 'Houston', 'TX', 'USA', 'F5K 4H1', '+1 (800) 356-1234', 
'+1 (800) 600-1234', 'messi.leo@chinookcorp.com');
/
--Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE OR REPLACE TRIGGER AFTER_UPDATE_ALBUM
AFTER UPDATE OR INSERT ON ALBUM FOR EACH ROW
BEGIN
DBMS_OUTPUT.PUT_LINE(:NEW.TITLE || ' created');
END;
/
SET SERVEROUTPUT ON
INSERT INTO ALBUM
VALUES(348, 'In Return', 147);
/
--Create an after delete trigger on the customer table that fires after a row is deleted from the table.

CREATE OR REPLACE TRIGGER AFTER_DELETE_CUSTOMER
AFTER DELETE ON CUSTOMER FOR EACH ROW
BEGIN
DBMS_OUTPUT.PUT_LINE(:OLD.FIRSTNAME || ' ' || :OLD.LASTNAME || ' deleted');
END;
/
SET SERVEROUTPUT ON
DELETE FROM CUSTOMER WHERE CUSTOMERID = 60;
/

--TASK7.0 JOINS
--In this section you will be working with combining various tables through the use of joins. You will work with outer, inner, right, left, cross, and self joins.
--7.1 INNER
--Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT FIRSTNAME, LASTNAME, INVOICEID FROM CUSTOMER CUST
JOIN INVOICE INV ON CUST.CUSTOMERID = INV.CUSTOMERID
ORDER BY CUST.LASTNAME;
/
--7.2 OUTER
--Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, invoiceId, and total.
SELECT FIRSTNAME, LASTNAME, INVOICEID, TOTAL FROM CUSTOMER CUST
FULL JOIN INVOICE INV ON CUST.CUSTOMERID = INV.CUSTOMERID
ORDER BY CUST.LASTNAME;
/
--7.3 RIGHT
--Task – Create a right join that joins album and artist specifying artist name and title.
SELECT NAME, TITLE FROM ALBUM ALBM
RIGHT JOIN ARTIST ART ON ALBM.ARTISTID = ART.ARTISTID
ORDER BY NAME;
/
--7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT NAME, TITLE FROM ALBUM ALBM
CROSS JOIN ARTIST ART
ORDER BY NAME ASC;
/
--7.5 SELF
--Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT
(EMP.FIRSTNAME || ' ' || EMP.LASTNAME) EMPLOYEE,
(MNG.FIRSTNAME || ' ' || MNG.LASTNAME) MANAGER
FROM EMPLOYEE EMP
LEFT JOIN EMPLOYEE MNG ON EMP.REPORTSTO = MNG.EMPLOYEEID
ORDER BY MANAGER;
/