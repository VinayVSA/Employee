package com.cg.in.TaskletConfig;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.batch.core.Job;

@RestController
@RequestMapping("/bt")
public class BtController {
	
	    @Autowired
	    private JobLauncher jobLauncher;

	    @Autowired
	    @Qualifier("Taskh2ToCsvJob")
	    private Job Taskh2ToCsvJob;

	    @GetMapping("/startTASKH2ToCsvJob")
	    public BatchStatus startH2ToCsvJob() throws Exception {
	        JobParameters jobParameters = new JobParametersBuilder()
	                .addLong("time", System.currentTimeMillis())
	                .toJobParameters();

	        return jobLauncher.run(Taskh2ToCsvJob, jobParameters).getStatus();
	    }

}
