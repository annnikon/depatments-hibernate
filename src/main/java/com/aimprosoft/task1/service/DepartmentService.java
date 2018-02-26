package com.aimprosoft.task1.service;

import com.aimprosoft.task1.model.Department;

import java.sql.SQLException;

public interface DepartmentService {

    Iterable<Department> listDepartments() throws SQLException;

    void addDepartment(Department department) throws SQLException;

    void deleteDepartment(long id) throws SQLException;

    void editDepartment(long id, Department department) throws SQLException;

    Department getById(long id) throws SQLException;

    Department getByName(String name) throws SQLException;
}
