package com.example.redis.springbootrediscache.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
//@RedisHash(value = "user")
@Document(collection = "user")
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String name;
}