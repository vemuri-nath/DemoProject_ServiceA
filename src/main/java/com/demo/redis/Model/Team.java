package com.demo.redis.Model;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

@RedisHash(value = "TEAM")
public class Team implements Serializable {

    private long id;
    private String name;
    private String description;
    private List<Users> usersList;
    private List<Services>servicesList;
}
