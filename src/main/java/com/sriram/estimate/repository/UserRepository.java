package com.sriram.estimate.repository;

import com.sriram.estimate.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    public User findUserByEmail(String email);
}
