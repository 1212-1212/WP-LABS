package mk.ukim.finki.wp.wp_lab.web.controllers;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Teacher;
import mk.ukim.finki.wp.wp_lab.model.Type;
import mk.ukim.finki.wp.wp_lab.service.CourseService;
import mk.ukim.finki.wp.wp_lab.service.GradeService;
import mk.ukim.finki.wp.wp_lab.service.StudentService;
import mk.ukim.finki.wp.wp_lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    private final GradeService gradeService;
    private final StudentService studentService;

    public CourseController(CourseService courseService, TeacherService teacherService, GradeService gradeService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.gradeService = gradeService;
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

        if (Objects.nonNull(courseId)) {
            courseService.edit(courseName, courseDescription, teacherId, type, courseId);
        } else {
            courseService.save(courseName, courseDescription, teacherId, type);
        }
        return "redirect:/courses";
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteCourse(@PathVariable Long id) {
        Optional<Course> course = courseService.findCourseById(id);
        if(course.isPresent()) {
            gradeService.deleteAllByCourse(course.get());
            courseService.deleteCourseById(id);
        }
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
        Optional<Course> course = courseService.findCourseById(id);
        if (course.isPresent()) {
            List<Teacher> teachers = teacherService.findAll();
            model.addAttribute("teachers", teachers);
            model.addAttribute("course", course.get());
            model.addAttribute("types", Type.toListOfStrings());
            return "add-course";
        }

        return "redirect:/courses?error=IllegalArgument";
    }
}
