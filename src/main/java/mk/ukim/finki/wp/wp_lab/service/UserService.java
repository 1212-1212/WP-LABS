package mk.ukim.finki.wp.wp_lab.service;

import mk.ukim.finki.wp.wp_lab.model.Role;
import mk.ukim.finki.wp.wp_lab.model.User;
import mk.ukim.finki.wp.wp_lab.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {



    Optional<User> findByUsername(String username);

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    List<User> findAll();

}
