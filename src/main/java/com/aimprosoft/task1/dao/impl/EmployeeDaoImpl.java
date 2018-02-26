package com.aimprosoft.task1.dao.impl;

import com.aimprosoft.task1.dao.EmployeeDao;
import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.utils.MysqlUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class EmployeeDaoImpl implements EmployeeDao, SqlStatements {


    @Override
    public Iterable<Employee> list(String departmentName) throws SQLException {
        HashSet<Employee> list = new HashSet<>();
          try (Connection connection = MysqlUtils.getConnection()) {
              try (PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEES_ALL_SQL+DEPARTMENT_CONDITION)) {

                  preparedStatement.setString(1, departmentName);
                  try (ResultSet resultSet = preparedStatement.executeQuery()) {
                      if (resultSet != null) {

                          while (resultSet.next()) {

                              list.add(fillEmployee(resultSet));

                          }
                      }

                  }


              }
          }

        return list;

    }



    @Override
    public void add(Employee employee) throws SQLException {


        try (Connection connection = MysqlUtils.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEES_INSERT_SQL)) {

               fillStatement(preparedStatement,employee);
               preparedStatement.executeUpdate();

            }
        }


    }

    @Override
    public Employee getById(long id) throws SQLException {

        Employee employee = null;

        try (Connection connection = MysqlUtils.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEES_ALL_SQL + ID_CONDITION)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    if (resultSet != null) {

                        if (resultSet.next()) {
                            employee = fillEmployee(resultSet);
                        }

                    }

                }
            }
        }

        return employee;

    }


    public void update(long id, Employee newEmployee) throws SQLException {

        try (Connection connection = MysqlUtils.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEES_UPDATE_SQL+ID_CONDITION)) {
                fillStatement(preparedStatement,newEmployee);
                preparedStatement.setLong(6, id);
                preparedStatement.executeUpdate();

            }


        }
    }



    @Override
    public Employee getByEmail(String email) throws SQLException {

        Employee employee = null;

        try ( Connection connection = MysqlUtils.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEES_ALL_SQL + EMAIL_CONDITION)) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    if (resultSet != null) {

                        if (resultSet.next()) {
                            employee = fillEmployee(resultSet);
                        }


                    }
                }


            }

        }

        return employee;

    }

    public void delete(long id) throws SQLException {

        try ( Connection connection = MysqlUtils.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(EMPLOYEES_DELETE_SQL+ID_CONDITION)) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();


            }


        }


    }


    private Employee fillEmployee(ResultSet resultSet) throws SQLException{
        Employee employee = new Employee();
        employee.setId(resultSet.getLong("id"));
        employee.setEmail(resultSet.getString("email"));
        employee.setName(resultSet.getString("name"));
        employee.setBirthday(resultSet.getDate("birthday"));
        employee.setRoom(resultSet.getInt("room"));
        employee.setDepartmentName(resultSet.getString("department"));
        return employee;
    }

    private void fillStatement(PreparedStatement preparedStatement, Employee newEmployee) throws SQLException {
        preparedStatement.setString(1, newEmployee.getEmail());
        preparedStatement.setString(2, newEmployee.getName());
        java.sql.Date currentDate = new java.sql.Date(newEmployee.getBirthday().getTime());
        preparedStatement.setString(3, currentDate.toString());
        preparedStatement.setInt(4, newEmployee.getRoom());
        preparedStatement.setString(5, newEmployee.getDepartmentName());
    }


}
