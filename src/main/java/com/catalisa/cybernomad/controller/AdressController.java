package com.catalisa.cybernomad.controller;

import com.catalisa.cybernomad.dto.AdressDTO;
import com.catalisa.cybernomad.model.AdressModel;
import com.catalisa.cybernomad.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/adress")
public class AdressController {
    @Autowired
    AdressService adressService;

    //VERIFICAR: Ao cadastrar um novo endereço a requisição responde o corpo da entidade com o ID
    @PostMapping
    public AdressModel registerAdress(@RequestBody AdressModel adressModel){
        return adressService.register(adressModel);
    }

    @GetMapping
    public List<AdressDTO> searchAllAdress (){
        List<AdressModel> adressModelList = adressService.searchAll();
        List<AdressDTO> adressDTOList = new ArrayList<>();
        for (AdressModel adressModel : adressModelList) {
            AdressDTO dto = adressService.convertToDTO(adressModel);
            adressDTOList.add(dto);
        }
        return adressDTOList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdressDTO> searchAdressById(@PathVariable Long id){
        AdressModel adressModel = adressService.searchById(id).orElse(null);
        if (adressModel != null){
            AdressDTO dto = adressService.convertToDTO(adressModel);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdressModel> alterAdress(@PathVariable Long id, @RequestBody AdressModel adressUpdate){
        AdressModel adress = adressService.alter(id, adressUpdate);
        return ResponseEntity.ok(adress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdress(@PathVariable Long id){
        adressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
