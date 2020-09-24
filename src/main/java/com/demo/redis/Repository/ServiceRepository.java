package com.demo.redis.Repository;

import com.demo.redis.Model.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ServiceRepository {
    private HashOperations hashOperations;
    //private ListOperations listOperations;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public ServiceRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        //this.listOperations = redisTemplate.opsForList();
    }

    public void save(Services services){
        hashOperations.put("SERVICE",services.getId().toString(),services);
    }
    public Services getById(Long id){
        return (Services) hashOperations.get("SERVICE",id.toString());
    }
    public void saveAll(List<Services> list){
        Map<String, Object> map1 = new HashMap<>();
        for (Services u:
                list) {
            map1.put(u.getId().toString(), u);
        }
        hashOperations.putAll("SERVICE",map1);
    }

    public Map<String,Object> usersMap(){
        return hashOperations.entries("SERVICE");
    }
}
