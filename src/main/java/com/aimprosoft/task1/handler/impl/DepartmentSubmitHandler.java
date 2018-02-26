package com.aimprosoft.task1.handler.impl;

import com.aimprosoft.task1.dto.DepartmentDto;
import com.aimprosoft.task1.dto.EmployeeDto;
import com.aimprosoft.task1.handler.AbstractHandler;
import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.service.impl.DepartmentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DepartmentSubmitHandler extends AbstractHandler {

    private DepartmentServiceImpl departmentService = new DepartmentServiceImpl();

    public DepartmentSubmitHandler() {
        setViewName(DEPARTMENT_EDIT);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        DepartmentDto dto = getDto(request);

        if (!dto.isValid()) {
            request.setAttribute("warning", "Please fill this fields correctly:");
            reject(request,response,dto);
            return;
        }

        if (!isNameUnique(dto.getId(), dto.getName())) {
            request.setAttribute("warning", "This department already exists: " + dto.getName());
            reject(request,response,dto);
            return;
        }

        Department department = convertDtoToInstance(dto);

        if (dto.getId()== 0) {
            departmentService.addDepartment(department);
        } else {
            departmentService.editDepartment(dto.getId(), department);
        }

        redirect(response, "departments?action=list");

    }

    private void reject(HttpServletRequest request, HttpServletResponse response, DepartmentDto dto) throws ServletException, IOException {
        request.setAttribute("errors", dto.getErrors());
        request.setAttribute("dto", dto);
        forward(request, response);
    }



    private boolean isNameUnique(long id, String newName) throws SQLException {
        if (id == 0) return departmentService.getByName(newName) == null;
        return departmentService.getById(id).getName().equals(newName) || departmentService.getByName(newName) == null;
    }

    private DepartmentDto getDto(HttpServletRequest request) {
        DepartmentDto dto = new DepartmentDto();
        long id;
        try {
            id = Long.parseLong(request.getParameter("id")); //update
        } catch (NumberFormatException | NullPointerException e) {
            id = 0; //create
        }

        dto.setId(id);
        dto.setName(request.getParameter("name"));
        dto.setInfo(request.getParameter("info"));

        return dto;
    }

    private Department convertDtoToInstance(DepartmentDto dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setInfo(dto.getInfo());
        return department;
    }




}
