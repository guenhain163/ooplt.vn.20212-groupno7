package exam.management.be.service;

import exam.management.be.model.Users;
import exam.management.be.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService, BaseService<Users> {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    @Override
    public Iterable<Users> findAll() {
        return null;
    }

    @Override
    public Optional<Users> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Users save(Users users) {
        return userRepository.save(users);
    }

    @Override
    public Users update(Users users) {
        return userRepository.saveAndFlush(users);
    }

    @Override
    public void remove(Integer id) {
        userRepository.deleteById(id);
    }

    public Map<String, Object> me(Integer id) {
        Optional<Users> userOptional = findById(id);
        Map<String, Object> userResponse = new HashMap<>();
        userResponse.put("email", userOptional.get().getEmail());
        userResponse.put("role", userOptional.get().getRole());
        userResponse.put("id", userOptional.get().getId());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully retrieved user information.");
        response.put("user", userResponse);

        return response;
    }

    public Users createDefault(String email) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordDefault = "12345678";
        String encodedPassword = passwordEncoder.encode(passwordDefault);

        Users newUser = new Users(email, encodedPassword);
        return save(newUser);
    }

    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
