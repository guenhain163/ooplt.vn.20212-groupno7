package com.example.be.controller.auth;

import com.example.be.jwt.JwtTokenFilter;
import com.example.be.jwt.JwtTokenUtil;
import com.example.be.model.Users;
import com.example.be.request.AuthRequest;
import com.example.be.response.AuthResponse;
import com.example.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/api/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            Users user = (Users) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            Long expiresIn = jwtUtil.getExpiresIn(accessToken);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken, expiresIn);

            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/api/auth/user")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String header) {
        try {
            String token = header.substring(7);
            int userId = jwtUtil.getId(token);
            Optional<Users> userOptional = userService.findById(userId);
            Map<String, Object> userResponse = new HashMap<>();
            userResponse.put("email", userOptional.get().getEmail());
            userResponse.put("role", userOptional.get().getRole());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Successfully retrieved user information.");
            response.put("user", userResponse);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
