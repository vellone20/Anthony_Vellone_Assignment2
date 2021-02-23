package ca.sheridancollege.vellone.assignment2.database;


import ca.sheridancollege.vellone.assignment2.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseAccess {

    @Autowired
    protected  NamedParameterJdbcTemplate jdbc;

    public void insertTeam(String teamName, String continent, int gamesPlayed, int gamesWon, int gamesDrawn, int gamesLost, int points){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="INSERT INTO team(teamName, continent, gamesPlayed, gamesWon, gamesDrawn, gamesLost, points) " +
                "VALUES(:teamName, :continent, :gamesPlayed, :gamesWon, :gamesDrawn, :gamesLost, :points)";
        namedParameters.addValue("teamName", teamName).addValue("continent", continent)
                .addValue("gamesPlayed", gamesPlayed).addValue("gamesWon", gamesWon)
                .addValue("gamesDrawn", gamesDrawn).addValue("gamesLost", gamesLost)
                .addValue("points", points);
        int rowsAffected = jdbc.update(query,namedParameters);
        if (rowsAffected > 0)
            System.out.println("The record was inserted successfully!");
    }

    public List<Team> getTeams(){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="SELECT * FROM team";
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }

    public void deleteTeamByID(Long id){//id used as key to find team
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="DELETE FROM team WHERE id = :id";
        namedParameters.addValue("id", id);
        int rowsAffected = jdbc.update(query,namedParameters);
        if (rowsAffected > 0)
            System.out.println("The record was deleted successfully!");
    }

    public void editTeamByID(Team team){//all fields allowed to be edited then commit
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="UPDATE team set teamName= :teamName, continent= :continent, gamesPlayed= :gamesPlayed" +
                ", gamesWon= :gamesWon, gamesDrawn= :gamesDrawn, gamesLost= :gamesLost, points= :points WHERE id = :id";
        namedParameters.addValue("teamName", team.getTeamName()).addValue("continent", team.getContinent())
                .addValue("gamesPlayed", team.getGamesPlayed()).addValue("gamesWon", team.getGamesWon())
                .addValue("gamesDrawn", team.getGamesDrawn()).addValue("gamesLost", team.getGamesLost())
                .addValue("points", team.getPoints()).addValue("id", team.getId());
        int rowsAffected = jdbc.update(query,namedParameters);
        if (rowsAffected > 0)
            System.out.println("The record was updated successfully!");
    }

    public List<Team> getTeam(Long id){
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query ="SELECT * FROM team WHERE id = :id";
        namedParameters.addValue("id", id);
        return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Team>(Team.class));
    }

}
