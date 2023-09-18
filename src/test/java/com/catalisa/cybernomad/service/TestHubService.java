package com.catalisa.cybernomad.service;

import com.catalisa.cybernomad.dto.AdressDTO;
import com.catalisa.cybernomad.dto.HubDTO;
import com.catalisa.cybernomad.model.AdressModel;
import com.catalisa.cybernomad.model.HubModel;
import com.catalisa.cybernomad.repository.HubRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestHubService {

    @Mock
    private HubRepository hubRepository;

    @Mock
    private AdressService adressService;

    @InjectMocks
    private HubService hubService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateHub() {
        HubModel hubModel = new HubModel();
        hubModel.setId(1L);
        hubModel.setName("Hub Teste");

        when(hubRepository.save(any(HubModel.class))).thenReturn(hubModel);

        HubModel result = hubService.createHub(hubModel);

        verify(hubRepository, times(1)).save(hubModel);
        assertEquals(hubModel, result);
    }

    @Test
    public void testSearchAllHubs() {
        HubModel hub1 = new HubModel();
        hub1.setId(1L);
        hub1.setName("Hub 1");
        hub1.setAdressModel(new AdressModel());

        HubModel hub2 = new HubModel();
        hub2.setId(2L);
        hub2.setName("Hub 2");
        hub2.setAdressModel(new AdressModel());

        when(hubRepository.findAll()).thenReturn(Arrays.asList(hub1, hub2));

        List<HubModel> result = hubService.searchAll();

        verify(hubRepository, times(1)).findAll();
        assertEquals(Arrays.asList(hub1, hub2), result);
    }

    @Test
    public void testSearchHubById() {
        Long knownId = 1L;
        HubModel knownHub = new HubModel();
        knownHub.setId(knownId);
        knownHub.setName("Hub Teste");
        knownHub.setAdressModel(new AdressModel());

        when(hubRepository.findById(knownId)).thenReturn(Optional.of(knownHub));

        Optional<HubModel> result = hubService.searchById(knownId);

        verify(hubRepository, times(1)).findById(knownId);
        assertEquals(Optional.of(knownHub), result);
    }

    @Test
    public void testAlterHub() {
        HubModel existingHub = new HubModel();
        existingHub.setId(1L);
        existingHub.setName("Hub Antigo");
        existingHub.setAdressModel(new AdressModel());

        HubModel updatedHub = new HubModel();
        updatedHub.setId(1L);
        updatedHub.setName("Hub Novo");
        updatedHub.setAdressModel(new AdressModel());

        when(hubRepository.findById(1L)).thenReturn(Optional.of(existingHub));
        when(hubRepository.save(any(HubModel.class))).thenReturn(updatedHub);

        HubModel result = hubService.alter(1L, updatedHub);

        verify(hubRepository, times(1)).findById(1L);
        verify(hubRepository, times(1)).save(existingHub);
        assertEquals(updatedHub, result);
    }

    @Test
    public void testDeleteHub() {
        Long hubIdToDelete = 1L;

        hubService.delete(hubIdToDelete);

        verify(hubRepository, times(1)).deleteById(hubIdToDelete);
    }

    @Test
    public void testConvertToDTO() {
        HubModel hubModel = new HubModel();
        hubModel.setName("Hub Exemplo");
        hubModel.setAdressModel(new AdressModel());

        HubDTO dto = new HubDTO();
        dto.setName("Hub Exemplo");

        when(adressService.convertToDTO(hubModel.getAdressModel())).thenReturn(new AdressDTO());

        HubDTO result = hubService.convertToDTO(hubModel);

        assertEquals(dto.getName(), result.getName());
    }
}

