package com.example.mavenspringapi.mag.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


public record OrderRequest(
        @Schema(name = "Id of employee")
        String employeeId,
        @Schema(name = "Customer Name")
        String customerName,
        @Schema(name="Customer Address")
        String customerAddress,
        @Schema(name="Customer Phone")
        @NotBlank
        String customerPhone,
        @Schema(name="Customer Email")
        String customerEmail,
        @Schema(name="Order Status")
        String orderStatus,
        @Schema(name="Order Name")
        String orderName) {

}
