package com.trilogy.cloudgamestoreadmin.util.feign;


import com.trilogy.cloudgamestoreadmin.model.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "level-up-service")
public interface LevelUpFeignClient {
    @PostMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.CREATED)
    LevelUp saveLevelUp(@RequestBody @Valid LevelUp levelUp);

    @GetMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.OK)
    List<LevelUp> fetchAllLevelUp();

    @PutMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.OK)
    void updateLevelUp(@RequestBody @Valid LevelUp levelUp);

    @GetMapping(value = "/levelUp/{levelUpId}")
    @ResponseStatus(HttpStatus.OK)
    LevelUp fetchLevelUp(@PathVariable int levelUpId);

    @DeleteMapping(value = "/levelUp/{levelUpId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLevelUp(@PathVariable int levelUpId);

}
