package mk.ukim.finki.wp.wp_lab.web.controllers;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Student;
import mk.ukim.finki.wp.wp_lab.model.Teacher;
import mk.ukim.finki.wp.wp_lab.model.Type;
import mk.ukim.finki.wp.wp_lab.service.CourseService;
import mk.ukim.finki.wp.wp_lab.service.StudentService;
import mk.ukim.finki.wp.wp_lab.service.TeacherService;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    private final StudentService studentService;

    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping
    public String getCoursePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        TreeSet<Course> courses = new TreeSet<>(courseService.findAll());
        model.addAttribute("courses", courses);
        return "listCourses";
    }

    @PostMapping("/add")
    public String save(@RequestParam String courseName,
                       @RequestParam String courseDescription,
                       @RequestParam Type type,
                       @RequestParam Long teacherId,
                       @RequestParam(required = false) Long courseId) {

        if (!Objects.isNull(courseId)) {
            courseService.edit(courseName, courseDescription, teacherId, type, courseId);
        } else {
            courseService.save(courseName, courseDescription, teacherId, type);
        }
        return "redirect:/courses";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

    @GetMapping("/add-form")
    public String getCourseFormPage(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("types", Type.toListOfStrings());
        return "add-course";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {
        if (courseService.findCourseById(id).isPresent()) {
            Course course = courseService.findCourseById(id).get();
            List<Teacher> teachers = teacherService.findAll();
            model.addAttribute("teachers", teachers);
            model.addAttribute("course", course);
            model.addAttribute("types", Type.toListOfStrings());
            return "add-course";
        }
        return "redirect:/courses?error=IllegalArgument";
    }
}
