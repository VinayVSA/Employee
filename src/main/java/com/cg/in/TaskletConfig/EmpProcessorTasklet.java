package com.cg.in.TaskletConfig;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.cg.in.entities.Employee;
import com.cg.in.entities.EmployeeBo;

@Component
public class EmpProcessorTasklet implements Tasklet{

	
	 @Override
	    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		 List<EmployeeBo> employeeBoList = (List<EmployeeBo>) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("employeeBoList");

	        // Process each EmployeeBo in the list
	        for (EmployeeBo employeeBo : employeeBoList) {
	            // Your processing logic here
	        }

	        // Store the processed list back if needed
	        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("processedEmployeeBoList", employeeBoList);

	        return RepeatStatus.FINISHED;
	    }
	
}
