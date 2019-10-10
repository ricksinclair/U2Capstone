package com.trilogy.cloudgamestorelevelupservice.service;

import com.trilogy.cloudgamestorelevelupservice.dao.LevelUpDao;
import com.trilogy.cloudgamestorelevelupservice.model.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    private LevelUpDao levelUpDao;

    @Autowired
    ServiceLayer(LevelUpDao levelUpDao) {this.levelUpDao = levelUpDao;}

    /*************************************************
     * Level Up Service API
     *************************************************/

    /**
     * Saves level up to database
     * @param levelUp
     * @return
     */
    public LevelUp saveLevelUp(LevelUp levelUp) {
        return levelUpDao.addLevelUp(levelUp);
    }

    /**
     * Retrieves one level up when passed a valid Level Up ID
     * @param levelUpId
     * @return
     */
    public LevelUp fetchLevelUp(int levelUpId) {
        return levelUpDao.getLevelUp(levelUpId);
    }

    /**
     * Retrieves all level ups from the database
     * @return
     */
    public List<LevelUp> fetchAllLevelUp() {
        return levelUpDao.getAllLevelUps();
    }

    /**
     * Updates an existing level up entry when passed a valid level up object
     * @param levelUp
     */
    public void updateLevelUp(LevelUp levelUp) {
        levelUpDao.updateLevelUp(levelUp);
    }

    /**
     * Deletes a level up entry from database when passed a valid level up ID
     * @param levelUpId
     */
    public void deleteLevelUp(int levelUpId) {
        levelUpDao.deleteLevelUp(levelUpId);
    }

}
