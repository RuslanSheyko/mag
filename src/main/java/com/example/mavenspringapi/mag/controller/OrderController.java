package com.example.mavenspringapi.mag.controller;

import com.example.mavenspringapi.mag.controller.dto.OrderRequest;
import com.example.mavenspringapi.mag.exception.OrderNotFoundException;
import com.example.mavenspringapi.mag.repo.OrderRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderRepository repository;

    public OrderController(OrderRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "get order")
    @GetMapping("/{phone}")
    public ResponseEntity<OrderRequest> getOrder(@PathVariable String phone) {
        return ResponseEntity.ok(repository.get(phone) //
                .orElseThrow(() -> new OrderNotFoundException(phone)));
    }

    @Operation(summary = "create new order")
    @PostMapping("/new")
    public ResponseEntity<HttpStatus> addNewOrder(@RequestBody OrderRequest newOrder) {
        repository.createOrder(newOrder);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
