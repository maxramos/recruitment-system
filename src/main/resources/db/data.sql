-- *** ROLES ***
INSERT INTO RS_ROLES(ROLE_ID, NAME) VALUES(1, 'unassigned');
INSERT INTO RS_ROLES(ROLE_ID, NAME) VALUES(2, 'admin');
INSERT INTO RS_ROLES(ROLE_ID, NAME) VALUES(3, 'editor');
INSERT INTO RS_ROLES(ROLE_ID, NAME) VALUES(4, 'recruiter');

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- *** EMPLOYEES ***

-- unassigned
INSERT INTO RS_EMPLOYEES(EMPLOYEE_ID, USERNAME, ROLE_ID, ENABLED)
VALUES(1, 'rtan', 1, 1);

-- admin
INSERT INTO RS_EMPLOYEES(EMPLOYEE_ID, USERNAME, ROLE_ID, ENABLED)
VALUES(2, 'mramos', 2, 1);

-- editor
INSERT INTO RS_EMPLOYEES(EMPLOYEE_ID, USERNAME, ROLE_ID, ENABLED)
VALUES(3, 'rreyles', 3, 1);

-- recruiter
INSERT INTO RS_EMPLOYEES(EMPLOYEE_ID, USERNAME, ROLE_ID, ENABLED)
VALUES(4, 'vbarcellano', 4, 1);

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- *** CANDIDATES ***
