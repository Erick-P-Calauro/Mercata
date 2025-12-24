package com.mercata.inventarium.Vendors.Controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import com.mercata.inventarium.Configuration.MessagingConfiguration;
import com.mercata.inventarium.Exceptions.MissingValueException;
import com.mercata.inventarium.Vendors.DTOs.Vendor.VendorPayload;
import com.mercata.inventarium.Vendors.Services.VendorService;

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
