package mk.ukim.finki.wp.wp_lab.web.filters;

import mk.ukim.finki.wp.wp_lab.model.Student;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter
public class SelectedStudentFilter  implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String selectedUsername = (String) request.getSession().getAttribute("username");
        Student selectedStudent = (Student) request.getSession().getAttribute("selectedStudent");
        String path = request.getServletPath();

        if((selectedUsername == null || selectedStudent == null) && "/studentEnrollmentSummary".equals(path))
        {
            response.sendRedirect("/listStudents");
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
