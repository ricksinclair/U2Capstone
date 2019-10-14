package com.trilogy.cloudgamestorelevelupqueueconsumer.util.feign;


import com.trilogy.cloudgamestorelevelupqueueconsumer.model.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "level-up-service")
public interface LevelUpServiceClient {

    @PostMapping(value = "/levelUp")
    LevelUp saveLevelUp(@RequestBody LevelUp levelUp);

    @PutMapping(value = "/levelUp")
    void updateLevelUp(@RequestBody LevelUp levelUp);

}

