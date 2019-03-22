package com.sagawalabs.jms.spring.app;

import java.net.URISyntaxException;

import com.sagawalabs.jms.spring.activemq.BillingAppListener;
import com.sagawalabs.jms.spring.config.JmsConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBillingForNewCustomerApp {
    public static void main(String[] args) throws URISyntaxException, Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JmsConfig.class);
        context.register(ConfigBillingForNewCustomerApp.class);

        try {
            BillingAppListener billingAppListener = (BillingAppListener) context.getBean("billingAppListener");
            System.out.println("ConfigBillingForewCustomerApp receives " + billingAppListener.receiveMessage());
        } finally {
            context.close();
        }
    }

}