package mk.ukim.finki.wp.wp_lab.service.impl;

import mk.ukim.finki.wp.wp_lab.model.Role;
import mk.ukim.finki.wp.wp_lab.model.User;
import mk.ukim.finki.wp.wp_lab.repository.UserRepository;
import mk.ukim.finki.wp.wp_lab.service.UserService;
import org.openqa.selenium.InvalidArgumentException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    private boolean checkCredentials(String username, String password)
    {
        return (!Objects.isNull(password) && !password.isEmpty() && !password.isBlank()) &&
                (!Objects.isNull(username) && !username.isEmpty() && !username.isBlank());
    }

    @Override
    @Transactional
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {

        if(!checkCredentials(username, password))
            throw new InvalidArgumentException(username);
        if(findByUsername(username).isPresent())
            throw  new InvalidArgumentException(username);
        if(!password.equals(repeatPassword))
            throw new InvalidArgumentException(password);

        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if(Objects.isNull(username) || username.isEmpty() || username.isBlank())
            throw new InvalidArgumentException(username);
        if(Objects.isNull(password) || password.isEmpty() || password.isBlank())
            throw new InvalidArgumentException(username);
        if(findByUsername(username).isPresent())
            throw  new InvalidArgumentException(username);

        return userRepository.findByUsername(username).orElseThrow(() -> new InvalidArgumentException(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
