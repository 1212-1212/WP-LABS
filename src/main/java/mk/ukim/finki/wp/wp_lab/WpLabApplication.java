package mk.ukim.finki.wp.wp_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class WpLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(WpLabApplication.class, args);
    }

}
