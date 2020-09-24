package com.demo.redis.Repository;

import com.demo.redis.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private HashOperations hashOperations;
    //private ListOperations listOperations;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public UserRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        //this.listOperations = redisTemplate.opsForList();
    }

    //save the user
    public void save(Users users){
        hashOperations.put("USER", users.getId().toString(),users);
    }

    //get by user id
    public Users getById(Long id){
        return (Users) hashOperations.get("USER",id.toString());
    }

    //save all users
    public void saveAll(List<Users>list){
        Map<String, Object> map1 = new HashMap<>();
        for (Users u:
             list) {
            map1.put(u.getId().toString(), u);
        }
        hashOperations.putAll("USER",map1);
    }

    //get the list of all users
    public Map<String,Object> usersMap(){
        return hashOperations.entries("USER");
    }
}
