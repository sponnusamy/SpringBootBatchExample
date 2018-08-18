# SpringBootBatchExample

Simple Example to read from a table, process it and inserts into another table. The job would be triggered once as soon the server is up or can be triggered via http url.

# Setup

1. Install MySQL and create database `teamdb`. Initialization scripts would be automatically executed by flyway.
2. `git clone https://github.com/sponnusamy/SpringBootBatchExample.git`
3. `cd SpringBootBatchExample`
4. `mvn package && java -jar target/springbatch-0.0.1-SNAPSHOT.jar`

# Trigger Job 
http://localhost:8080/invoke
