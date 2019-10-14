package com.trilogy.cloudgamestorelevelupservice.dao;

import com.trilogy.cloudgamestorelevelupservice.model.LevelUp;

import java.util.List;

public interface LevelUpDao {

    LevelUp addLevelUp(LevelUp levelUp);

    LevelUp getLevelUp(int levelUpId);

    List<LevelUp> getAllLevelUps();

    void updateLevelUp(LevelUp levelUp);

    void deleteLevelUp(int levelUpId);

    LevelUp getLevelUpByCustomerId(int customerId);
}
