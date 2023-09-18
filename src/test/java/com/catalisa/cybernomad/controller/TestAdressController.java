package com.catalisa.cybernomad.controller;

import com.catalisa.cybernomad.dto.AdressDTO;
import com.catalisa.cybernomad.model.AdressModel;
import com.catalisa.cybernomad.service.AdressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@WebMvcTest(AdressController.class)
public class TestAdressController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdressController adressController;

    @MockBean
    AdressService adressService;

    @MockBean
    AdressModel adressModel;

    @Test
    public void testRegisterAdress() throws Exception {
        AdressModel exampleAdress = new AdressModel();
        exampleAdress.setStreet("Exemplo Street");
        exampleAdress.setNumber(123L);
        exampleAdress.setCity("Exemplo City");
        exampleAdress.setState("Exemplo State");
        exampleAdress.setCountry("Exemplo Country");
        exampleAdress.setZipCode("Exemplo ZipCode");
        exampleAdress.setReference("Exemplo Reference");

        ObjectMapper objectMapper = new ObjectMapper();
        String adressJson = objectMapper.writeValueAsString(exampleAdress);

        when(adressService.register(any(AdressModel.class))).thenReturn(exampleAdress);

        mockMvc.perform(post("/adress")
                        .content(adressJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.street").value("Exemplo Street"))
                .andExpect(jsonPath("$.number").value(123))
                .andExpect(jsonPath("$.city").value("Exemplo City"))
                .andExpect(jsonPath("$.state").value("Exemplo State"))
                .andExpect(jsonPath("$.country").value("Exemplo Country"))
                .andExpect(jsonPath("$.zipCode").value("Exemplo ZipCode"))
                .andExpect(jsonPath("$.reference").value("Exemplo Reference"))
                .andReturn();
    }

    @Test
    public void testSearchAllAdress() throws Exception {
        AdressModel adress1 = new AdressModel(1L, "Street 1", 123L, "City 1", "State 1", "Country 1", "12345", "Reference 1");
        AdressModel adress2 = new AdressModel(2L, "Street 2", 456L, "City 2", "State 2", "Country 2", "67890", "Reference 2");

        List<AdressModel> adressModelList = new ArrayList<>();
        adressModelList.add(adress1);
        adressModelList.add(adress2);

        AdressDTO dto1 = new AdressDTO("Street 1", 123L, "City 1", "State 1", "Country 1", "12345", "Reference 1");
        AdressDTO dto2 = new AdressDTO("Street 2", 456L, "City 2", "State 2", "Country 2", "67890", "Reference 2");

        List<AdressDTO> expectedDTOList = new ArrayList<>();
        expectedDTOList.add(dto1);
        expectedDTOList.add(dto2);

        when(adressService.searchAll()).thenReturn(adressModelList);
        when(adressService.convertToDTO(adress1)).thenReturn(dto1);
        when(adressService.convertToDTO(adress2)).thenReturn(dto2);

        mockMvc.perform(MockMvcRequestBuilders.get("/adress")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].street").value(dto1.getStreet()))
                .andExpect(jsonPath("$[0].number").value(dto1.getNumber()))
                .andExpect(jsonPath("$[0].city").value(dto1.getCity()))
                .andExpect(jsonPath("$[0].state").value(dto1.getState()))
                .andExpect(jsonPath("$[0].country").value(dto1.getCountry()))
                .andExpect(jsonPath("$[0].zipCode").value(dto1.getZipCode()))
                .andExpect(jsonPath("$[0].reference").value(dto1.getReference()))
                .andExpect(jsonPath("$[1].street").value(dto2.getStreet()))
                .andExpect(jsonPath("$[1].number").value(dto2.getNumber()))
                .andExpect(jsonPath("$[1].city").value(dto2.getCity()))
                .andExpect(jsonPath("$[1].state").value(dto2.getState()))
                .andExpect(jsonPath("$[1].country").value(dto2.getCountry()))
                .andExpect(jsonPath("$[1].zipCode").value(dto2.getZipCode()))
                .andExpect(jsonPath("$[1].reference").value(dto2.getReference()));
    }

    @Test
    public void testSearchAdressById() throws Exception {
        AdressModel adress = new AdressModel(1L, "Street 1", 123L, "City 1", "State 1", "Country 1", "12345", "Reference 1");
        AdressDTO dto = new AdressDTO("Street 1", 123L, "City 1", "State 1", "Country 1", "12345", "Reference 1");

        when(adressService.searchById(1L)).thenReturn(Optional.of(adress));
        when(adressService.convertToDTO(adress)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/adress/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.street").value(dto.getStreet()))
                .andExpect(jsonPath("$.number").value(dto.getNumber()))
                .andExpect(jsonPath("$.city").value(dto.getCity()))
                .andExpect(jsonPath("$.state").value(dto.getState()))
                .andExpect(jsonPath("$.country").value(dto.getCountry()))
                .andExpect(jsonPath("$.zipCode").value(dto.getZipCode()))
                .andExpect(jsonPath("$.reference").value(dto.getReference()));

        mockMvc.perform(MockMvcRequestBuilders.get("/adress/{id}", 999)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAlterAdress() throws Exception {
        Long adressId = 1L;

        AdressModel updatedAdress = new AdressModel();
        updatedAdress.setId(adressId);
        updatedAdress.setStreet("Street Updated");
        updatedAdress.setNumber(456L);
        updatedAdress.setCity("City Updated");
        updatedAdress.setState("State Updated");
        updatedAdress.setCountry("Country Updated");
        updatedAdress.setZipCode("67890");
        updatedAdress.setReference("Reference Updated");

        when(adressService.alter(eq(adressId), Mockito.any(AdressModel.class))).thenReturn(updatedAdress);

        mockMvc.perform(MockMvcRequestBuilders.put("/adress/{id}", adressId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"street\": \"Street Updated\", \"number\": 456, \"city\": \"City Updated\", " +
                                "\"state\": \"State Updated\", \"country\": \"Country Updated\", \"zipCode\": \"67890\", " +
                                "\"reference\": \"Reference Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.street").value("Street Updated"))
                .andExpect(jsonPath("$.number").value(456))
                .andExpect(jsonPath("$.city").value("City Updated"))
                .andExpect(jsonPath("$.state").value("State Updated"))
                .andExpect(jsonPath("$.country").value("Country Updated"))
                .andExpect(jsonPath("$.zipCode").value("67890"))
                .andExpect(jsonPath("$.reference").value("Reference Updated"));
    }

    @Test
    public void testDeleteAdress() throws Exception {
        Long adressId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/adress/{id}", adressId))
                .andExpect(status().isNoContent());

        verify(adressService, times(1)).delete(adressId);
    }
}
