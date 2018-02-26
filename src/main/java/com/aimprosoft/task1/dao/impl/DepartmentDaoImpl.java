package com.aimprosoft.task1.dao.impl;

import com.aimprosoft.task1.dao.DepartmentDao;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.utils.MysqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class DepartmentDaoImpl implements DepartmentDao, SqlStatements {


    @Override
    public Iterable<Department> list() throws SQLException {

        HashSet<Department> list = new HashSet<>();
        try (Connection connection = MysqlUtils.getConnection()){
             try (PreparedStatement preparedStatement = connection.prepareStatement(DEPARTMENTS_ALL_SQL)){
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet != null) {

                        while (resultSet.next()) {
                            Department department = fillDepartment(resultSet);
                            list.add(department);

                        }
                    }
                }


             }


        }

        return list;

    }



    @Override
    public void add(Department department) throws SQLException {

        try ( Connection connection = MysqlUtils.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DEPARTMENTS_INSERT_SQL)) {

                fillStatement(preparedStatement,department);
                preparedStatement.executeUpdate();


            }
        }

    }

    @Override
    public Department getByName(String name) throws SQLException {
            Department department = null;
            try (Connection connection = MysqlUtils.getConnection()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(DEPARTMENTS_ALL_SQL+NAME_CONDITION)) {

                    preparedStatement.setString(1, name);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {

                        if (resultSet != null) {

                            if (resultSet.next()) {

                               department = fillDepartment(resultSet);

                            }
                        }
                    }


                }
            }


            return department;

        }

    @Override
    public Department getById(long id) throws SQLException {
        Department department = null;
        try  (Connection  connection = MysqlUtils.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DEPARTMENTS_ALL_SQL+ID_CONDITION)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    if (resultSet != null) {

                        if (resultSet.next()) {
                            department = fillDepartment(resultSet);

                        }
                    }

                }
                  

            }
        }

        return department;
    }

    @Override
    public void delete(long id) throws SQLException {
       try (Connection connection = MysqlUtils.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DEPARTMENTS_DELETE_SQL+ID_CONDITION)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();


            }
        }


    }

    @Override
    public void update(long id, Department department) throws SQLException {

        try (Connection connection = MysqlUtils.getConnection()){
           try (PreparedStatement preparedStatement = connection.prepareStatement(DEPARTMENTS_UPDATE_SQL+ID_CONDITION)) {
                fillStatement(preparedStatement,department);
                preparedStatement.setLong(3, id);
                preparedStatement.executeUpdate();
                

            }
        }


    }

    private void fillStatement(PreparedStatement preparedStatement, Department department) throws SQLException{
        preparedStatement.setString(1, department.getName());
        preparedStatement.setString(2, department.getInfo());
    }

    private Department fillDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getLong("id"));
        department.setName(resultSet.getString("name"));
        department.setInfo(resultSet.getString("info"));
        return department;
    }


}
