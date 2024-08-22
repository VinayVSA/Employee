package com.cg.in.TaskletConfig;

import org.springframework.batch.core.StepContribution;
//import org.springframework.batch.core.tasklet.Tasklet;
//import org.springframework.batch.core.tasklet.TaskletStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.in.EmployeeMapper;
import com.cg.in.entities.Employee;
import com.cg.in.entities.EmployeeBo;
import com.cg.in.repository.EmployeeRepository;

import java.util.Iterator;
import java.util.List;



@Component
public class EmpReaderTasklet implements Tasklet {

	    private final EmployeeRepository employeeRepository;
	    private final EmployeeMapper employeeMapper;
	    private Iterator<EmployeeBo> employeeIterator;

	    @Autowired
	    public EmpReaderTasklet(EmployeeRepository employeeRepository,EmployeeMapper employeeMapper) {
	    	this.employeeMapper = employeeMapper;
	    	this.employeeRepository = employeeRepository;
	    }

	    @Override
	    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        
	    	List<Employee> employees = employeeRepository.findAll(); // Fetch employees from repository
	        List<EmployeeBo> employeeBos = employeeMapper.toBOList(employees); // Convert to EmployeeBo list

	        // Store the list in the ExecutionContext
	        chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("employeeBoList", employeeBos);

	        return RepeatStatus.FINISHED;
	    }
}
