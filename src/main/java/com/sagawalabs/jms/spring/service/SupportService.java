package com.sagawalabs.jms.spring.service;

import com.sagawalabs.jms.model.CustomerEvent;
import org.springframework.stereotype.Service;


@Service
public class SupportService {

	public String handleNewCustomer(CustomerEvent customerEvt) {
		String message = "SupportService handleNewCustomer " + customerEvt.toString();
		return message;
	}

}
