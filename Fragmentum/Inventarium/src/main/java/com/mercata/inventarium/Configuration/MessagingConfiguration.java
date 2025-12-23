package com.mercata.inventarium.Configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

    public static final String ORDER_QUEUE = "inventarium_order_queue";
    public static final String VENDOR_QUEUE = "inventarium_vendor_queue";

    @Bean
    public Queue getInventariumQueue () {
        return new Queue(ORDER_QUEUE);
    }

    @Bean
    public Queue getVendorQueue () {
        return new Queue(VENDOR_QUEUE);
    }

}
