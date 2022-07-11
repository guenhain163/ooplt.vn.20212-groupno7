package exam.management.be.controller.auth;

import exam.management.be.jwt.JwtTokenUtil;
import exam.management.be.model.Users;
import exam.management.be.request.AuthRequest;
import exam.management.be.response.AuthResponse;
import exam.management.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

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
            Map<String, Object> me = userService.me(jwtUtil.getId(accessToken));
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken, expiresIn, me.get("user"));

            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/api/auth/user")
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String header) {
        try {
            String token = header.split(" ")[1].trim();
            int userId = jwtUtil.getId(token);

            return new ResponseEntity<>(userService.me(userId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/auth/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String header) {
        try {
            String token = header.split(" ")[1].trim();

            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
