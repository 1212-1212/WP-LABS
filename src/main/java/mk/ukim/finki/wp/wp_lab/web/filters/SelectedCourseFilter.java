package mk.ukim.finki.wp.wp_lab.web.filters;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.service.CourseService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebFilter
public class SelectedCourseFilter implements Filter {
    private final CourseService courseService;
    private final List<String> allowedMappings = List.of("/courses", "/listCourses", "/courses/add-form", "/courses/add", "/courses/delete", "/courses/edit-form");
    private final List<String> courseMustBeSelectedMappings = List.of("/listStudents", "/createStudent", "/studentEnrollmentSummary");

    public SelectedCourseFilter(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String selectedCourseId = (String) request.getSession().getAttribute("courseId");
        String path = request.getServletPath();
        List<Long> validIdsOfCourses = courseService.validIdsOfCourses();
        System.out.println(path);
        int lastIndex = path.lastIndexOf("/");
        boolean isValid = false;
        if (Character.isDigit(path.charAt(lastIndex + 1))) {
            Long id = Long.valueOf(path.split("/")[path.split("/").length - 1]);
            isValid = !validIdsOfCourses.contains(id);
        }
        if (selectedCourseId == null && courseMustBeSelectedMappings.contains(path) || isValid) {
            response.sendRedirect("/courses");
        } else {
            filterChain.doFilter(request, response);
        }


    }

    @Override
    public void destroy() {

    }
}
