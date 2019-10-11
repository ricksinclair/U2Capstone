package com.trilogy.cloudgamestoreretail.util.feign;

import com.trilogy.cloudgamestoreretail.model.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient(name = "level-up-service")
public interface LevelUpClient {

    @PostMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.CREATED)
    LevelUp saveLevelUp(@RequestBody @Valid LevelUp levelUp);

    @PutMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.OK)
    void updateLevelUp(@RequestBody @Valid LevelUp levelUp);

    //Circuit Breaker Pattern placeholder - Keep in mind LevelUp ID vs. Customer ID approach
    @GetMapping(value = "/levelUp/{levelUpId")
    @ResponseStatus(HttpStatus.OK)
    LevelUp fetchLevelUp(@PathVariable int levelUpId);
}