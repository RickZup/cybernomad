package com.catalisa.cybernomad.controller;

import com.catalisa.cybernomad.model.HubModel;
import com.catalisa.cybernomad.service.HubService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hubs")
public class HubController {

    @Autowired
    HubService hubService;

    //----------CREATE----------
    @PostMapping
    public HubModel registerHub(@RequestBody HubModel hubModel) {
        return hubService.createHub(hubModel);
    }

    //----------READ ALL----------
    @GetMapping
    public List<HubModel> searchAllHubs(){
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new Hibernate5Module());
//        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return hubService.searchAll();
    }
}
