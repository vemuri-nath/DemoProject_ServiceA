package com.demo.redis.Repository;

import com.demo.redis.Model.Team;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TeamRepo {
    public void save(Team team);
    public Team getById(String id);
    public void saveAll(List<Team> list);
    public Map<String,Object> findAll();
    public Long find();
}
