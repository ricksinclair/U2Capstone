package com.trilogy.cloudgamestorelevelupservice.dao;

import com.trilogy.cloudgamestorelevelupservice.model.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LevelUpDaoImpl implements LevelUpDao {

    private final String INSERT_LEVEL_UP_SQL =
            "insert into level_up (customer_id, points, member_date) " +
                    "values(?, ?, ?)";

    private final String SELECT_LEVEL_UP_SQL =
            "select * from level_up where level_up_id = ?";

    private final String SELECT_ALL_LEVEL_UP_SQL =
            "select * from level_up";

    private final String UPDATE_LEVEL_UP_SQL =
            "update level_up set customer_id = ?, points = ?, member_date = ? where level_up_id = ?";

    private final String DELETE_LEVEL_UP_SQL =
            "delete from level_up where level_up_id = ?";

    private final String SELECT_LEVEL_UP_BY_CUSTOMER_ID_SQL =
            "select * from level_up where customer_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
     LevelUpDaoImpl(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    private LevelUp mapRowToLevelUp(ResultSet rs, int rowNum) throws SQLException {
        LevelUp levelUp = new LevelUp();
        levelUp.setLevelUpId(rs.getInt("level_up_id"));
        levelUp.setCustomerId(rs.getInt("customer_id"));
        levelUp.setPoints(rs.getInt("points"));
        levelUp.setMemberDate(rs.getDate("member_date").toLocalDate());
        return levelUp;
    }
    @Override
    @Transactional
    public LevelUp addLevelUp(LevelUp levelUp) {
         jdbcTemplate.update(INSERT_LEVEL_UP_SQL,
                 levelUp.getCustomerId(),
                 levelUp.getPoints(),
                 levelUp.getMemberDate());

         int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
         levelUp.setLevelUpId(id);
         return levelUp;
    }

    @Override
    public LevelUp getLevelUp(int levelUpId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_LEVEL_UP_SQL, this::mapRowToLevelUp, levelUpId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<LevelUp> getAllLevelUps() {
        return jdbcTemplate.query(SELECT_ALL_LEVEL_UP_SQL, this::mapRowToLevelUp);
    }

    @Override
    public void updateLevelUp(LevelUp levelUp) {
        jdbcTemplate.update(UPDATE_LEVEL_UP_SQL,
                levelUp.getCustomerId(),
                levelUp.getPoints(),
                levelUp.getMemberDate(),
                levelUp.getLevelUpId());
    }

    @Override
    public void deleteLevelUp(int levelUpId) {
        jdbcTemplate.update(DELETE_LEVEL_UP_SQL, levelUpId);
    }

    @Override
    public LevelUp getLevelUpByCustomerId(int customerId) {

        try {
            return jdbcTemplate.queryForObject(SELECT_LEVEL_UP_BY_CUSTOMER_ID_SQL, this::mapRowToLevelUp, customerId);
        } catch (Exception e) {
            return null;
        }
    }

}
