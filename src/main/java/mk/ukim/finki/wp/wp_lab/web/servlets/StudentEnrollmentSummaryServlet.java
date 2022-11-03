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

@WebServlet(name = "studentEnrollmentSummaryServlet", urlPatterns = {"/studentEnrollmentSummary", "/studentEnrollmentSummary.html"})
public class StudentEnrollmentSummaryServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    private final StudentService studentService;

    public StudentEnrollmentSummaryServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.studentService = studentService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        Course course = courseService.findCourseById(Long.valueOf((String) req.getSession().getAttribute("courseId")));
        req.getSession().setAttribute("selectedCourse", course);
        Student student = (Student) req.getSession().getAttribute("selectedStudent");
        if (!course.getStudents().contains(student)) {
            courseService.addStudentInCourse(student.getUsername(), course.getCourseId());
        }
        springTemplateEngine.process("studentEnrollmentSummary.html", webContext, resp.getWriter());
    }
}
