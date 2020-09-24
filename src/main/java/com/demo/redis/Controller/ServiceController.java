package com.demo.redis.Controller;

import com.demo.redis.Model.Services;
import com.demo.redis.Model.Users;
import com.demo.redis.Repository.ServiceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis/service")
public class ServiceController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    ServiceRepository serviceRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/{id}")
    public Services saveServices(@PathVariable Long id) throws JsonProcessingException {
        String s = restTemplate.getForObject("http://localhost:8083/api/services/"+id, String.class);
        if(s==null)return null;
        Services services = objectMapper.readValue(s,Services.class);
        serviceRepository.save(services);
        return serviceRepository.getById(id);

    }
    @GetMapping("/{id}")
    public Services findById(@PathVariable Long id){
        return serviceRepository.getById(id);
    }
    @PostMapping("/all")
    public List list() throws JsonProcessingException {
        String s = restTemplate.getForObject("http://localhost:8083/api/services/",String.class);
        List<Services> servicesList = objectMapper.readValue(s, new TypeReference<List<Services>>() {
        }) ;
        serviceRepository.saveAll(servicesList);
        return servicesList;
    }

    @GetMapping("/all")
    public Map<String, Object> getUsers(){
        return serviceRepository.usersMap();
    }

}
