package com.trilogy.cloudgamestorelevelupservice.controller;

import com.trilogy.cloudgamestorelevelupservice.model.LevelUp;
import com.trilogy.cloudgamestorelevelupservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LevelUpController {

    @Autowired
    ServiceLayer serviceLayer;

    /*************************************************
     * Level Up Service Controller
     * Path: /levelUp
     *************************************************/

    /**
     * Saves level up to database
     * @param levelUp
     * @return
     */
    @PostMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp saveLevelUp(@RequestBody @Valid LevelUp levelUp) {
        return serviceLayer.saveLevelUp(levelUp);
    }

    /**
     * Retrieves all level ups from the database
     * @return
     */
    @GetMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUp> fetchAllLevelUp() {
        return serviceLayer.fetchAllLevelUp();
    }

    /**
     * Updates an existing level up entry when passed a valid level up object
     * @param levelUp
     */
    @PutMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody @Valid LevelUp levelUp) {
        serviceLayer.updateLevelUp(levelUp);
    }

    /*************************************************
     * Level Up Service Controller
     * Path: /levelUp/{levelUpId}
     *************************************************/


    /**
     * Retrieves one level up when passed a valid Level Up ID
     * @param levelUpId
     * @return
     */
    @GetMapping(value = "/levelUp/{levelUpId}")
    @ResponseStatus(HttpStatus.OK)
    public LevelUp fetchLevelUp(@PathVariable int levelUpId) {
        return serviceLayer.fetchLevelUp(levelUpId);
    }

    /**
     * Deletes a level up entry from database when passed a valid level up ID
     * @param levelUpId
     */
    @DeleteMapping(value = "/levelUp/{levelUpId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUp(@PathVariable int levelUpId) {
        serviceLayer.deleteLevelUp(levelUpId);
    }
}
