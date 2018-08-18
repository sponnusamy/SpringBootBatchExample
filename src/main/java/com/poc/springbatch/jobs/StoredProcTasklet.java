package com.poc.springbatch.jobs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class StoredProcTasklet implements Tasklet {
    private static Logger LOGGER = LoggerFactory.getLogger(TeamProcessJobExecutionListener.class);
    DataSource dataSource;

    public StoredProcTasklet(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("*******Starts StoredProcTasklet********");
        try (Connection conn = this.dataSource.getConnection()) {

            CallableStatement cStmt = conn.prepareCall("{call sample_proc(?)}");
            cStmt.registerOutParameter(1, Types.VARCHAR);
            cStmt.execute();
            String status = cStmt.getString(1);
            if ("FAILED".equals(status)) {
                throw new Exception("Job Failed");
            }

            LOGGER.info("*******Ends StoredProcTasklet********");
        }

        return RepeatStatus.FINISHED;
    }

}
