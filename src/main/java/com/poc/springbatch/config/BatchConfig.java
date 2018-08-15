package com.poc.springbatch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.poc.springbatch.model.ProcessedTeam;
import com.poc.springbatch.model.Team;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<Team> jdbcReader(DataSource dataSource) {
        JdbcCursorItemReader<Team> reader = new JdbcCursorItemReader<Team>();
        reader.setDataSource(dataSource);
        reader.setName("jdbc-reader");
        reader.setSql("SELECT ID, NAME, RATING FROM TEAMS");
        reader.setRowMapper(rowMapper());
        ;
        return reader;
    }

    @Bean
    public RowMapper<Team> rowMapper() {
        return new TeamResultRowMapper();
    }

    @Bean
    public TeamItemProcessor processor() {
        return new TeamItemProcessor();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcBatchItemWriter<ProcessedTeam> writer(DataSource dataSource) {

        JdbcBatchItemWriter<ProcessedTeam> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("insert into teams_processed(name,rating,color_code) values (:name, :rating, :colorCode)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<ProcessedTeam> writer) {
        return stepBuilderFactory.get("step1").<Team, ProcessedTeam>chunk(10).reader(jdbcReader(dataSource()))
                .processor(processor()).writer(writer).build();
    }

    @Bean
    Job teamProcessJob(JobBuilderFactory jbf, StepBuilderFactory sbf, Step step1) throws Exception {
        return jbf.get("teamProcessJob").incrementer(new RunIdIncrementer()).start(step1).listener(listener()).build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new TeamProcessJobExecutionListener();
    }
}
