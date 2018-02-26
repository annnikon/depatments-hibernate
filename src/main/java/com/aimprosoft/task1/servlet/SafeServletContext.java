package com.aimprosoft.task1.servlet;


import com.aimprosoft.task1.handler.*;
import com.aimprosoft.task1.handler.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SafeServletContext implements MappingKeys {

    private static Map<String, Handler> handlers = new ConcurrentHashMap<>();

    private static String DEFAULT = DEPARTMENTS+LIST;
    static {

        handlers.put(DEPARTMENTS+EDIT, new DepartmentEditHandler());
        handlers.put(DEPARTMENTS+DELETE, new DepartmentDeleteHandler());
        handlers.put(DEPARTMENTS+LIST, new DepartmentListHandler());
        handlers.put(DEPARTMENTS+SUBMIT, new DepartmentSubmitHandler());

        handlers.put(EMPLOYEES+EDIT, new EmployeeEditHandler());
        handlers.put(EMPLOYEES+DELETE, new EmployeeDeleteHandler());
        handlers.put(EMPLOYEES+LIST, new EmployeeListHandler());
        handlers.put(EMPLOYEES+SUBMIT, new EmployeeSubmitHandler());

    }


    public static Handler resolve(HttpServletRequest request) {
        String action = request.getParameter("action");

        String key = request.getServletPath()+ SEPARATOR + action;

        return handlers.getOrDefault(key, handlers.get(DEFAULT));
    }


}
