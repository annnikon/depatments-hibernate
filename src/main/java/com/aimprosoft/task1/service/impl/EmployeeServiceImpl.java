package com.aimprosoft.task1.service.impl;

import com.aimprosoft.task1.dao.EmployeeDao;
import com.aimprosoft.task1.dao.hibernateimpl.HibernateEmployeeDaoImp;
import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.service.EmployeeService;

import java.sql.SQLException;

public class EmployeeServiceImpl implements EmployeeService {
    static EmployeeDao dao = new HibernateEmployeeDaoImp();

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        dao.add(employee);
    }

    @Override
    public Iterable<Employee> listEmployees(String department) throws SQLException {
        return dao.list(department);
    }

    @Override
    public void deleteEmployee(long id) throws SQLException {
        dao.delete(id);
    }

    @Override
    public void editEmployee(long id, Employee employee) throws SQLException {
        dao.update(id, employee);
    }

    @Override
    public Employee getById(long id) throws SQLException {
        return dao.getById(id);
    }

    @Override
    public Employee getByEmail(String email) throws SQLException {
        return dao.getByEmail(email);
    }

}
