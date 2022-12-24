package mk.ukim.finki.wp.wp_lab.web.controllers;

import mk.ukim.finki.wp.wp_lab.exceptions.NoStudentSelectedException;
import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Grade;
import mk.ukim.finki.wp.wp_lab.model.Student;
import mk.ukim.finki.wp.wp_lab.service.CourseService;
import mk.ukim.finki.wp.wp_lab.service.GradeService;
import mk.ukim.finki.wp.wp_lab.service.StudentService;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;
    private final CourseService courseService;
    private final StudentService studentService;

    public GradeController(GradeService gradeService, CourseService courseService, StudentService studentService) {
        this.gradeService = gradeService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping
    private String getPage(Model model, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        model.addAttribute("format", "dd-MM-yyyy HH:mm:ss");
        model.addAttribute("grades", gradeService.findAllByTimestampBetween(from, to));

        return "listGrades";
    }

    @GetMapping("/add-grade")
    private String getAddGradePage(Model model) {
        model.addAttribute("grades", Grade.getDomain());
        model.addAttribute("courses", courseService.findAll());
        model.addAttribute("students", studentService.findAll());
        return "add-grade";
    }

    @PostMapping("/add-grade")
    private String addGrade(@RequestParam Character grade,
                            @RequestParam String studentUsername,
                            @RequestParam String courseName,
                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) throws NoStudentSelectedException {
        Optional<Student> student = studentService.findStudentByUsername(studentUsername);
        Optional<Course> course = courseService.findCourseByName(courseName);
        if (course.isPresent() && student.isPresent()) {
            if (course.get().getStudents().contains(student.get())) {
                if (gradeService.findGradeByStudentAndCourse(student.get(), course.get()).isPresent()) {
                    gradeService.edit(gradeService.findGradeByStudentAndCourse(student.get(), course.get()).get(), grade, student.get(), course.get(), dateTime);

                } else {
                    gradeService.save(new Grade(grade, student.get(), course.get(), dateTime));
                }
            }
        }
        return "redirect:/grades";
    }

}
