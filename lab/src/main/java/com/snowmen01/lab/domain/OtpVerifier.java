package com.snowmen01.lab.domain;

public class OtpVerifier {

    private final OtpGenerator generator;
    private final int windowSize;
    private static final int TIME_STEP_MS = 30000;

    public OtpVerifier(OtpGenerator generator, int windowSize) {
        this.generator = generator;
        this.windowSize = windowSize;
    }

    public boolean verify(String secret, String otpCode, long currentTimestamp) {
        if (otpCode == null || otpCode.length() != 6) {
            return false;
        }

        for (int i = -windowSize; i <= windowSize; i++) {
            long targetTimestamp = currentTimestamp + (long) i * TIME_STEP_MS;
            String computedOtp = generator.generate(secret, targetTimestamp);
            if (computedOtp.equals(otpCode)) {
                return true;
            }
        }
        return false;
    }
}
