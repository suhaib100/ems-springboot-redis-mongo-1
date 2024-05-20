package com.example.redis.springbootrediscache.repository;

import com.example.redis.springbootrediscache.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDBRepository extends  MongoRepository<User, String> {
}
