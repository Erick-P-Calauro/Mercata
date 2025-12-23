package com.mercata.inventarium.Controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import com.mercata.inventarium.Configuration.MessagingConfiguration;
import com.mercata.inventarium.DTO.Vendor.VendorPayload;
import com.mercata.inventarium.Errors.MissingValueException;
import com.mercata.inventarium.Services.VendorService;

@Component
public class MessageController {

    @Autowired
    VendorService vendorService;

    @RabbitListener(queues = MessagingConfiguration.ORDER_QUEUE)
    public void listenOrderQueue(String in)  {
        System.out.println("ORDER QUEUE MESSAGE : " + in);
    }

    @RabbitListener(queues = MessagingConfiguration.VENDOR_QUEUE)
    public void listenVendorQueue(Message<VendorPayload> vendorPayload) throws MissingValueException {
        vendorService.processVendorCreatedPayload(vendorPayload);
    }

}
