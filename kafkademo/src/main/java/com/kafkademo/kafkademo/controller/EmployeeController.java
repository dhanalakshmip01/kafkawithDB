package com.kafkademo.kafkademo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafkademo.kafkademo.kafka.Producer;
import com.kafkademo.kafkademo.modal.Employee;
import com.kafkademo.kafkademo.repository.EmployeeRepository;



@RestController
@RequestMapping("/kafka")
public class EmployeeController {

	private final Producer producer;
	@Autowired
	public EmployeeController(Producer producer) {
	this.producer = producer;
	}
	
	@Autowired
	EmployeeRepository repository;
	
	@GetMapping(value="/publicDBtoTopic")
	public void sendMessageToKafkaTopic() {
		List<Employee> emplist =repository.getAllEmployees();		
		  emplist.parallelStream().forEach(employee ->
		  this.producer.sendMessage(employee));
		 	
		//this.producer.sendMessage(emplist);
		/*for (Employee employee : emplist) {
			String message = employee.getEmployeeId()+","+employee.getEmpName()+","+employee.getDesignation()+","+employee.getJobLevel()+","+employee.getSalary()+","+employee.getRating(); ;
			this.producer.sendMessage(message);
		}*/
		
	}
	
	/*
	 * @PostMapping(value = "/publish") public void
	 * sendMessageToKafkaTopic(@RequestParam("message") String message) {
	 * this.producer.sendMessage(message); }
	 */
}
