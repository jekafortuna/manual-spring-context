package com.bobocode.servlet;

import com.bobocode.client.NameProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/name")
public class NameServlet extends HttpServlet {
    private static final String SPRING_APP_CONTEXT = "SPRING_APP_CONTEXT";

    @Override
    public void init(ServletConfig config) throws ServletException {
        AnnotationConfigApplicationContext springContext = new AnnotationConfigApplicationContext(NameProvider.class);
        ServletContext servletContext = config.getServletContext();
        servletContext.setAttribute(SPRING_APP_CONTEXT, springContext);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var springContext = req.getServletContext().getAttribute(SPRING_APP_CONTEXT);
        NameProvider nameProvider = ((ApplicationContext) springContext).getBean(NameProvider.class);
        PrintWriter writer = resp.getWriter();
        writer.println(nameProvider.getName());
    }
}
