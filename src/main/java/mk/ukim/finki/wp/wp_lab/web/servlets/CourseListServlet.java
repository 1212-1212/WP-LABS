package mk.ukim.finki.wp.wp_lab.web.servlets;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.service.CourseService;
import org.checkerframework.checker.nullness.Opt;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name="courseListServlet", urlPatterns = {"/listCourses", "/listCourses.html"})
public class CourseListServlet  extends HttpServlet {
    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;
    public CourseListServlet(CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("courses", courseService.findAll());
        springTemplateEngine.process("listCourses.html", webContext, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Course> course = courseService.findCourseById(Long.valueOf(req.getParameter("courseId")));

        course.ifPresent(val -> {
            req.getSession().setAttribute("selectedCourse", course.get());
            req.getSession().setAttribute("courseId", course.get().getCourseId());

        });
        courseService.findAll()
                        .stream()
                                .forEach(crs -> System.out.printf("%s, %s\n", crs.getName(), crs.getStudents().toString()));
        resp.sendRedirect("/listStudents");
    }
}
