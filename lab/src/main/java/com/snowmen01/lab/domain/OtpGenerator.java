package com.snowmen01.lab.domain;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class OtpGenerator {

    private static final int TIME_STEP_MS = 30000;
    private static final int DIGITS = 6;
    private static final String SHA1 = "HmacSHA1"; // SHA-1

    public String generate(String secret, long timestamp) {
        long timeIndex = timestamp / TIME_STEP_MS;
        byte[] k = decodeSecret(secret);
        byte[] data = ByteBuffer.allocate(8).putLong(timeIndex).array();

        try {
            Mac mac = Mac.getInstance(SHA1);
            mac.init(new SecretKeySpec(k, SHA1));
            byte[] hash = mac.doFinal(data);

            int offset = hash[hash.length - 1] & 0xf;
            int binary = ((hash[offset] & 0x7f) << 24) |
                         ((hash[offset + 1] & 0xff) << 16) |
                         ((hash[offset + 2] & 0xff) << 8) |
                         (hash[offset + 3] & 0xff);

            int otp = binary % (int) Math.pow(10, DIGITS);
            return String.format("%0" + DIGITS + "d", otp);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Không thể khởi tạo OTP", e);
        }
    }

    private byte[] decodeSecret(String secret) {
        try {
            return Base64.getDecoder().decode(secret);
        } catch (Exception e) {
            return secret.getBytes();
        }
    }
}
