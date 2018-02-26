package com.aimprosoft.task1.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Handler extends ViewNames{
    void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
