package com.demo.redis.Repository;

import com.demo.redis.Model.Users;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRepo {
    public void save(Users users);
    public Users getById(Long id);
    public Users getByEmail(String email);
    public void saveAll(List<Users> list);
    public Map<String,Object> usersMap();
}
