package com.cg.in.TaskletConfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BtConfig {
	
	 @Autowired
	    private EmpReaderTasklet readerTasklet;

	    @Autowired
	    private EmpProcessorTasklet processorTasklet;

	    @Autowired
	    private EmpWriterTasklet writerTasklet;

	    @Bean
	    public Job Taskh2ToCsvJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	        return new JobBuilder("h2ToCsvJob", jobRepository)
	                .start(readerStep(jobRepository, transactionManager))
	                .next(processorStep(jobRepository, transactionManager))
	                .next(writerStep(jobRepository, transactionManager))
	                .build();
	    }

	    @Bean
	    public Step readerStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	        return new StepBuilder("readerStep", jobRepository)
	                .tasklet(readerTasklet, transactionManager)
	                .build();
	    }

	    @Bean
	    public Step processorStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	        return new StepBuilder("processorStep", jobRepository)
	                .tasklet(processorTasklet, transactionManager)
	                .build();
	    }

	    @Bean
	    public Step writerStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
	        return new StepBuilder("writerStep", jobRepository)
	                .tasklet(writerTasklet, transactionManager)
	                .build();
	    }

}
