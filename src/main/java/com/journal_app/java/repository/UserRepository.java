package com.journal_app.java.repository;

import com.journal_app.java.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String userName);
    void deleteByUserName(String userName);
    Optional<User> findByEmail(String email);

}
