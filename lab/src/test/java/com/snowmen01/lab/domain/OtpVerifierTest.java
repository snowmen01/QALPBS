package com.snowmen01.lab.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OtpVerifierTest {

    private final OtpGenerator generator = new OtpGenerator();
    private final OtpVerifier verifier = new OtpVerifier(generator, 2);
    private final String secret = "JBSWY3DPEHPK3PXP";

    @Test
    @DisplayName("Xac thuc OTP chinh xac tai thoi diem hien tai")
    void shouldVerifyCorrectOtp() {
        long now = System.currentTimeMillis();
        String otp = generator.generate(secret, now);
        
        assertTrue(verifier.verify(secret, otp, now));
    }

    @Test
    @DisplayName("Xac thuc OTP sai lech 30s")
    void verifierOneStepDrift() {
        long now = System.currentTimeMillis();
        String prevOtp = generator.generate(secret, now - 30000);
        String nextOtp = generator.generate(secret, now + 30000);
        
        assertTrue(verifier.verify(secret, prevOtp, now));
        assertTrue(verifier.verify(secret, nextOtp, now));
    }

    @Test
    @DisplayName("Xac thuc OTP sai lech 1p")
    void verifierTwoStepsDrift() {
        long now = System.currentTimeMillis();
        String nextOtp = generator.generate(secret, now + 60000);
        
        assertTrue(verifier.verify(secret, nextOtp, now));
    }

    @Test
    @DisplayName("Xac thuc OTP sai")
    void failForIncorrectOtp() {
        long now = System.currentTimeMillis();
        assertFalse(verifier.verify(secret, "000000", now));
    }
}
