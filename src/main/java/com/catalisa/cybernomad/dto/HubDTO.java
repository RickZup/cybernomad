package com.catalisa.cybernomad.dto;

import com.catalisa.cybernomad.enums.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HubDTO {
    private String name;
    private AdressDTO adressDTO;
    private String observation;
    private String openingHours;
    private List<Resource> resources;
}
