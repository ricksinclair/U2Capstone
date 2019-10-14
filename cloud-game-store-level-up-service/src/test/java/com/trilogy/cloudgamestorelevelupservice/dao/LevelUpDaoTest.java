package com.trilogy.cloudgamestorelevelupservice.dao;

import com.trilogy.cloudgamestorelevelupservice.model.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LevelUpDaoTest {

    @Autowired
    private LevelUpDao levelUpDao;

    @Before
    public void setUp() {
        //Cleans up test DB
        List<LevelUp> levelUps = levelUpDao.getAllLevelUps();
        levelUps.stream()
                .forEach(levelUp -> levelUpDao.deleteLevelUp(levelUp.getLevelUpId()));
    }

    @Test
    public void addGetDeleteLevelUp() {
        LevelUp levelUp = new LevelUp(1,100, LocalDate.now());
        //Adds Level Up to database
        levelUp = levelUpDao.addLevelUp(levelUp);

        //Test get all and asserts that level up was successfully added to database
        assertEquals(1, levelUpDao.getAllLevelUps().size());

        //Test get one Level Up from database
        LevelUp retrieveLevelUp = levelUpDao.getLevelUp(levelUp.getLevelUpId());

        //Asserts that level up retrieved is the same as level up previously saved
        assertEquals(levelUp, retrieveLevelUp);

        //Tests Get by customer ID
        LevelUp customerIdRetreivedLevelUp = levelUpDao.getLevelUpByCustomerId(levelUp.getCustomerId());
        assertEquals(levelUp, customerIdRetreivedLevelUp);

        //Add a second Level up to database
        LevelUp levelUp1 = new LevelUp(2, 200, LocalDate.now());
        levelUp1 = levelUpDao.addLevelUp(levelUp1);

        //Assert second level up was added successfully
        assertEquals(2, levelUpDao.getAllLevelUps().size());

        //Delete a level up
        levelUpDao.deleteLevelUp(levelUp.getLevelUpId());

        //Assert that one level up was deleted
        assertEquals(1, levelUpDao.getAllLevelUps().size());
    }

    @Test
    public void getAllLevelUps() {
        LevelUp levelUp = new LevelUp(1,100, LocalDate.now());
        //Adds Level Up to database
        levelUp = levelUpDao.addLevelUp(levelUp);

        //Add a second Level up to database
        LevelUp levelUp1 = new LevelUp(2, 200, LocalDate.now());
        levelUp1 = levelUpDao.addLevelUp(levelUp1);

        //Assert second level up was added successfully
        assertEquals(2, levelUpDao.getAllLevelUps().size());
    }

    @Test
    public void updateLevelUp() {
        LevelUp levelUp = new LevelUp(1,100, LocalDate.now());
        //Adds Level Up to database
        levelUp = levelUpDao.addLevelUp(levelUp);

        //Updates the level up previously added
        levelUp.setCustomerId(3);
        levelUp.setPoints(333);
        levelUp.setMemberDate(LocalDate.of(2019, 10, 31));
        levelUpDao.updateLevelUp(levelUp);

        //Retrieves updated level up from database
        LevelUp updatedLevelUp = levelUpDao.getLevelUp(levelUp.getLevelUpId());

        //Asserts that updated retrieved level up matches the updated information
        assertEquals(levelUp, updatedLevelUp);
    }
}