package com.demo.redis.Controller;

import com.demo.redis.Model.Users;
import com.demo.redis.Repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis/user")
public class UserController {
    @Autowired
    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    //save users
    @GetMapping("/{id}")
    public Users saveUsers(@PathVariable Long id) throws JsonProcessingException {
        if(userRepository.getById(id)!=null)return userRepository.getById(id);
        String userString = restTemplate.getForObject("http://localhost:8083/api/user/"+id,String.class);
        if(userString==null)return null;
        Users users = objectMapper.readValue(userString, Users.class);
        userRepository.save(users);
        return userRepository.getById(id);
    }




    //show the list of all users
    @GetMapping("/all")
    public Map<String, Object> getUsers() throws JsonProcessingException {
        if(userRepository.find()!=0)return userRepository.usersMap();
        String s = restTemplate.getForObject("http://localhost:8083/api/user/",String.class);
        List<Users> usersList = objectMapper.readValue(s, new TypeReference<List<Users>>() {
        }) ;
        userRepository.saveAll(usersList);
        return userRepository.usersMap();
    }
}
