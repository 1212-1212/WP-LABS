package mk.ukim.finki.wp.wp_lab.config;

import mk.ukim.finki.wp.wp_lab.service.UserService;
import org.openqa.selenium.InvalidArgumentException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public CustomUsernamePasswordAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if(username.isBlank() || username.isEmpty())
            throw new InvalidArgumentException(username);
        if(password.isEmpty() || password.isBlank())
            throw new InvalidArgumentException(password);

        UserDetails userDetails = userService.loadUserByUsername(username);

        if(!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new InvalidArgumentException("Incorrect password!");

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
