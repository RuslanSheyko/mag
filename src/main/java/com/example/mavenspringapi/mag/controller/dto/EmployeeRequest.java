package com.example.mavenspringapi.mag.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeRequest(
        @Schema(description = "name", example = "Jhon")
        @NotBlank(message = "First name cannot be blank")
        String firstName,
        @Schema(description = "Last name", example = "Doe")
        @NotBlank(message = "Last name cannot be blank")
        String lastName,
        @Schema(description = "email", example = "mina@gmail.com")
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email cannot be blank")
        String email,
        @Schema(description = "employee status", example = "work")
        String employeeStatus,
        @Schema(description = "role", example = "miner")
        @NotBlank(message = "Role cannot be blank")
        String roles) {

}
