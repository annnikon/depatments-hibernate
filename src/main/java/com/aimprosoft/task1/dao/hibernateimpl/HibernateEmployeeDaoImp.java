package com.aimprosoft.task1.dao.hibernateimpl;

import com.aimprosoft.task1.dao.EmployeeDao;
import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class HibernateEmployeeDaoImp implements EmployeeDao {

    private static SessionFactory factory = HibernateUtils.getSessionFactory();


class EmployeeCriteria {

    Session session;
    CriteriaBuilder builder;
    CriteriaQuery<Employee> criteria;
    Root<Employee> root;

    public EmployeeCriteria(Session session) {

        this.session = session;
        builder = session.getCriteriaBuilder();
        criteria = builder.createQuery(Employee.class);
        root = criteria.from(Employee.class);
        criteria.select(root);

    }

    List<Employee> restrictByDepartmentName(String departmentName) {
        criteria.where(builder.equal(root.get("departmentName"),departmentName));
        criteria.orderBy(builder.asc(root.get("email")));
        return session.createQuery(criteria).getResultList();

    }

    Employee restrictById(long id) {
        criteria.where(builder.equal(root.get("id"),id));
        return session.createQuery(criteria).uniqueResult();
    }

    Employee restrictByEmail(String email) {
        criteria.where(builder.equal(root.get("email"),email));
        return session.createQuery(criteria).uniqueResult();
    }


}


    @Override
    public Iterable<Employee> list(String departmentName) {
        List<Employee> list;
        Session session = factory.openSession();
        session.beginTransaction();
        list = new EmployeeCriteria(session).restrictByDepartmentName(departmentName);
        session.getTransaction().commit();
        return list;
    }

    @Override
    public Employee getById(long id) throws SQLException {
        Employee employee;
        Session session = factory.openSession();

                session.beginTransaction();
                employee = new EmployeeCriteria(session).restrictById(id);
                session.getTransaction().commit();


        return employee;
    }

    @Override
    public Employee getByEmail(String email) throws SQLException {
        Employee employee;
Session session = factory.openSession();

                session.beginTransaction();
                employee = new EmployeeCriteria(session).restrictByEmail(email);
                session.getTransaction().commit();

        return employee;
    }

    @Override
    public void add(Employee employee){

            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();
    }

    @Override
    public void delete(long id) {

            Session session = factory.getCurrentSession();
                session.beginTransaction();

                Employee employee = session.get(Employee.class, id);
                session.delete(employee);

                session.getTransaction().commit();



    }

    @Override
    public void update(long id, Employee newEmployee) {

            Session session = factory.getCurrentSession();
            session.beginTransaction();

                Employee employee = session.get(Employee.class, id);
                fillEmployee(employee, newEmployee);
                session.flush();

                session.getTransaction().commit();



    }

    private void fillEmployee(Employee employee, Employee newEmployee) {
    employee.setName(newEmployee.getName());
    employee.setEmail(newEmployee.getEmail());
    employee.setBirthday(newEmployee.getBirthday());
    employee.setRoom(newEmployee.getRoom());
    employee.setDepartmentName(newEmployee.getDepartmentName());
    }
}
