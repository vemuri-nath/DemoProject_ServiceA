package com.demo.redis.Controller;

import com.demo.redis.Model.Team;
import com.demo.redis.Repository.TeamRepoistory;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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

    @PostMapping ("/{id}")
    public Team saveTeam(@PathVariable String id) throws JsonProcessingException {
        String ts = restTemplate.getForObject("http://localhost:8083/api/team/"+id,String.class);
        if(ts == null)return null;
        Team team = objectMapper.readValue(ts,Team.class);
        if(team == null)return null;
        repo.save(team);
        return repo.getById(team.getName());
    }
    @GetMapping("/{id}")
    public Team getTeam(@PathVariable String id){
        return repo.getById(id);
    }

    @PostMapping("/all")
   public List list() throws JsonProcessingException {
        String s = restTemplate.getForObject("http://localhost:8083/api/team/",String.class);
        List<Team> teams = objectMapper.readValue(s,new TypeReference<List<Team>>() {});
        repo.saveAll(teams);
        return teams;
   }


    @GetMapping("/all")
    public Map<String, Object> getTeam()  {

        return repo.findAll();

    }

}
