package com.catalisa.cybernomad.model;

import com.catalisa.cybernomad.enums.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_HUB")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HubModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hub_id")
    private Long id;
    private String name;
    @OneToOne
    private AdressModel adressModel;
    private String observation;
    private String openingHours;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "HUB_RESOURCES")
    @Column(name = "RESOURCES")
    private List<Resource> resources;
}
