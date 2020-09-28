package com.demo.redis.Controller;

import com.demo.redis.Model.Team;
import com.demo.redis.Repository.TeamRepoistory;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/redis/team")
public class RedisController {
    @Autowired
    TeamRepoistory repo;

    @Autowired
    private RestTemplate restTemplate;

    ObjectMapper objectMapper = new ObjectMapper();


    @GetMapping("/{id}")
    public Team getTeam(@PathVariable String id) throws JsonProcessingException {
        if(repo.getById(id)!=null)return repo.getById(id);
        String ts = restTemplate.getForObject("http://localhost:8083/api/team/"+id,String.class);
        if(ts == null)return null;
        Team team = objectMapper.readValue(ts,Team.class);
        repo.save(team);
        return repo.getById(team.getName());
    }

    @GetMapping("/all")
    public Map<String, Object> getTeam() throws JsonProcessingException {
        if(repo.find()!=0)return repo.findAll();
        String s = restTemplate.getForObject("http://localhost:8083/api/team/",String.class);
        List<Team> teams = objectMapper.readValue(s,new TypeReference<List<Team>>() {});
        repo.saveAll(teams);
        return repo.findAll();
    }

}
