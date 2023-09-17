package com.catalisa.cybernomad.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdressDTO {
    private String street;
    private Long number;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String reference;
}
