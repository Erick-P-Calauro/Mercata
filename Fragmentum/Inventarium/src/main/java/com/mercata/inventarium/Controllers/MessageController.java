package com.mercata.inventarium.Controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.mercata.inventarium.Configuration.MessagingConfiguration;

@Component
public class MessageController {

    @RabbitListener(queues = MessagingConfiguration.ORDER_QUEUE)
    public void listenOrderQueue(String in)  {
        System.out.println("ORDER QUEUE MESSAGE : " + in);
    }

    @RabbitListener(queues = MessagingConfiguration.VENDOR_QUEUE)
    public void listenVendorQueue(String in) {
        System.out.println("VENDOR QUEUE MESSAGE : " + in);
    }

}
