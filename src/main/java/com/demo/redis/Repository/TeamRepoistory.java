package com.demo.redis.Repository;

import com.demo.redis.Model.Team;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamRepoistory {
    private HashOperations hashOperations;
    //private ListOperations listOperations;

    private RedisTemplate redisTemplate;

    public TeamRepoistory(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        //this.listOperations = redisTemplate.opsForList();
    }
    public void save(Team team){

        hashOperations.put("TEAM",team.getName(), team);

    }
    public Team getById(String id){
        return (Team) hashOperations.get("TEAM",id);
    }
    public List<Team> findALl(){
        return  hashOperations.values("TEAM");
    }
}
