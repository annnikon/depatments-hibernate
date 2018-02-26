package com.aimprosoft.task1.dao.impl;

public interface SqlStatements {

     String DEPARTMENTS_TABLE = "departments";
     String DEPARTMENTS_DECLARED_FIELDS = " (name, info) ";
     String DEPARTMENTS_NEW_FIELDS = " name = ?, info = ? ";
     String DEPARTMENTS_ALL_SQL = "SELECT * FROM "+DEPARTMENTS_TABLE;
     String DEPARTMENTS_INSERT_SQL = "INSERT INTO "+DEPARTMENTS_TABLE+DEPARTMENTS_DECLARED_FIELDS+" VALUES (?,?)";
     String DEPARTMENTS_UPDATE_SQL = "UPDATE "+DEPARTMENTS_TABLE+" SET "+DEPARTMENTS_NEW_FIELDS;
     String DEPARTMENTS_DELETE_SQL = "DELETE FROM "+DEPARTMENTS_TABLE;

     String EMPLOYEES_TABLE = "employees";
     String EMPLOYEES_DECLARED_FIELDS = " (email, name, birthday, room, department) ";
     String EMPLOYEES_NEW_FIELDS = " email = ?, name = ?, birthday = ?, room = ?, department = ? ";
     String EMPLOYEES_ALL_SQL = "SELECT * FROM "+ EMPLOYEES_TABLE;
     String EMPLOYEES_INSERT_SQL = "INSERT INTO "+ EMPLOYEES_TABLE + EMPLOYEES_DECLARED_FIELDS +" VALUES (?, ?, ?, ?, ?)";
     String EMPLOYEES_UPDATE_SQL = "UPDATE "+ EMPLOYEES_TABLE +" SET "+EMPLOYEES_NEW_FIELDS;
     String EMPLOYEES_DELETE_SQL = "DELETE FROM "+ EMPLOYEES_TABLE;


    String ID_CONDITION = " WHERE id = ?";
    String NAME_CONDITION = " WHERE name = ?";
    String EMAIL_CONDITION = " WHERE email = ?";
    String DEPARTMENT_CONDITION = " WHERE department = ?";
}
