package com.poc.springbatch.jobs;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.poc.springbatch.model.Team;

public class TeamResultRowMapper implements RowMapper<Team> {

    @Override
    public Team mapRow(ResultSet rs, int rowNum) throws SQLException {

        Team result = new Team();
        result.setId(rs.getInt("id"));
        result.setName(rs.getString("name"));
        result.setRating(rs.getInt("rating"));

        return result;
    }

}
