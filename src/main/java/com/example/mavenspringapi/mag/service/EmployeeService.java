package com.example.mavenspringapi.mag.service;

import com.example.mavenspringapi.mag.repo.EmployeeRepository;
import com.example.mavenspringapi.mag.controller.dto.EmployeeRequest;
import com.example.mavenspringapi.mag.exception.DuplicateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmployeeService {


    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Optional<EmployeeRequest> get(String email) {
        return repository.findByEmail(email);
    }

    @Transactional
    public void update(EmployeeRequest employee, String email) {
        repository.update(employee, email);
    }

    @Transactional
    public void create(EmployeeRequest request) {
        String email = request.email();
        Optional<EmployeeRequest> existingUser = repository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new DuplicateException(String.format("User with the email address '%s' already exists.", email));
        }
        EmployeeRequest user = new EmployeeRequest(request.firstName(), request.lastName(), request.email(),  request.employeeStatus(),request.roles());
        repository.add(user);
    }

}
