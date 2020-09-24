package com.demo.redis.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

@RedisHash("USER")
@JsonIgnoreProperties(ignoreUnknown = true)

public class Users implements Serializable {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    @JsonProperty("designation")
    private String designation;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone_no")
    private String phone_no;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("password")
    private String password;

}
