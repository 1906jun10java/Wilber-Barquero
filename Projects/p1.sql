/*******************************************************************************
   Drops
********************************************************************************/
ALTER TABLE EMPLOYEE DROP CONSTRAINT FK_EMP_REPORTSTO_EMPID;
/
ALTER TABLE EMPLOYEE DROP CONSTRAINT FK_CREDENTIALS_EMPLOYEE;
/
ALTER TABLE REIMBURSEMENT DROP CONSTRAINT FK_EMPLOYEE_REIMBURSEMENT;
/
DROP PROCEDURE CREATE_EMPLOYEE;
/
DROP PROCEDURE DELETE_EMPLOYEE;
/
DROP SEQUENCE EMPLOYEE_SEQUENCE;
/
DROP SEQUENCE REIMBURSEMENT_SEQUENCE;
/
DROP TRIGGER EMPLOYEE_ON_INSERT;
/
DROP TRIGGER REIMBURSEMENT_ON_INSERT;
/
DROP TABLE EMPLOYEE;
/
DROP TABLE CREDENTIALS;
/
DROP TABLE REIMBURSEMENT;
/

/*******************************************************************************
   Table Creation 
********************************************************************************/
CREATE TABLE CREDENTIALS(
EMP_EMAIL VARCHAR2(30) PRIMARY KEY,
EMP_PASS VARCHAR2(12)
);
/

CREATE TABLE EMPLOYEE(
EMP_ID NUMBER PRIMARY KEY, 
EMP_FIRSTNAME VARCHAR2(30), 
EMP_LASTNAME VARCHAR2(30), 
EMP_EMAIL VARCHAR2(30) NOT NULL,
EMP_DEPARTMENT VARCHAR2(30),
EMP_REPORTSTO NUMBER,


CONSTRAINT FK_CREDENTIALS_EMPLOYEE
FOREIGN KEY (EMP_EMAIL)
REFERENCES CREDENTIALS(EMP_EMAIL)
);
/


CREATE TABLE REIMBURSEMENT(
REIM_ID NUMBER PRIMARY KEY,
EMP_ID NUMBER,
REIM_AMMOUNT NUMBER(9,2),
--REIM_DATE DATE,
REIM_TYPE VARCHAR2(30),
--REIM_RECIPT BLOB,
REIM_STATUS VARCHAR2(30),
REIM_APPROVEDBY VARCHAR2(30),


CONSTRAINT FK_EMPLOYEE_REIMBURSEMENT
FOREIGN KEY (EMP_ID)
REFERENCES EMPLOYEE(EMP_ID)
);
/

/*******************************************************************************
   Creating Sequences
********************************************************************************/
CREATE SEQUENCE EMPLOYEE_SEQUENCE
START WITH 1
INCREMENT BY 1;
/

CREATE SEQUENCE REIMBURSEMENT_SEQUENCE
START WITH 1000
INCREMENT BY 1;
/

/*******************************************************************************
   Store Procedures
********************************************************************************/
CREATE OR REPLACE PROCEDURE CREATE_EMPLOYEE(    -- INSERT_EMPLOYEE
    EMP_FIRSTNAME IN VARCHAR2, 
    EMP_LASTNAME IN VARCHAR2, 
    EMP_EMAIL IN VARCHAR2,
    EMP_DEPARTMENT IN VARCHAR2,
    EMP_REPORTSTO IN NUMBER,
    EMP_PASS IN VARCHAR2
    )
AS
BEGIN
    INSERT INTO CREDENTIALS VALUES(EMP_EMAIL, EMP_PASS);
    INSERT INTO EMPLOYEE VALUES(
        EMPLOYEE_SEQUENCE.NEXTVAL, 
        EMP_FIRSTNAME, 
        EMP_LASTNAME, 
        EMP_EMAIL,
        EMP_DEPARTMENT,
        EMP_REPORTSTO
        );
    COMMIT;
END;
/


