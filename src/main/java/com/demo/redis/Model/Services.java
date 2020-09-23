package com.demo.redis.Model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("SERVICE")
public class Services {
    private long id;
    private String name;
    private String service_description;
}
