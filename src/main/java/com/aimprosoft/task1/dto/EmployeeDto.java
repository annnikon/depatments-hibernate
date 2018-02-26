package com.aimprosoft.task1.dto;

import com.aimprosoft.task1.model.Employee;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class EmployeeDto {

    public final static String EMAIL_REGEXP = "[\\w\\d_.]+[@]([\\w]+[\\.][\\w]+)+";
    public final static String NAME_REGEXP = "[\\p{L}\\s]+";
    public final static String DATE_FORMAT = "yyy-MM-dd";


    private long id;
    private String email, name, birthday, room, departmentName;
    private Map<String, String> errors = new LinkedHashMap<>();

    public void setId(long id) {
        this.id = id;
    }

   public void setEmail(String email) {
        this.email = email;
        if (!Pattern.compile(EMAIL_REGEXP).matcher(email).matches()) {
            errors.put("Email", "May contain letters, _, digits, @ and .");
        }
    }

    public void setName(String name) {
        this.name = name;
        if (!Pattern.compile(NAME_REGEXP).matcher(name).matches()) {
            errors.put("Name", "May contain any letters in uppercase or lowercase, and spaces");
        }
    }

    public void setBirthday(String birthday) {

        this.birthday = birthday;
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            format.parse(birthday);
        } catch (ParseException | NullPointerException e) {
            errors.put("Birthday", "Should be in format: " + DATE_FORMAT);
        }
    }

    public void setRoom(String room) {
        this.room = room;
        try {
            Integer.parseInt(room);
        } catch (NumberFormatException | NullPointerException e) {
            errors.put("Room", "Should be an integer number");
        }
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
        if (departmentName == null || departmentName.isEmpty()) {
            errors.put("Department Name", "Please specify department for employee!");
        }
    }

    public long getId() {
        return id;
    }


    public boolean isValid() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getRoom() {
        return room;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                " email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", room='" + room + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", is Valid=" + isValid() +
                '}';
    }


}
