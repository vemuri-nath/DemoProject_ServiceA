package com.demo.redis.Controller;

import com.demo.redis.Model.Team;
//import com.demo.redis.Repository.TeamRepoistory;
import com.demo.redis.Repository.TeamRepoistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    TeamRepoistory repo;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping ("/{id}")
    public void anInt(@PathVariable String id){
        Team team = restTemplate.getForObject("http://localhost:8083/api/team/"+id,Team.class);
        repo.save(team);
    }
    @GetMapping("/{id}")
    public Team getTeam(@PathVariable String id){
        return repo.getById(id);
    }



    @GetMapping("/all")
    public List <Team> getTeam(){
        /*for(Team t:teams) repo.save(t);

         Iterable<Team> it= repo.findAll();
         List<Team> teamList = new ArrayList<Team>();
         it.forEach(teamList::add);
         return teamList;

          */

        List<Team> teamList = restTemplate.getForObject("http://localhost:8083/api/team/", List.class);
        /*for (Team t : teamList) {
            repo.save(t);
        }*/
        return restTemplate.getForObject("http://localhost:8083/api/team/", List.class);
        //return repo.findALl();

    }
}
