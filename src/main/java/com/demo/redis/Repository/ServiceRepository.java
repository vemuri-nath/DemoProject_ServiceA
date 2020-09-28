package com.demo.redis.Repository;

import com.demo.redis.Model.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
public class ServiceRepository implements ServiceRepo{
    private HashOperations hashOperations;
    //private ListOperations listOperations;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public ServiceRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        //this.listOperations = redisTemplate.opsForList();
    }
    @Override
    public void save(Services services){
        if(getById(services.getId()) != null)return ;
        hashOperations.put("SERVICE",services.getId().toString(),services);
        //System.out.println("cache miss");
        redisTemplate.expire("SERVICE",5, TimeUnit.SECONDS);
    }
    @Override
    public Services getById(Long id){
        return (Services) hashOperations.get("SERVICE",id.toString());
    }
    @Override
    public void saveAll(List<Services> list){
        Map<String, Object> map1 = new HashMap<>();
        for (Services u:
                list) {
            save(u);
        }
    }

    @Override
    public Map<String,Object> servicesMap(){
        return hashOperations.entries("SERVICE");
    }

    @Override
    public Long find()
    {

        return hashOperations.size("SERVICE");
    }

}
