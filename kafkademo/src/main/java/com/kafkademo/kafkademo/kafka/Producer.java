package com.kafkademo.kafkademo.kafka;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.kafkademo.kafkademo.modal.Employee;



@Service
public class Producer {

	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	
    private String TOPIC ="topic1";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    private Gson gson;
   

    public void sendMessage(Employee emp) {
        logger.info(String.format("$$ -> Producing message --> %s", emp));    
        System.out.println("employee producing data ::"+ gson.toJson(emp));
         this.kafkaTemplate.send(TOPIC, gson.toJson(emp));
    }
}
