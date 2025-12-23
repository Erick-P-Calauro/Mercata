package com.mercata.inventarium.DTO.Vendor;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VendorResponse {
    private UUID vendor_id;
    private String vendor_name;
    private String vendor_description;
}
