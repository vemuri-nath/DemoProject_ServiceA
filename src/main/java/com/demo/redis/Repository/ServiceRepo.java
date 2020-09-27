package com.demo.redis.Repository;

import com.demo.redis.Model.Services;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ServiceRepo {
    public void save(Services services);
    public Services getById(Long id);
    public void saveAll(List<Services> list);
    public Map<String,Object> servicesMap();
    public Long find();

}
