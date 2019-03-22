package com.sagawalabs.jms.spring.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import com.sagawalabs.jms.model.CustomerEvent;
import com.sagawalabs.jms.spring.service.MessageTransformer;
import com.sagawalabs.jms.spring.service.SupportService;
import com.sagawalabs.jms.util.DataUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class SupportAppListener {

    @Autowired
    private JmsTemplate jmsQueueTemplate;

    @Autowired
    private SupportService supportService;

    @Autowired
    private MessageTransformer msgTransformer;

    private String queueName = "Consumer.Support." + DataUtil.CUSTOMER_VTC_TOPIC;

    public String receiveMessage() throws JMSException {
        System.out.println(Thread.currentThread().getName() + ": SupportAppListener receiveMessage." );

        Destination destination = new ActiveMQQueue(queueName);
        TextMessage textMessage = (TextMessage) jmsQueueTemplate.receive(destination);

        CustomerEvent customerEvt = msgTransformer.fromJson(textMessage.getText(), CustomerEvent.class);
        return supportService.handleNewCustomer(customerEvt);
    }
}