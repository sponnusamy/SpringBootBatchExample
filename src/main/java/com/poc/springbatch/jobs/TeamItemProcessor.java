package com.poc.springbatch.jobs;

import org.springframework.batch.item.ItemProcessor;

import com.poc.springbatch.model.ProcessedTeam;
import com.poc.springbatch.model.Team;

public class TeamItemProcessor implements ItemProcessor<Team, ProcessedTeam> {

    @Override
    public ProcessedTeam process(Team item) throws Exception {

        ProcessedTeam teamProcessed = new ProcessedTeam();
        teamProcessed.setId(item.getId());
        teamProcessed.setName(item.getName());
        teamProcessed.setRating(item.getRating());
        teamProcessed.setColorCode(item.getName().length() > 3 ? item.getName().toUpperCase().substring(0, 3)
                : item.getName().toUpperCase());

        return teamProcessed;

    }

}
