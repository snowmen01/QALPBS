package com.snowmen01.lab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("UserOtp")
public class UserOtpEntity implements Serializable {
    @Id
    private String userId;
    private String deviceId;
    private String secretKey;
    private long createdAt;
}