package com.aimprosoft.task1.dao.hibernateimpl;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.model.Department;

import com.aimprosoft.task1.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class HibernateDepartmentDaoImpl implements DepartmentDao {

    private static SessionFactory factory = HibernateUtils.getSessionFactory();

    class DepartmentCriteria {

        Session session;
        CriteriaBuilder builder;
        CriteriaQuery<Department> criteria;
        Root<Department> root;

        public DepartmentCriteria(Session session) {

            this.session = session;
            builder = session.getCriteriaBuilder();
            criteria = builder.createQuery(Department.class);
            root = criteria.from(Department.class);
            criteria.select(root);

        }

        List<Department> getAllOrdered() {
            criteria.orderBy(builder.asc(root.get("name")));
            return session.createQuery(criteria).getResultList();

        }

        Department restrictById(long id) {
            criteria.where(builder.equal(root.get("id"),id));
            return session.createQuery(criteria).uniqueResult();
        }

        Department restrictByName(String name) {
            criteria.where(builder.equal(root.get("name"),name));
            return session.createQuery(criteria).uniqueResult();
        }


    }



    @Override

    public Iterable<Department> list() {
        List<Department> list;
Session session = factory.openSession();
                session.beginTransaction();
                list = new DepartmentCriteria(session).getAllOrdered();
                session.getTransaction().commit();


        return list;
    }

    @Override
    public Department getById(long id) {
        Department department = null;

            Session session = factory.getCurrentSession();

                session.beginTransaction();
                department = new DepartmentCriteria(session).restrictById(id);
                session.getTransaction().commit();



        return department;
    }

    @Override
    public Department getByName(String name) {
        Department department = null;

            Session session = factory.getCurrentSession();
                session.beginTransaction();
                department = new DepartmentCriteria(session).restrictByName(name);
                session.getTransaction().commit();



        return department;
    }

    @Override
    public void add(Department department)  {

            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.persist(department);
            session.getTransaction().commit();

    }

    @Override
    public void delete(long id) {

            Session session = factory.getCurrentSession();
                session.beginTransaction();

                Department department = session.get(Department.class, id);
                session.delete(department);

                session.getTransaction().commit();
    }

    @Override
    public void update(long id, Department newDepartment) {

            Session session = factory.getCurrentSession();
                session.beginTransaction();

                Department department = session.get(Department.class, id);
                fillDepartent(department, newDepartment);
                session.flush();

                session.getTransaction().commit();




    }

    private void fillDepartent(Department department, Department newDepartment) {
        department.setName(newDepartment.getName());
        department.setInfo(newDepartment.getInfo());
    }
}
