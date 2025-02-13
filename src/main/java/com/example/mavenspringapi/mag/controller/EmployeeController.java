package com.example.mavenspringapi.mag.controller;


import com.example.mavenspringapi.mag.controller.dto.EmployeeRequest;
import com.example.mavenspringapi.mag.exception.EmployeeErrorResponse;
import com.example.mavenspringapi.mag.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/employees", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "create employee")
    @ApiResponse(responseCode = "201")
    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = EmployeeErrorResponse.class)))
    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = EmployeeErrorResponse.class)))
    @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = EmployeeErrorResponse.class)))
    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = EmployeeErrorResponse.class)))
    @PostMapping("/new")
    ResponseEntity<?> newEmployee(@RequestBody EmployeeRequest employeeRequest) {
        if (employeeService.get(employeeRequest.email()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        employeeService.create(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "get employee by email")
    @GetMapping("/{email}")
    @Query(name = "email")
    public ResponseEntity<EmployeeRequest> one(@PathVariable String email) {
        return ResponseEntity.of(employeeService.get(email));
    }


//    @Operation(summary = "update employee by email")
//    @PutMapping("/{email}")
//    ResponseEntity replaceEmployee(@RequestBody Employee newEmployee, @RequestParam String email) {
//        employeeService.save(newEmployee, email);
//        return ResponseEntity.ok().build();
//    }
}