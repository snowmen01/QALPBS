package com.snowmen01.lab.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record VerifyRequest(
    @NotBlank(message = "User ID không được để trống")
    String userId,

    @NotBlank(message = "Mã OTP không được để trống")
    @Pattern(regexp = "^[0-9]{6}$", message = "OTP phải là 6 chữ số")
    String otpCode
) {}