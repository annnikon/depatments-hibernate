package com.aimprosoft.task1.handler.impl;

import com.aimprosoft.task1.dto.DepartmentDto;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.service.impl.DepartmentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DepartmentEditHandler extends AbstractHandler {

    private DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    public DepartmentEditHandler() {
        setViewName(DEPARTMENT_EDIT);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
           long id = Long.parseLong(request.getParameter("id"));
           request.setAttribute("dto", convertInstanceToDto(departmentService.getById(id)));

        }
        catch (NumberFormatException | NullPointerException e) {
            // do nothing: new department will be added
        }

        forward(request, response);

    }

    private DepartmentDto convertInstanceToDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setInfo(department.getInfo());
        return dto;
    }
}
