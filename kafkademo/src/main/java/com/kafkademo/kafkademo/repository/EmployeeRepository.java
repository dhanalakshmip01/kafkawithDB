package com.kafkademo.kafkademo.repository;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kafkademo.kafkademo.modal.Employee;




@Repository
@PropertySource(value= {"classpath:Queries.properties"})
public class EmployeeRepository {

	@Autowired
	private Environment env;
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	 public List<Employee> getAllEmployees() {
		  RowMapper<Employee> rowMapper =  new BeanPropertyRowMapper<Employee>(Employee.class);		
		  List<Employee> list = jdbcTemplate.query(env.getProperty("FETCH_ALL_EMP"), rowMapper);		  
		  return list;
	 }
	 
	 
	 public String addEmployee(Employee employee) {			
	  jdbcTemplate.update(env.getProperty("ADD_EMP"), employee.getEmployeeId(), employee.getEmpName(), employee.getDesignation(),employee.getJobLevel(),employee.getSalary(),employee.getRating());
			return "employee saved Succesfully";
				  
	 }

}
