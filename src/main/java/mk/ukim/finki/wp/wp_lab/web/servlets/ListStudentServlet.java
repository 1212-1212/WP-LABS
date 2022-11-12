package mk.ukim.finki.wp.wp_lab.web.servlets;

import mk.ukim.finki.wp.wp_lab.exceptions.NoStudentSelectedException;
import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Student;
import mk.ukim.finki.wp.wp_lab.service.CourseService;
import mk.ukim.finki.wp.wp_lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name="listStudentServlet", urlPatterns = {"/listStudents", "/listStudents.html"})
public class ListStudentServlet extends HttpServlet {

    private final StudentService studentService;
    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;

    public ListStudentServlet(StudentService studentService, CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.studentService=studentService;
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String username = req.getParameter("username");
        req.getSession().setAttribute("username", username);
        Optional<Student> student = Optional.empty();
        try {
            student  = studentService.findStudentByUsername((String) req.getSession().getAttribute("username"));
            student.ifPresent(s -> req.getSession().setAttribute("selectedStudent", s));
        } catch (NoStudentSelectedException e) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("hasError", true);
            context.setVariable("error", e.getMessage());
            context.setVariable("students", studentService.listAll());
            springTemplateEngine.process("listStudents.html", context, resp.getWriter());
        }
        System.out.println(student);
        if(student.isPresent()) {
            resp.sendRedirect("/studentEnrollmentSummary");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("students", studentService.listAll());
        springTemplateEngine.process("listStudents.html",webContext,resp.getWriter());
    }
}
