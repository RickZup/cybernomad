package com.catalisa.cybernomad.service;

import com.catalisa.cybernomad.dto.AdressDTO;
import com.catalisa.cybernomad.model.AdressModel;
import com.catalisa.cybernomad.repository.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdressService {

    @Autowired
    private AdressRepository adressRepository;

    public AdressModel register(AdressModel adressModel){
        return adressRepository.save(adressModel);
    }

    public List<AdressModel> searchAll(){
        return adressRepository.findAll();
    }

    public Optional<AdressModel> searchById(Long id){
        return adressRepository.findById(id);
    }

    public AdressModel alter(Long id, AdressModel adressModelupdate){
        AdressModel adress = adressRepository.findById(id).get();
        if (adress != null){
            adress.setStreet(adressModelupdate.getStreet());
        }
        return adressRepository.save(adress);
    }

    public void delete(Long id){
        adressRepository.deleteById(id);
    }

    public AdressDTO convertToDTO(AdressModel adressModel) {
        AdressDTO dto = new AdressDTO();
        dto.setStreet(adressModel.getStreet());
        dto.setNumber(adressModel.getNumber());
        dto.setCity(adressModel.getCity());
        dto.setState(adressModel.getState());
        dto.setCountry(adressModel.getCountry());
        dto.setZipCode(adressModel.getZipCode());
        dto.setReference(adressModel.getReference());
        return dto;
    }
}
