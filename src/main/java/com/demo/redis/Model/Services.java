package com.demo.redis.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data

@JsonIgnoreProperties(ignoreUnknown = true)

public class Services implements Serializable {
    private long id;
    private String name;
    private String service_description;
}
