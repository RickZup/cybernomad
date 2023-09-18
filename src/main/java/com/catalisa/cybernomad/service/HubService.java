    package com.catalisa.cybernomad.service;

    import com.catalisa.cybernomad.dto.HubDTO;
    import com.catalisa.cybernomad.model.HubModel;
    import com.catalisa.cybernomad.repository.HubRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class HubService {
        @Autowired
        HubRepository hubRepository;

        @Autowired
        AdressService adressService;

        public HubModel createHub(HubModel hubModel) {
            return hubRepository.save(hubModel);
        }

        public List<HubModel> searchAll(){
            return hubRepository.findAll();
        }

        public Optional<HubModel> searchById(Long id){
            return hubRepository.findById(id);
        }

        public HubModel alter(Long id, HubModel hubUpdate){
            HubModel hub = hubRepository.findById(id).get();
            if (hub != null){
                hub.setName(hubUpdate.getName());
            }
            if (hub != null){
                hub.setObservation(hubUpdate.getObservation());
            }
            if (hub != null){
                hub.setOpeningHours(hubUpdate.getOpeningHours());
            }
            return hubRepository.save(hub);
        }

        public void delete(Long id){
            hubRepository.deleteById(id);
        }

        public HubDTO convertToDTO(HubModel hubModel) {
            HubDTO dto = new HubDTO();
            dto.setName(hubModel.getName());
            dto.setObservation(hubModel.getObservation());
            dto.setOpeningHours(hubModel.getOpeningHours());
            dto.setResources(hubModel.getResources());
            dto.setAdressDTO(adressService.convertToDTO(hubModel.getAdressModel()));
            return dto;
        }
    }
