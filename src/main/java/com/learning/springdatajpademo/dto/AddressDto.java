package com.learning.springdatajpademo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressDto {

    private Long id;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String zipCode;

    // private PersonDto person;
}
