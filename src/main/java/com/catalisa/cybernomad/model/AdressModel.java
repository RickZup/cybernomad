package com.catalisa.cybernomad.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_ADRESS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private Long number;
    private String city;
    private String state;
    private String country;
    @Column(nullable = true)
    private String zipCode;
    @Column(nullable = true)
    private String reference;
}
