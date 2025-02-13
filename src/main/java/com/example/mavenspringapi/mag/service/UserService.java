package com.example.mavenspringapi.mag.service;

import com.example.mavenspringapi.mag.controller.dto.SignupRequest;
import com.example.mavenspringapi.mag.exception.DuplicateException;
import com.example.mavenspringapi.mag.model.user.User;
import com.example.mavenspringapi.mag.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public void signup(SignupRequest request) {
    String email = request.email();
    Optional<User> existingUser = repository.findByEmail(email);
    if (existingUser.isPresent()) {
      throw new DuplicateException(String.format("User with the email address '%s' already exists.", email));
    }

    String hashedPassword = passwordEncoder.encode(request.password());
    User user = new User(request.name(), email, hashedPassword);
    repository.add(user);
  }

}
