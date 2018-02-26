package com.aimprosoft.task1.service;

import com.aimprosoft.task1.model.Employee;
import java.sql.SQLException;

  public interface EmployeeService {

      void addEmployee(Employee employee) throws SQLException;

      Iterable<Employee> listEmployees(String department) throws SQLException;

      void deleteEmployee(long id) throws SQLException;

      void editEmployee(long id, Employee employee) throws SQLException;

      Employee getById(long id) throws SQLException;

      Employee getByEmail(String email) throws SQLException;

  }
