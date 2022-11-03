package mk.ukim.finki.wp.wp_lab.web.filters;

import mk.ukim.finki.wp.wp_lab.model.Course;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class SelectedCourseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String selectedCourseId = (String) request.getSession().getAttribute("courseId");
        String path = request.getServletPath();

        if(selectedCourseId == null && !"/listCourses".equals(path))
        {
            response.sendRedirect("/listCourses");
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }


     }

    @Override
    public void destroy() {

    }
}
