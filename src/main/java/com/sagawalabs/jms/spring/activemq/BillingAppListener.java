package com.sagawalabs.jms.spring.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.sagawalabs.jms.spring.service.BillingService;
import com.sagawalabs.jms.spring.service.MessageTransformer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.sagawalabs.jms.util.DataUtil;
import com.sagawalabs.jms.model.CustomerEvent;

@Component
public class BillingAppListener {

    @Autowired
    private JmsTemplate jmsQueueTemplate;

    @Autowired
    private BillingService billingService;

    @Autowired
    private MessageTransformer msgTransformer;

    private String queueName = "Consumer.Billing." + DataUtil.CUSTOMER_VTC_TOPIC;

    public String receiveMessage() throws JMSException {
        System.out.println(Thread.currentThread().getName() + ": BillingAppListener receiveMessage.");

        Destination destination = new ActiveMQQueue(queueName);
        TextMessage textMessage = (TextMessage) jmsQueueTemplate.receive(destination);

        CustomerEvent customerEvt = msgTransformer.fromJson(textMessage.getText(), CustomerEvent.class);
        return billingService.handleNewCustomer(customerEvt);
    }
}