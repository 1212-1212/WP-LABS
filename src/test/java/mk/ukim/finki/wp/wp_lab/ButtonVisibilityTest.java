package mk.ukim.finki.wp.wp_lab;

import mk.ukim.finki.wp.wp_lab.model.Course;
import mk.ukim.finki.wp.wp_lab.model.Teacher;
import mk.ukim.finki.wp.wp_lab.model.TeacherFullName;
import mk.ukim.finki.wp.wp_lab.service.CourseService;
import mk.ukim.finki.wp.wp_lab.service.TeacherService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class ButtonVisibilityTest {

    @FindBy(tagName = "button")
    private List<WebElement> buttons;

    private WebDriver webDriver;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @BeforeEach
    public void setup()
    {
        webDriver = new HtmlUnitDriver(true);
        init();
    }
    public void init()
    {
        Teacher teacher = new Teacher(new TeacherFullName("name", "surname"), "username", LocalDate.now());
        teacherService.save(teacher);
        courseService.save(new Course("name", "description", null, teacher));
    }

    @Test
    public void testVisibility()
    {
        webDriver.get("http://localhost:9999/courses");
        System.out.println(webDriver.getCurrentUrl());
        Assert.assertTrue(webDriver.findElements(By.tagName("button")).size() == 1);

        webDriver.get("http://localhost:9999/login");

        WebElement submitButton = webDriver.findElement(By.tagName("button"));
        WebElement usernameField = webDriver.findElement(By.id("username"));
        WebElement passwordField = webDriver.findElement(By.id("password"));

        usernameField.sendKeys("admin");
        passwordField.sendKeys("admin");
        submitButton.click();
        webDriver.get("http://localhost:9999/courses");

        List<WebElement> editButtons = webDriver.findElements(By.className("edit"));
        List<WebElement> deleteButtons = webDriver.findElements(By.className("delete"));
        WebElement addButton = webDriver.findElement(By.className("add"));
        Assert.assertTrue(editButtons.size() == 1 && deleteButtons.size() == 1 && addButton.isDisplayed());


    }



}
