package com.kafkademo.kafkademo.kafka;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.kafkademo.kafkademo.modal.Employee;
import com.kafkademo.kafkademo.repository.EmployeeRepository;

@Service
public class Consumer {

	
	 private final Logger logger = LoggerFactory.getLogger(Consumer.class);
	 
		
		  @Autowired private Gson gson;
		 
		    @Autowired
		    private KafkaTemplate<String, String> kafkaTemplate;
		    
		    @Autowired
		    EmployeeRepository employeeRepository;
		    
		    @Value("${tpd.topic2}")
		    private String topic2;

	    @KafkaListener(topics = "${tpd.topic1}",groupId ="group-id")
	    public  void consume(String employ) throws IOException {	
	    	
	    	System.out.println("Employeee consumed data" + employ);
	    	double bonus =0;
	    	double salary =0;
	        logger.info(" Consumed message ->", employ);
			 Employee emp = gson.fromJson(employ, Employee.class); 
	        
	        bonus=	emp.getSalary()*10/100;
	        salary = emp.getSalary() +bonus;
	        Employee processedemp = new Employee();
	        processedemp.setEmployeeId(emp.getEmployeeId());
	        processedemp.setEmpName(emp.getEmpName());
	        processedemp.setDesignation(emp.getDesignation());
	        processedemp.setJobLevel(emp.getJobLevel());
	        processedemp.setSalary(salary);
	        processedemp.setRating(emp.getRating());	       
	        logger.info(String.format("$$ -> Producing message to topic2 --> %s", processedemp));     
	         this.kafkaTemplate.send(topic2,String.valueOf(processedemp.getEmployeeId()),gson.toJson(processedemp));
	    }
  
	    @KafkaListener(topics = "${tpd.topic2}")
	    public  void consumedata(String employee) throws IOException {
	    	logger.info(" Consumed message from topic2 ->", employee);
	    	 Employee emp = gson.fromJson(employee, Employee.class); 
			employeeRepository.addEmployee(emp);
			
	    }
}
