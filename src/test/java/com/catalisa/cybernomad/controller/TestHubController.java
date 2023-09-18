package com.catalisa.cybernomad.controller;

import com.catalisa.cybernomad.dto.AdressDTO;
import com.catalisa.cybernomad.dto.HubDTO;
import com.catalisa.cybernomad.enums.Resource;
import com.catalisa.cybernomad.model.AdressModel;
import com.catalisa.cybernomad.model.HubModel;
import com.catalisa.cybernomad.service.AdressService;
import com.catalisa.cybernomad.service.HubService;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HubController.class)
public class TestHubController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HubController hubController;

    @MockBean
    HubService hubService;

    @MockBean
    HubModel hubModel;

    @MockBean
    AdressModel adressModel;

    @Test
    public void testRegisterHub() throws Exception {
        HubModel exampleHub = new HubModel();
        exampleHub.setName("Exemplo Hub");
        exampleHub.setObservation("Exemplo Observation");
        exampleHub.setOpeningHours("Exemplo OpeningHours");
        exampleHub.setResources(Arrays.asList(Resource.INTERNET, Resource.ENERGY));

        AdressModel exampleAdress = new AdressModel();
        exampleAdress.setStreet("Exemplo Street");
        exampleAdress.setNumber(123L);
        exampleAdress.setCity("Exemplo City");
        exampleAdress.setState("Exemplo State");
        exampleAdress.setCountry("Exemplo Country");
        exampleAdress.setZipCode("Exemplo ZipCode");
        exampleAdress.setReference("Exemplo Reference");

        exampleHub.setResources(Arrays.asList(Resource.INTERNET, Resource.ENERGY));
        exampleHub.setAdressModel(exampleAdress);

        Mockito.when(hubService.createHub(Mockito.any(HubModel.class))).thenReturn(exampleHub);

        ObjectMapper objectMapper = new ObjectMapper();
        String hubJson = objectMapper.writeValueAsString(exampleHub);

        mockMvc.perform(MockMvcRequestBuilders.post("/hubs")
                        .content(hubJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Exemplo Hub"))
                .andExpect(jsonPath("$.observation").value("Exemplo Observation"))
                .andExpect(jsonPath("$.openingHours").value("Exemplo OpeningHours"))
                .andExpect(jsonPath("$.resources[0]").value("INTERNET"))
                .andExpect(jsonPath("$.resources[1]").value("ENERGY"))
                .andExpect(jsonPath("$.adressModel.street").value("Exemplo Street"))
                .andExpect(jsonPath("$.adressModel.number").value(123))
                .andExpect(jsonPath("$.adressModel.city").value("Exemplo City"))
                .andExpect(jsonPath("$.adressModel.state").value("Exemplo State"))
                .andExpect(jsonPath("$.adressModel.country").value("Exemplo Country"))
                .andExpect(jsonPath("$.adressModel.zipCode").value("Exemplo ZipCode"))
                .andExpect(jsonPath("$.adressModel.reference").value("Exemplo Reference"));
    }

    @Test
    public void testSearchAllHubs() throws Exception {
        HubModel hub1 = new HubModel();
        hub1.setName("Hub 1");

        HubModel hub2 = new HubModel();
        hub2.setName("Hub 2");

        List<HubModel> hubList = Arrays.asList(hub1, hub2);

        HubDTO hubDTO1 = new HubDTO();
        hubDTO1.setName("Hub 1");
        hubDTO1.setAdressDTO(new AdressDTO("Street 1", 123L, "City 1", "State 1", "Country 1", "12345", "Reference 1"));

        HubDTO hubDTO2 = new HubDTO();
        hubDTO2.setName("Hub 2");
        hubDTO2.setAdressDTO(new AdressDTO("Street 2", 456L, "City 2", "State 2", "Country 2", "67890", "Reference 2"));

        List<HubDTO> expectedDTOList = Arrays.asList(hubDTO1, hubDTO2);

        when(hubService.searchAll()).thenReturn(hubList);
        when(hubService.convertToDTO(hub1)).thenReturn(hubDTO1);
        when(hubService.convertToDTO(hub2)).thenReturn(hubDTO2);

        mockMvc.perform(get("/hubs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(hubDTO1.getName()))
                .andExpect(jsonPath("$[0].adressDTO.street").value(hubDTO1.getAdressDTO().getStreet()))
                .andExpect(jsonPath("$[0].adressDTO.number").value(hubDTO1.getAdressDTO().getNumber()))
                .andExpect(jsonPath("$[0].adressDTO.city").value(hubDTO1.getAdressDTO().getCity()))
                .andExpect(jsonPath("$[0].adressDTO.state").value(hubDTO1.getAdressDTO().getState()))
                .andExpect(jsonPath("$[0].adressDTO.country").value(hubDTO1.getAdressDTO().getCountry()))
                .andExpect(jsonPath("$[0].adressDTO.zipCode").value(hubDTO1.getAdressDTO().getZipCode()))
                .andExpect(jsonPath("$[0].adressDTO.reference").value(hubDTO1.getAdressDTO().getReference()))
                .andExpect(jsonPath("$[1].name").value(hubDTO2.getName()))
                .andExpect(jsonPath("$[1].adressDTO.street").value(hubDTO2.getAdressDTO().getStreet()))
                .andExpect(jsonPath("$[1].adressDTO.number").value(hubDTO2.getAdressDTO().getNumber()))
                .andExpect(jsonPath("$[1].adressDTO.city").value(hubDTO2.getAdressDTO().getCity()))
                .andExpect(jsonPath("$[1].adressDTO.state").value(hubDTO2.getAdressDTO().getState()))
                .andExpect(jsonPath("$[1].adressDTO.country").value(hubDTO2.getAdressDTO().getCountry()))
                .andExpect(jsonPath("$[1].adressDTO.zipCode").value(hubDTO2.getAdressDTO().getZipCode()))
                .andExpect(jsonPath("$[1].adressDTO.reference").value(hubDTO2.getAdressDTO().getReference()));
    }

    @Test
    public void testSearchHubById() throws Exception {
        Long hubId = 1L;
        HubModel hubModel = new HubModel();
        hubModel.setId(hubId);
        hubModel.setName("Hub 1");

        HubDTO hubDTO = new HubDTO();
        hubDTO.setName(hubModel.getName());
        hubDTO.setAdressDTO(new AdressDTO("Street 1", 123L, "City 1", "State 1", "Country 1", "12345", "Reference 1"));

        when(hubService.searchById(hubId)).thenReturn(Optional.of(hubModel));
        when(hubService.convertToDTO(hubModel)).thenReturn(hubDTO);

        mockMvc.perform(get("/hubs/{id}", hubId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(hubDTO.getName()))
                .andExpect(jsonPath("$.adressDTO.street").value(hubDTO.getAdressDTO().getStreet()))
                .andExpect(jsonPath("$.adressDTO.number").value(hubDTO.getAdressDTO().getNumber()))
                .andExpect(jsonPath("$.adressDTO.city").value(hubDTO.getAdressDTO().getCity()))
                .andExpect(jsonPath("$.adressDTO.state").value(hubDTO.getAdressDTO().getState()))
                .andExpect(jsonPath("$.adressDTO.country").value(hubDTO.getAdressDTO().getCountry()))
                .andExpect(jsonPath("$.adressDTO.zipCode").value(hubDTO.getAdressDTO().getZipCode()))
                .andExpect(jsonPath("$.adressDTO.reference").value(hubDTO.getAdressDTO().getReference()));
    }

    @Test
    public void testAlterHub() throws Exception {
        HubModel hubUpdate = new HubModel();
        hubUpdate.setName("Hub Atualizado");
        hubUpdate.setObservation("Observação Atualizada");
        hubUpdate.setOpeningHours("Novo Horário");

        when(hubService.alter(any(Long.class), any(HubModel.class))).thenReturn(hubUpdate);

        mockMvc.perform(MockMvcRequestBuilders.put("/hubs/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(hubUpdate)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testDeleteHub() throws Exception {
        Long hubId = 1L; // ID do hub que deseja excluir

        mockMvc.perform(MockMvcRequestBuilders.delete("/hubs/{id}", hubId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
