package mk.ukim.finki.wp.wp_lab.model;

import lombok.Data;

@Data
public class Teacher {

    private Long id;
    private String name;
    private String username;

    public Teacher(String name, String username) {
        this.id = (long) (Math.random()*1000);
        this.name = name;
        this.username = username;
    }


}
