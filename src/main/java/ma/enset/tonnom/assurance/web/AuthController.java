package ma.enset.tonnom.assurance.web;

import lombok.*;
import lombok.RequiredArgsConstructor;
import ma.enset.tonnom.assurance.security.JwtService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Data
    static class LoginRequest {
        private String username;
        private String password;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword())
        );
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        return jwtService.generateToken(user);
    }
}