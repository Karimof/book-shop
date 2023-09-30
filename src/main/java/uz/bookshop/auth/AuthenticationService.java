package uz.bookshop.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.bookshop.config.JwtService;
import uz.bookshop.entity.Auth;
import uz.bookshop.entity.Author;
import uz.bookshop.entity.Users;
import uz.bookshop.entity.enums.Role;
import uz.bookshop.repository.AuthRepository;
import uz.bookshop.repository.AuthorRepository;
import uz.bookshop.repository.UserRepository;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthRepository authRepository;

    private final AuthorRepository authorRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        Role role = request.getRole();
        var auth = Auth.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdAt(new Date(System.currentTimeMillis()))
                .role(role)
                .build();
        authRepository.save(auth);
        saveUserOrAuthor(auth, request.getName());
        var jwtToken = jwtService.generatedToken(auth);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        var auth = authRepository.findByLogin(request.getLogin())
                .orElseThrow();
        var jwtToken = jwtService.generatedToken(auth);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserOrAuthor(Auth auth, String name) {
        if (auth.getRole().equals(Role.AUTHOR)) {
            var author = Author.builder()
                    .name(name)
                    .login(auth.getLogin())
                    .password(auth.getPassword())
                    .createdAt(auth.getCreatedAt())
                    .build();
            authorRepository.save(author);
        } else if (auth.getRole().equals(Role.USER)) {
            var user = Users.builder()
                    .name(name)
                    .login(auth.getLogin())
                    .password(auth.getPassword())
                    .createdAt(auth.getCreatedAt())
                    .build();
            userRepository.save(user);
        }
    }
}
