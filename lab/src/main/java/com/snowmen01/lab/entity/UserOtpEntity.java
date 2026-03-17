package com.snowmen01.lab.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOtpEntity {
    private String userId;
    private String deviceId;
    private String secretKey;
    private long createdAt;
}