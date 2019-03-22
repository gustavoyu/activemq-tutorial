package com.sagawalabs.jms.spring.app;

import java.net.URISyntaxException;

import com.sagawalabs.jms.spring.activemq.SupportAppListener;
import com.sagawalabs.jms.spring.config.JmsConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigSupportforNewCustomerApp {
    public static void main(String[] args) throws URISyntaxException, Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JmsConfig.class);
        context.register(ConfigSupportforNewCustomerApp.class);

        try {
            SupportAppListener supportAppListener = (SupportAppListener) context.getBean("supportAppListener");
            System.out.println("supportAppListener receives " + supportAppListener.receiveMessage());
        } finally {
            context.close();
        }
    }
}
