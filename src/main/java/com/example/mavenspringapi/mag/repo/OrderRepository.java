package com.example.mavenspringapi.mag.repo;

import com.example.mavenspringapi.mag.controller.dto.OrderRequest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Optional;


@Repository
public class OrderRepository {

    private static final String INSERT = "INSERT INTO mag.orders (employee_id, customer_name, customer_address, customer_phone,customer_email,order_status,order_name)" +
            " VALUES(:employee_id, :customer_name, :customer_address, :customer_phone, :customer_email, :order_status, :order_name)";

    private final JdbcClient jdbcClient;

    public OrderRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Optional<OrderRequest> get(String phone) {
        return jdbcClient.sql(INSERT)
                .param("customer_phone",phone )
                .query(OrderRequest.class)
                .optional();
    }


    public void createOrder(OrderRequest order) {
        long affected = jdbcClient.sql(INSERT)
                .param("employee_id", order.employeeId())
                .param("customer_name", order.customerName())
                .param("customer_address", order.customerAddress())
                .param("customer_phone", order.customerPhone())
                .param("customer_email", order.customerEmail())
                .param("order_status", order.orderStatus())
                .param("order_name", order.orderName())
                .update();

        Assert.isTrue(affected == 1, "Could not add user.");
    }


}

