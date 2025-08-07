package in.myedbiz.controller;

import in.myedbiz.config.jwt.JwtService;
import in.myedbiz.dto.AuthRequest;
import in.myedbiz.entity.Users;
import in.myedbiz.repository.UserRepository;
import in.myedbiz.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Util util;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AuthRequest request) {
        System.out.println(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        Users user = userRepo.findByEmail(request.getEmail()).get();
        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        user.getEmail(), user.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
                )
        );

        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/test")
    public ResponseEntity<?> getEnrollmentNO(){
        return ResponseEntity.ok(util.generateEnrollmentNo("MCA"));
    }

    @GetMapping("/test-unique-id")
    public ResponseEntity<?> getUniqueId(){
        return ResponseEntity.ok(util.generateUniqueId("ABHAY"));
    }

}
