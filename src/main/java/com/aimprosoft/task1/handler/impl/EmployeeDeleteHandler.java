package com.aimprosoft.task1.handler.impl;

import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.service.impl.EmployeeServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeDeleteHandler extends AbstractHandler {

    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long id = Long.parseLong(request.getParameter("id"));
        String department = request.getParameter("department");
        employeeService.deleteEmployee(id);
        redirect(response, "employees?action=list&department=" + department);
    }
}
