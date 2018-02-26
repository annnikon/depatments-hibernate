package com.aimprosoft.task1.handler.impl;

import com.aimprosoft.task1.dto.EmployeeDto;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.service.impl.EmployeeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

public class EmployeeEditHandler extends AbstractHandler {
    private final static String DATE_FORMAT = "yyy-MM-dd";

    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    public EmployeeEditHandler() {
        setViewName(EMPLOYEE_EDIT);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            EmployeeDto dto = convertInstanceToDto(employeeService.getById(id));
            request.setAttribute("dto", dto);
        }
        catch (NullPointerException | NumberFormatException e) {
            // just show add page
        }
        request.setAttribute("department", request.getParameter("department"));
        forward(request, response);
    }

    private EmployeeDto convertInstanceToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setEmail(employee.getEmail());
        dto.setName(employee.getName());
        dto.setRoom(String.valueOf(employee.getRoom()));
        dto.setBirthday(new SimpleDateFormat(DATE_FORMAT).format(employee.getBirthday()));
        dto.setDepartmentName(employee.getDepartmentName());
        return dto;
    }
}
