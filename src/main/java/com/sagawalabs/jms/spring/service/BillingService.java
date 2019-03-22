package com.sagawalabs.jms.spring.service;

import com.sagawalabs.jms.model.CustomerEvent;
import org.springframework.stereotype.Service;


@Service
public class BillingService {
	public String handleNewCustomer(CustomerEvent customerEvt){
		String message = "BillingService handleNewCustomer " + customerEvt.toString();
		return message;
	}
}
