package com.demo.redis.Controller;

import com.demo.redis.Model.Team;
import com.demo.redis.Model.Users;
import com.demo.redis.Repository.ServiceRepository;
import com.demo.redis.Repository.TeamRepoistory;
import com.demo.redis.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/redis/search")
public class SearchController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    TeamRepoistory teamRepoistory;

    ObjectMapper objectMapper = new ObjectMapper();

    //search by team name and get teams info
    @GetMapping("/team/{name}")
    public Team getTeam(@PathVariable String name) throws JsonProcessingException {
        if(teamRepoistory.getById(name) != null) return teamRepoistory.getById(name);
        String ts = restTemplate.getForObject("http://localhost:8083/api/team/"+name,String.class);
        if(ts == null)return null;
        Team team = objectMapper.readValue(ts,Team.class);
        if(team == null)return null;
       teamRepoistory.save(team);
        return teamRepoistory.getById(team.getName());
    }
    //search by user name


    //search by user email
    @GetMapping("/user/email/{mail}")
    public Users getTeamByUserMail(@PathVariable String mail) throws JsonProcessingException {
        if(userRepository.getByEmail(mail) != null)return userRepository.getByEmail(mail);
        String ts = restTemplate.getForObject("http://localhost:8083/api/user/email/"+mail,String.class);
        if(ts == null)return null;
       Users users = objectMapper.readValue(ts,Users.class);
        if(users == null)return null;
        userRepository.save(users);
        return userRepository.getByEmail(mail);
    }


    //search by user phonenumber
}
