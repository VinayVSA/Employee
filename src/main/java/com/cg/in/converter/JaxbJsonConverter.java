package com.cg.in.converter;

import com.cg.in.entities.EmployeeBo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.springframework.stereotype.Component;

@Component
public class JaxbJsonConverter {

	private final ObjectMapper objectMapper;

    public JaxbJsonConverter() {
        // Register the JaxbAnnotationModule to the ObjectMapper
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JaxbAnnotationModule());
    }

    public String convertPojoToJson(EmployeeBo employee) throws Exception {
        // Convert POJO to JSON
        return objectMapper.writeValueAsString(employee);
    }

    public EmployeeBo convertJsonToPojo(String json, Class<EmployeeBo> class1) throws Exception {
        
        return objectMapper.readValue(json, EmployeeBo.class);
    }
	
	
}
