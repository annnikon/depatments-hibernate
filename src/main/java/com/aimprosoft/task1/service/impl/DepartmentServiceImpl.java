package com.aimprosoft.task1.service.impl;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.dao.hibernateimpl.HibernateDepartmentDaoImpl;
import com.aimprosoft.task1.dao.impl.DepartmentDaoImpl;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.service.DepartmentService;

import java.sql.SQLException;

public class DepartmentServiceImpl implements DepartmentService {

    static DepartmentDao dao = new HibernateDepartmentDaoImpl();

    @Override
    public void addDepartment(Department department) throws SQLException {
        dao.add(department);
    }

    @Override
    public Iterable<Department> listDepartments() throws SQLException {
        return dao.list();
    }

    @Override
    public void deleteDepartment(long id) throws SQLException {
        dao.delete(id);
    }

    @Override
    public void editDepartment(long id, Department department) throws SQLException {
        dao.update(id, department);
    }

    @Override
    public Department getById(long id) throws SQLException {
        return dao.getById(id);
    }

    @Override
    public Department getByName(String name) throws SQLException {
        return dao.getByName(name);
    }

}
