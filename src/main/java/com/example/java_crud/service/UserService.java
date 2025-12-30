package com.example.java_crud.service;

import com.example.java_crud.model.User;
import com.example.java_crud.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.java_crud.Exception.CustomException;
import com.example.java_crud.dtos.UserDto.LoginResponse;
import java.util.List;
import com.example.java_crud.dtos.UserDto.ProfileResponse;
import org.springframework.http.HttpStatus;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {

        var isEmailExists = userRepository.findByEmail(user.getEmail());

        var name = user.getName();
        var email = user.getEmail();
        var password = user.getPassword();
        var role = user.getRole();

        // Validate name
        if (name == null || name.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Name is required");
        }

        // Validate email
        if (email == null || email.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Email is required");
        }
        if (!email.contains("@")) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Invalid email format");
        }

        // Validate password
        if (password == null || password.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Password is required");
        }
        if (password.length() < 6) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Password must be at least 6 characters long");
        }

        // Validate role
        if (role == null || role.isEmpty()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Role is required");
        }

        if (isEmailExists != null) {
            throw new CustomException(HttpStatus.CONFLICT, "User already exists in the database");
        }

        // Save user if all validations pass
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }

    // login user

    public LoginResponse loginUser(String email, String password) {

        // Find user by email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid email or password");
        }

        // Return response if match
        return new LoginResponse(user.getName(), user.getEmail(), "Login successful");
    }

    // api function to get all the details by teh user id

    public ProfileResponse GetUserProfile(Long id) {
        User user = userRepository.findById((long) id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: "));

        return new ProfileResponse(
                user.getName(),
                user.getEmail(),
                user.getRole());
    }

}
