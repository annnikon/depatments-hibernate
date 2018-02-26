package com.aimprosoft.task1.handler.impl;

import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.service.DepartmentService;
import com.aimprosoft.task1.service.impl.DepartmentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DepartmentDeleteHandler extends AbstractHandler {

    private DepartmentService departmentService = new DepartmentServiceImpl();

    public DepartmentDeleteHandler() {
        setViewName(DEPARTMENT_LIST);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long id = Long.parseLong(request.getParameter("id"));
        try {
            departmentService.deleteDepartment(id);

        }
        catch (SQLException e) {
            request.setAttribute("status", "Cannot department because it has employees!");
            forward(request,response);
            return;

        }

        redirect(response, "departments?action=list");

    }
}
