package com.snowmen01.lab.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OtpGeneratorTest {

    private final OtpGenerator otpGenerator = new OtpGenerator();
    private final String secret = "JBSWY3DPEHPK3PXP";

    @Test
    @DisplayName("Tao OTP 6 chu so")
    void shouldGenerateSixDigitOtp() {
        long timestamp = 1679058000000L;
        String otp = otpGenerator.generate(secret, timestamp);
        
        assertNotNull(otp);
        assertEquals(6, otp.length());
        assertTrue(otp.matches("^[0-9]{6}$"));
    }

    @Test
    @DisplayName("Tao OTP giong nhau khi cung time step")
    void shouldGenerateSameOtpForSameTimeStep() {
        long timestamp1 = 1679058000000L; 
        long timestamp2 = 1679058010000L;
        
        String otp1 = otpGenerator.generate(secret, timestamp1);
        String otp2 = otpGenerator.generate(secret, timestamp2);
        
        assertEquals(otp1, otp2);
    }

    @Test
    @DisplayName("Tao OTP khac nhau khi khac time step")
    void shouldGenerateDifferentOtpForDifferentTimeSteps() {
        long timestamp1 = 1679058000000L;
        long timestamp2 = 1679058031000L; 
        
        String otp1 = otpGenerator.generate(secret, timestamp1);
        String otp2 = otpGenerator.generate(secret, timestamp2);
        
        assertNotEquals(otp1, otp2);
    }
}
