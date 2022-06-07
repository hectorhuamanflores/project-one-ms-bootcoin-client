package com.bank.bootcoinclient.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bootcoinclient.producer.KafkaProducer;


@RestController
@RequestMapping("/seller")
public class RequestConfirmationController {
   
	@Autowired
	private KafkaProducer kafkaProducer;
	
	@Value(value = "${kafka.topic.name-confirmation}")
	private String topic;
	
	@PostMapping("/validation")
	  public ResponseEntity<String> requestTransaction(@RequestBody String transaction) {
	    kafkaProducer.publishMessage(transaction,this.topic);
	    return ResponseEntity.ok(transaction);
	  }
}
