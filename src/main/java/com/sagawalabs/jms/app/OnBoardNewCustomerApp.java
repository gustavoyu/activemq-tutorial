package com.sagawalabs.jms.app;

import com.sagawalabs.jms.demo.ActiveMQMessageProducer;
import com.sagawalabs.jms.util.DataUtil;

public class OnBoardNewCustomerApp {
    public static void main(String[] args) {
        ActiveMQMessageProducer msgQueueSender = new ActiveMQMessageProducer("tcp://localhost:32768", "admin", "admin");
        try {
            msgQueueSender.setup(false, true, DataUtil.CUSTOMER_VTC_TOPIC);
            msgQueueSender.sendMessage("CUSTOMER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
