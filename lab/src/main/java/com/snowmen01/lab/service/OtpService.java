package com.snowmen01.lab.service;

import com.snowmen01.lab.domain.OtpGenerator;
import com.snowmen01.lab.domain.OtpVerifier;
import com.snowmen01.lab.dto.request.EnrollRequest;
import com.snowmen01.lab.dto.request.VerifyRequest;
import com.snowmen01.lab.dto.response.OtpResponse;
import com.snowmen01.lab.entity.UserOtpEntity;
import com.snowmen01.lab.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;
    private final OtpGenerator otpGenerator = new OtpGenerator();
    private final OtpVerifier otpVerifier = new OtpVerifier(otpGenerator, 2);

    public OtpResponse enrollDevice(EnrollRequest request) {
        String secretKey = UUID.randomUUID().toString().substring(0, 10).toUpperCase();
        
        UserOtpEntity entity = new UserOtpEntity(
                request.userId(),
                request.deviceId(),
                secretKey,
                System.currentTimeMillis()
        );
        
        otpRepository.save(entity);
        
        return new OtpResponse(
                request.userId(), 
                "PENDING", 
                "Vui lòng xác nhận bằng mã OTP được gửi tới thiết bị của bạn.", 
                secretKey
        );
    }

    public OtpResponse confirmDevice(String userId, String smsOtp) {
        if ("123456".equals(smsOtp)) {
            return new OtpResponse(userId, "ACTIVATED", "Thiết bị đã được kích hoạt Smart OTP", null);
        }
        return new OtpResponse(userId, "FAILED", "Mã xác nhận không chính xác", null);
    }

    public OtpResponse verifySmartOtp(VerifyRequest request) {
        UserOtpEntity entity = otpRepository.findByUserId(request.userId());
        if (entity == null) {
            return new OtpResponse(request.userId(), "FAILED", "Người dùng chưa đăng ký Smart OTP", null);
        }

        boolean isValid = otpVerifier.verify(entity.getSecretKey(), request.otpCode(), System.currentTimeMillis());
        
        if (isValid) {
            return new OtpResponse(request.userId(), "SUCCESS", "Xác thực thành công", null);
        }
        
        return new OtpResponse(request.userId(), "FAILED", "Mã OTP không đúng hoặc đã hết hạn", null);
    }
}
