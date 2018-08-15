package com.poc.springbatch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class TeamProcessJobExecutionListener implements JobExecutionListener {

    private static Logger LOGGER = LoggerFactory.getLogger(TeamProcessJobExecutionListener.class);

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("*******BATCH JOB COMPLETED SUCCESSFULLY********");
        }
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOGGER.info("*******BATCH JOB STARTED********");
    }

}
