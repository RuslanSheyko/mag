package com.example.mavenspringapi.mag.repo;


import com.example.mavenspringapi.mag.controller.dto.EmployeeRequest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Optional;

@Repository
public class EmployeeRepository {

    private static final String UPDATE = "UPDATE employees.user first_name=:first_name ,last_name=:last_name,empolyee_status=:empolyee_status, email=:email, roles=:roles where email=:search_mail";
    private static final String INSERT = "INSERT INTO employees.user (first_name,last_name, email,empolyee_status, roles) VALUES(:first_name, :last_name,:email,:empolyee_status, :roles)";
    private static final String FIND_BY_EMAIL = "SELECT * FROM employees.user WHERE email = :email";

    private final JdbcClient jdbcClient;

    public EmployeeRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public void add(EmployeeRequest employee) {
        long affected = jdbcClient.sql(INSERT)
                .param("first_name", employee.firstName())
                .param("last_name", employee.lastName())
                .param("roles", employee.roles())
                .param("empolyee_status", employee.employeeStatus())
                .param("email", employee.email())
                .update();

        Assert.isTrue(affected == 1, "Could not add user.");
    }

    public void update(EmployeeRequest employee, String email) {
        long affected = jdbcClient.sql(UPDATE)
                .param("first_name", employee.firstName())
                .param("last_name", employee.lastName())
                .param("roles", employee.roles())
                .param("email", employee.email())
                .param("empolyee_status", employee.employeeStatus())
                .param("search_mail", email)
                .update();

        Assert.isTrue(affected == 1, "Could not add user.");
    }

    public Optional<EmployeeRequest> findByEmail(String email) {
        return jdbcClient.sql(FIND_BY_EMAIL)
                .param("email", email)
                .query(EmployeeRequest.class)
                .optional();
    }
}

