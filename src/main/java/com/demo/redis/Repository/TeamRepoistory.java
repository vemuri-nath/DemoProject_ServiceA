package com.demo.redis.Repository;

import com.demo.redis.Model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
public class TeamRepoistory implements TeamRepo{
    private HashOperations hashOperations;
    //private ListOperations listOperations;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public TeamRepoistory(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
        //this.listOperations = redisTemplate.opsForList();
    }

    //save team
    @Override
    public void save(Team team){
        //if(team == null)return;
        if(getById(team.getName())!=null)return;
        hashOperations.put("TEAM", team.getName(), team);
        redisTemplate.expire("TEAM",5,TimeUnit.SECONDS);

    }
    //get team by id
    @Override
    public Team getById(String id){
        //returns null if not found
        return (Team) hashOperations.get("TEAM",id);
    }

    //save all team
    @Override
    public void saveAll(List<Team> list){
        Map<String, Object> map1 = new HashMap<>();
        //map1.put("TEAM", list);
        for (Team t:
                list) {
            save(t);
        }

    }

    //get list of all teams
    @Override
    public Map<String,Object> findAll(){
        return  hashOperations.entries("TEAM");
    }

    @Override
    public Long find()
    {
        return hashOperations.size("TEAM");
    }


}
