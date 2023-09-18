package com.catalisa.cybernomad.controller;

import com.catalisa.cybernomad.dto.HubDTO;
import com.catalisa.cybernomad.model.HubModel;
import com.catalisa.cybernomad.service.HubService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hubs")
public class HubController {

    @Autowired
    HubService hubService;

    @PostMapping
    public HubModel registerHub(@RequestBody HubModel hubModel) {
        return hubService.createHub(hubModel);
    }

    @GetMapping
    public List<HubDTO> searchAllHubs(){
        List<HubModel> hubs = hubService.searchAll();
        List<HubDTO> hubsDTO = new ArrayList<>();
        for (HubModel hubModel : hubs) {
            HubDTO dto = hubService.convertToDTO(hubModel);
            hubsDTO.add(dto);
        }
        return hubsDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HubDTO> searchHubById(@PathVariable Long id){
        HubModel hubModel = hubService.searchById(id).orElse(null);
        if (hubModel != null){
            HubDTO dto = hubService.convertToDTO(hubModel);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HubModel> alterHub(@PathVariable Long id, @RequestBody HubModel hubUpdate){
        HubModel hubModel = hubService.alter(id, hubUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHub(@PathVariable Long id){
        hubService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
