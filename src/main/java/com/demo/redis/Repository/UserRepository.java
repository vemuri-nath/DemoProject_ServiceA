package com.demo.redis.Repository;

import com.demo.redis.Model.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
public class UserRepository implements UserRepo{
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
    @Override
    public void save(Users users){
        if(getById(users.getId())!=null)return;
        hashOperations.put("USER", users.getId().toString(),users);
        hashOperations.put("USER_MAIL",users.getEmail(),users);
        redisTemplate.expire("USER",5, TimeUnit.SECONDS);
        redisTemplate.expire("USER_MAIL",5,TimeUnit.SECONDS);

    }

    //get by user id
    @Override
    public Users getById(Long id){
        return (Users) hashOperations.get("USER",id.toString());
    }

    //get by user Email
    @Override
    public Users getByEmail(String email){
        return (Users) hashOperations.get("USER_MAIL",email);
    }

    //save all users
    @Override
    public void saveAll(List<Users>list){
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object>map2 = new HashMap<>();
        for (Users u:
             list) {
            save(u);
        }
    }

    //get the list of all users
    @Override
    public Map<String,Object> usersMap(){
        return hashOperations.entries("USER");
    }

    @Override
    public Long find()
    {
        return hashOperations.size("USER");
    }
}
