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

    //Circuit Breaker Pattern placeholder
    @GetMapping(value = "/levelUp/{levelUpId")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody LevelUp fetchLevelUp(@PathVariable int levelUpId);

    //Circuit Breaker pattern place holder
    @GetMapping(value = "/levelUp/customerId/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody LevelUp fetchLevelUpByCustomerId(@PathVariable int customerId);
}