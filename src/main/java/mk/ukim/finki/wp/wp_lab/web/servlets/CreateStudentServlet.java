package mk.ukim.finki.wp.wp_lab.web.servlets;

import mk.ukim.finki.wp.wp_lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateStudentServlet", urlPatterns = {"/createStudent", "/createStudent.html"})
public class CreateStudentServlet extends HttpServlet {


    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    public CreateStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService) {

        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        studentService.save(username, password, name, surname);
        System.out.println(studentService.listAll());
        resp.sendRedirect("/listStudents");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        springTemplateEngine.process("createStudent.html", webContext, resp.getWriter());

    }


}

