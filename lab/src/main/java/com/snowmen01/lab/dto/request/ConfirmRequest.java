package com.snowmen01.lab.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ConfirmRequest(
    @NotBlank(message = "User ID không được để trống")
    String userId,

    @NotBlank(message = "Mã SMS OTP không được để trống")
    @Pattern(regexp = "^[0-9]{6}$", message = "SMS OTP phải là 6 chữ số")
    String smsOtp
) {}
