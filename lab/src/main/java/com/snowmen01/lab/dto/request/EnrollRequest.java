package com.snowmen01.lab.dto.request;

import jakarta.validation.constraints.NotBlank;

public record EnrollRequest(
    @NotBlank(message = "User ID không được để trống")
    String userId,
    
    @NotBlank(message = "Device ID không được để trống")
    String deviceId
) {}