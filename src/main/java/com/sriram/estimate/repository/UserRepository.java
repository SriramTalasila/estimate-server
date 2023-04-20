package com.sriram.estimate.repository;

import com.sriram.estimate.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    public User findUserByEmail(String email);
}
