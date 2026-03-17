package com.snowmen01.lab.repository;

import com.snowmen01.lab.entity.UserOtpEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends CrudRepository<UserOtpEntity, String> {
}