package com.aimprosoft.task1.handler.impl;

import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.service.impl.DepartmentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DepartmentListHandler extends AbstractHandler {

    private DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    public DepartmentListHandler() {
        setViewName(DEPARTMENT_LIST);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("departments", departmentService.listDepartments());
        forward(request,response);
    }
}
