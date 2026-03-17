package com.snowmen01.lab.repository;

import com.snowmen01.lab.entity.UserOtpEntity;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class OtpRepository {
    private final Map<String, UserOtpEntity> storage = new ConcurrentHashMap<>();

    public void save(UserOtpEntity entity) {
        storage.put(entity.getUserId(), entity);
    }

    public UserOtpEntity findByUserId(String userId) {
        return storage.get(userId);
    }
}