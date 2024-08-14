package com.cg.in.TaskletConfig;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.cg.in.entities.Employee;
import com.cg.in.entities.EmployeeBo;

@Component
public class EmpWriterTasklet implements Tasklet{

	//private static final String FILE_HEADER = "ID,EmpName,Designation,Salary";
    private static final String FILE_NAME = "src/main/resources/employeesH2ToCsv.csv";

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
     List<EmployeeBo> employeeBos = (List<EmployeeBo>) chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("employeeBoList");
        
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (EmployeeBo employeeBo : employeeBos) {
                writer.write(String.format("%d,%s,%s,%.2f\n", employeeBo.getEmpId(), employeeBo.getEmpName(), employeeBo.getEmpDesignation(), employeeBo.getSalary()));
            }}catch (IOException e) {
            e.printStackTrace();
        }
        return RepeatStatus.FINISHED;
    }
	
}

