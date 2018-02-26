package com.aimprosoft.task1.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractHandler implements Handler {

    protected String viewName;


    protected void setViewName(String path) {
        this.viewName=path;

    }

    public final void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            handleInternal(request, response);
        } catch (Exception e) {

            request.setAttribute("error", e.getMessage() );
            request.setAttribute("stackTrace", e.getStackTrace());
            setViewName(ERROR_VIEW);
            forward(request, response);
        }
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(viewName).forward(request, response);
    }

    protected void redirect(HttpServletResponse response, String where) throws IOException {
        response.sendRedirect(where);
    }

    abstract public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
