package com.demo.redis.Repository;

import com.demo.redis.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TeamRepoistory {
    private HashOperations hashOperations;
    //private ListOperations listOperations;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public TeamRepoistory(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        //this.listOperations = redisTemplate.opsForList();
    }
    public void save(Team team){
        //if(team == null)return;
        hashOperations.put("TEAM", team.getName(), team);

    }
    public Team getById(String id){
        return (Team) hashOperations.get("TEAM",id);
    }

    public void saveAll(List<Team> list){
        Map<String, Object> map1 = new HashMap<>();
        map1.put("TEAM", list);
        hashOperations.putAll("TEAM", map1);
    }
    public Map<String,Object> findAll(){
        return  hashOperations.entries("TEAM");
    }


}
