package com.aimprosoft.task1.handler.impl;

import com.aimprosoft.task1.dto.DepartmentDto;
import com.aimprosoft.task1.dto.EmployeeDto;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ServiceConfigurationError;

public class EmployeeSubmitHandler extends AbstractHandler {

    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    public EmployeeSubmitHandler() {
        setViewName(EMPLOYEE_EDIT);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        EmployeeDto dto = getDto(request);

        if (!dto.isValid()) {
            request.setAttribute("warning", "Please fill this fields correctly:");
            reject(request, response, dto);
            return;
        }

        if (!isEmailUnique(dto.getId(),dto.getEmail())) {
            request.setAttribute("warning", "Email already belongs to another employee:"+dto.getEmail());
            reject(request,response,dto);
            return;
        }

        try {
            if (dto.getId() == 0) employeeService.addEmployee(convertDtoToInstance(dto));
            else employeeService.editEmployee(dto.getId(), convertDtoToInstance(dto));

        } catch (SQLException e) {
            request.setAttribute("warning", "No such department: " + dto.getDepartmentName());
            reject(request,response,dto);
            return;

        }
        redirect(response, "employees?action=list&department=" + dto.getDepartmentName());

    }

    private void reject(HttpServletRequest request, HttpServletResponse response, EmployeeDto dto) throws ServletException, IOException {
        request.setAttribute("errors", dto.getErrors());
        request.setAttribute("dto", dto);
        forward(request, response);
    }

    private Employee convertDtoToInstance(EmployeeDto dto) {
        if (!dto.isValid()) return null;
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setEmail(dto.getEmail());
        employee.setName(dto.getName());
        employee.setRoom(Integer.parseInt(dto.getRoom()));
        try {
            employee.setBirthday(new SimpleDateFormat(EmployeeDto.DATE_FORMAT).parse(dto.getBirthday()));
        }
        catch (ParseException ignored) { /*will not appear because dto is valid*/}
        employee.setDepartmentName(dto.getDepartmentName());
        return employee;
    }

    private EmployeeDto getDto(HttpServletRequest request) {
        EmployeeDto dto = new EmployeeDto();
        long id;
        try {
            id = Long.parseLong(request.getParameter("id")); //edit existing
        }
        catch (NullPointerException | NumberFormatException e) {
            id = 0; //add employee
        }

        dto.setId(id);
        dto.setEmail(request.getParameter("email"));
        dto.setName(request.getParameter("name"));
        dto.setBirthday(request.getParameter("birthday"));
        dto.setRoom(request.getParameter("room"));
        dto.setDepartmentName(request.getParameter("departmentName"));
        return dto;
    }

    private boolean isEmailUnique(long id, String newEmail) throws SQLException{

        if (id==0) return employeeService.getByEmail(newEmail) == null;
        return employeeService.getById(id).getEmail().equals(newEmail) || employeeService.getByEmail(newEmail) == null;
    }

}
