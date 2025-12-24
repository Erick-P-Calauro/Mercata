package com.mercata.inventarium.Vendors.DTOs.Vendor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VendorCreate {
    private String vendor_name;
    private String vendor_description;
}
