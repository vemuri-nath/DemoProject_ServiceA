package com.demo.redis.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor

@RedisHash("USER")
public class Users {
    private Long id;
    private String first_name;
    private String last_name;
    private String gender;
    private String designation;
    private String email;
    private String phone_no;
    private String password;

}
