package com.trilogy.cloudgamestorelevelupqueueconsumer.util.feign;


import com.trilogy.cloudgamestorelevelupqueueconsumer.model.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@FeignClient(name = "level-up-service")
public interface LevelUpServiceClient {

    @PostMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.CREATED)
    LevelUp saveLevelUp(@RequestBody @Valid LevelUp levelUp);

    @PutMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.OK)
    void updateLevelUp(@RequestBody @Valid LevelUp levelUp);

}

