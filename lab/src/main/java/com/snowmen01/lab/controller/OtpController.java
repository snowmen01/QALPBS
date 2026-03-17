package com.snowmen01.lab.controller;

import com.snowmen01.lab.dto.request.ConfirmRequest;
import com.snowmen01.lab.dto.request.EnrollRequest;
import com.snowmen01.lab.dto.request.VerifyRequest;
import com.snowmen01.lab.dto.response.OtpResponse;
import com.snowmen01.lab.service.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/enroll")
    public ResponseEntity<OtpResponse> enroll(@Valid @RequestBody EnrollRequest request) {
        OtpResponse response = otpService.enrollDevice(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm")
    public ResponseEntity<OtpResponse> confirm(@Valid @RequestBody ConfirmRequest request) {
        OtpResponse response = otpService.confirmDevice(request.userId(), request.smsOtp());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<OtpResponse> verify(@Valid @RequestBody VerifyRequest request) {
        OtpResponse response = otpService.verifySmartOtp(request);
        
        if ("SUCCESS".equals(response.status())) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body(response);
    }
}