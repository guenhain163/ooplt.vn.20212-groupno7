package com.example.be.controller.admin;

import com.example.be.exceptions.ResourceNotFoundException;
import com.example.be.model.Users;
import com.example.be.request.UserRequest;
import com.example.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/admin/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Integer id) {
        Optional<Users> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @PostMapping
    public ResponseEntity<Users> createNewUser(@Valid @RequestBody UserRequest user) {
        try {
            if (!Objects.equals(user.getPassword(), user.getConfirmPassword())) {
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }

            return new ResponseEntity<>(userService.save(new Users(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole()
            )), HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