CREATE OR REPLACE PROCEDURE UPDATE_PERSONAL_EMPLOYEE_INFO(
EMP_ID IN EMPLOYEE.EMP_ID%TYPE,
EMP_FIRSTNAME IN EMPLOYEE.EMP_FIRSTNAME%TYPE,
EMP_LASTNAME IN EMPLOYEE.EMP_LASTNAME%TYPE,
--EMP_EMAIL IN EMPLOYEE.EMP_EMAIL%TYPE,
EMP_DEPARTMENT IN EMPLOYEE.EMP_DEPARTMENT%TYPE,
EMP_REPORTSTO IN EMPLOYEE.EMP_REPORTSTO%TYPE
)
IS
BEGIN
UPDATE EMPLOYEE SET EMP_FIRSTNAME = EMP_FIRSTNAME WHERE EMP_ID = EMP_ID;
UPDATE EMPLOYEE SET EMP_LASTNAME = EMP_LASTNAME WHERE EMP_ID = EMP_ID;
--UPDATE CREDENTIALS SET EMP_EMAIL = EMP_EMAIL WHERE EMP_EMAIL = EMP_EMAIL;
--UPDATE EMPLOYEE SET EMP_EMAIL = EMP_EMAIL WHERE EMP_ID = EMP_ID;
UPDATE EMPLOYEE SET EMP_DEPARTMENT = EMP_DEPARTMENT WHERE EMP_ID = EMP_ID;
UPDATE EMPLOYEE SET EMP_REPORTSTO = EMP_REPORTSTO WHERE EMP_ID = EMP_ID;
COMMIT;
END;
/


CREATE OR REPLACE PROCEDURE UPDATE_EMPLOYEE_PASSWORD(
EMP_EMAIL IN CREDENTIALS.EMP_EMAIL%TYPE,
EMP_PASS IN CREDENTIALS.EMP_PASS%TYPE
)
IS
BEGIN
UPDATE CREDENTIALS SET EMP_PASS = EMP_PASS WHERE EMP_EMAIL = EMP_EMAIL;
COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE DELETE_EMPLOYEE(    -- INSERT_EMPLOYEE
    EMAIL IN VARCHAR2 
    )
AS
BEGIN
    DELETE FROM EMPLOYEE WHERE EMP_EMAIL=EMAIL;
    DELETE FROM CREDENTIALS WHERE EMP_EMAIL=EMAIL;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE REQUEST_REIMBURSMENT(    -- REQUEST_REINBURSMENT 
    EMP_ID IN NUMBER,
    REIM_AMMOUNT IN NUMBER,
    --REIM_DATE IN DATE,
    REIM_TYPE IN VARCHAR2
    --REIM_RECIPT IN BLOB,
    )
AS
BEGIN
    INSERT INTO REIMBURSEMENT(REIM_ID, EMP_ID, REIM_AMMOUNT, REIM_TYPE) VALUES(
        REIMBURSEMENT_SEQUENCE.NEXTVAL,
        EMP_ID,
        REIM_AMMOUNT,
        --REIM_DATE,
        REIM_TYPE
        --REIM_RECIPT, 
        );
    COMMIT;
END;
/

/*******************************************************************************
   Inserting Employee's in the DB
********************************************************************************/
EXECUTE CREATE_EMPLOYEE('Wilber','Barquero','wb@gmail.com','Software','','p4ssw0rd');
EXECUTE CREATE_EMPLOYEE('Suko','Lee','sl@gmail.com','Software','1','p4ssw0rd');
EXECUTE CREATE_EMPLOYEE('Ingrid','Lizama','il@gmail.com','Hardware','','p4ssw0rd');
EXECUTE CREATE_EMPLOYEE('Claudia','Saenz','cs@gmail.com','Hardware','2','p4ssw0rd');
EXECUTE CREATE_EMPLOYEE('Mario','Rivera','mr@gmail.com','QA','3','p4ssw0rd');

DELETE FROM EMPLOYEE WHERE EMP_EMAIL ='cs@gmail.com';
EXECUTE DELETE_EMPLOYEE('cs@gmail.com');

EMP_ID IN NUMBER,
    REIM_AMMOUNT IN NUMBER,
    --REIM_DATE IN DATE,
    REIM_TYPE IN VARCHAR2
/*******************************************************************************
   Inserting Reimbursment in the DB
********************************************************************************/
EXECUTE REQUEST_REIMBURSMENT(2,500,'Travel');
EXECUTE REQUEST_REIMBURSMENT(2,1000,'Food')
EXECUTE REQUEST_REIMBURSMENT(4,200,'Food');
EXECUTE REQUEST_REIMBURSMENT(4,300,'Gas');
EXECUTE REQUEST_REIMBURSMENT(4,100,'Travel');


/*******************************************************************************
   Query TESTS
********************************************************************************/
DELETE FROM EMPLOYEE WHERE EMP_ID=1;
SELECT 
    E.EMP_FIRSTNAME ||' '|| E.EMP_LASTNAME EMPLOYEE,
    M.EMP_FIRSTNAME ||' '|| M.EMP_LASTNAME MANAGER
FROM
    EMPLOYEE E
INNER JOIN
    EMPLOYEE M ON M.EMP_ID = E.EMP_REPORTSTO;

SELECT * FROM EMPLOYEE WHERE EMP_EMAIL = 'wb@gmail.com';
