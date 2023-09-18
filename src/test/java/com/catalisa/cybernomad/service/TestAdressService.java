package com.catalisa.cybernomad.service;

import com.catalisa.cybernomad.dto.AdressDTO;
import com.catalisa.cybernomad.model.AdressModel;
import com.catalisa.cybernomad.repository.AdressRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;
import java.util.Optional;
@RunWith(MockitoJUnitRunner.class)
public class TestAdressService {

    @Mock
    private AdressRepository adressRepository;

    @InjectMocks
    private AdressService adressService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterAdress() {
        AdressModel adressModel = new AdressModel();
        adressModel.setId(1L);
        adressModel.setStreet("Rua Teste");

        when(adressRepository.save(any(AdressModel.class))).thenReturn(adressModel);

        AdressModel result = adressService.register(adressModel);

        verify(adressRepository, times(1)).save(adressModel);
        assertEquals(adressModel, result);
    }

    @Test
    public void testSearchAllAdresses() {
        AdressModel adress1 = new AdressModel();
        adress1.setId(1L);
        adress1.setStreet("Rua Teste 1");

        AdressModel adress2 = new AdressModel();
        adress2.setId(2L);
        adress2.setStreet("Rua Teste 2");

        when(adressRepository.findAll()).thenReturn(Arrays.asList(adress1, adress2));
        List<AdressModel> result = adressService.searchAll();
        verify(adressRepository, times(1)).findAll();
        assertEquals(Arrays.asList(adress1, adress2), result);
    }

    @Test
    public void testSearchAdressById() {
        Long knownId = 1L;
        AdressModel knownAdress = new AdressModel();
        knownAdress.setId(knownId);
        knownAdress.setStreet("Rua Teste");

        when(adressRepository.findById(knownId)).thenReturn(Optional.of(knownAdress));

        Optional<AdressModel> result = adressService.searchById(knownId);
        verify(adressRepository, times(1)).findById(knownId);
        assertEquals(Optional.of(knownAdress), result);
    }

    @Test
    public void testAlterAdress() {
        AdressModel existingAdress = new AdressModel();
        existingAdress.setId(1L);
        existingAdress.setStreet("Rua Antiga");

        AdressModel updatedAdress = new AdressModel();
        updatedAdress.setId(1L);
        updatedAdress.setStreet("Rua Nova");

        when(adressRepository.findById(1L)).thenReturn(Optional.of(existingAdress));
        when(adressRepository.save(any(AdressModel.class))).thenReturn(updatedAdress);

        AdressModel result = adressService.alter(1L, updatedAdress);

        verify(adressRepository, times(1)).findById(1L);
        verify(adressRepository, times(1)).save(existingAdress);
        assertEquals(updatedAdress, result);
    }

    @Test
    public void testDeleteAdress() {
        Long adressIdToDelete = 1L;

        adressService.delete(adressIdToDelete);

        verify(adressRepository, times(1)).deleteById(adressIdToDelete);
    }

    @Test
    public void testConvertToDTO() {
        AdressModel adressModel = new AdressModel();
        adressModel.setStreet("Rua Exemplo");
        adressModel.setNumber(123L);

        AdressDTO dto = adressService.convertToDTO(adressModel);

        assertEquals(adressModel.getStreet(), dto.getStreet());
        assertEquals(adressModel.getNumber(), dto.getNumber());
    }

}
