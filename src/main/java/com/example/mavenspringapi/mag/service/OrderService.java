package com.example.mavenspringapi.mag.service;

import com.example.mavenspringapi.mag.controller.dto.OrderRequest;
import com.example.mavenspringapi.mag.repo.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void create(OrderRequest request) {
        repository.createOrder(request);
    }

}
