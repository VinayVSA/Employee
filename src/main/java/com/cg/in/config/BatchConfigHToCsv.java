package com.cg.in.config;

import com.cg.in.entities.Employee;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;
@Configuration
public class BatchConfigHToCsv {

	    @Bean
	   // @Primary
	    @Qualifier("csvToH2Job")
	    public Job csvToH2Job(JobRepository jobRepository,
	                             JobExecutionListener listener,
	                             @Qualifier("csvToH2Step") Step csvToH2Step) {
	        return new JobBuilder("CsvToH2Job", jobRepository)
	                .listener(listener)
	                .start(csvToH2Step)
	                .build();
	    }

	    @Bean
	   // @Primary
	    @Qualifier("csvToH2Step")
	    public Step csvToH2Step(JobRepository jobRepository,
	                      PlatformTransactionManager transactionManager,
	                      EmployeeItemReaderCsvToH2 reader,
	                      EmployeeItemProcessor processor,
	                      EmployeeItemWriterCsvToH2 writer) {
	        return new StepBuilder("CsvToH2Step", jobRepository)
	                .<Employee, Employee>chunk(10, transactionManager)
	                .reader(reader)
	                .processor(processor)
	                .writer(writer)
	                .build();
	    }

}
