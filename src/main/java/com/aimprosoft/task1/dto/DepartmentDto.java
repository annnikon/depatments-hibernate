package com.aimprosoft.task1.dto;

import com.aimprosoft.task1.model.Department;
import com.aimprosoft.task1.model.Employee;
import com.aimprosoft.task1.utils.ValidationUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

public class DepartmentDto {

    private long id;
    private String name, info;

    private Map<String, String> errors = new LinkedHashMap<>();

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
        if (name == null || name.isEmpty()) {
            errors.put("Name", "Cannot be empty");
        }
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", errors=" + errors +
                ", is Valid=" + isValid() +
                '}';
    }
}
