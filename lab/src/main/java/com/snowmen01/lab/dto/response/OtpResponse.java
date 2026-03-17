package com.snowmen01.lab.dto.response;

public record OtpResponse(
    String userId,
    String status,
    String message,
    String metadata
) {}