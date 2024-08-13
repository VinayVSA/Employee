package com.cg.in.config;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.StepBuilderHelper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.PassThroughFieldExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.cg.in.entities.Employee;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
@Configuration
//@EnableBatchProcessing
public class BatchConfig{

	@Bean
	//@Primary
    @Qualifier("h2ToCsvJob")
    public Job h2ToCsvJob(JobRepository jobRepository,
                         JobListener listener,
                         @Qualifier("h2ToCsvStep") Step h2ToCsvStep) {
        return new JobBuilder("H2ToCsvJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .start(h2ToCsvStep)
                .build();
    }

    @Bean
   // @Primary
    @Qualifier("h2ToCsvStep")
    public Step h2ToCsvStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           EmployeeItemReader reader,
                           EmployeeItemProcessor processor,
                           EmployeeItemWriter writer) {
        return new StepBuilder("H2ToCsvStep", jobRepository)
                .<Employee, Employee>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

}

