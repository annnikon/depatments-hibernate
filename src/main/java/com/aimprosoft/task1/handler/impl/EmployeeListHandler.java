package com.aimprosoft.task1.handler.impl;

import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.service.impl.EmployeeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeListHandler extends AbstractHandler {
    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    public EmployeeListHandler() {
        setViewName(EMPLOYEE_LIST);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("employees", employeeService.listEmployees(request.getParameter("department")));
        forward(request, response);

    }
}
