package mk.ukim.finki.wp.wp_lab.web.servlets;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Grade;
import mk.ukim.finki.wp.wp_lab.model.Student;
import mk.ukim.finki.wp.wp_lab.service.CourseService;
import mk.ukim.finki.wp.wp_lab.service.GradeService;
import mk.ukim.finki.wp.wp_lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "studentEnrollmentSummaryServlet", urlPatterns = {"/studentEnrollmentSummary", "/studentEnrollmentSummary.html"})
public class StudentEnrollmentSummaryServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    private final GradeService gradeService;

    private final StudentService studentService;

    public StudentEnrollmentSummaryServlet(SpringTemplateEngine springTemplateEngine, CourseService courseService, GradeService gradeService, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
        this.gradeService = gradeService;
        this.studentService = studentService;
    }


    @Override
    @Transactional

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        Course course = (Course) req.getSession().getAttribute("selectedCourse");
        Student student = (Student) req.getSession().getAttribute("selectedStudent");
        Optional<Course> optional = courseService.addStudentInCourse(student, course);
        if(optional.isPresent()) {
            Grade grade = new Grade('F', student, course, LocalDateTime.now());
            gradeService.save(grade);
        }
        Map<Student, Grade> map = new HashMap<>(gradeService.getGradeMapForCourse(course));
        req.getSession().setAttribute("map", map);
        System.out.println(map);
        springTemplateEngine.process("studentEnrollmentSummary.html", webContext, resp.getWriter());
    }
}
