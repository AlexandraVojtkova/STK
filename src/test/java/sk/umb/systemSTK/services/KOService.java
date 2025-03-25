package sk.umb.systemSTK.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.systemSTK.persistent.entity.KOEntity;
import sk.umb.systemSTK.persistent.entity.TKEntity;
import sk.umb.systemSTK.persistent.repository.KORepository;
import sk.umb.systemSTK.persistent.repository.TechnicianRepository;
import sk.umb.systemSTK.persistent.repository.UserRepository;
import sk.umb.systemSTK.utils.KoDTO;
import sk.umb.systemSTK.utils.TkDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class KOService {
    @Autowired
    private KORepository koRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    public List<KoDTO> getAllKo() {
        List<KoDTO> allKo = new ArrayList<>();

        for (KOEntity koEntity : koRepository.findAll()) {
            KoDTO koDTO = new KoDTO();
            koDTO.setId(koEntity.getVINKO());
            koDTO.setDate(koEntity.getDate());
            koDTO.setControlType(koEntity.getControlType());
            koDTO.setCategory(koEntity.getCategory());
            koDTO.setTechnicianId(koEntity.getIdOfTechnician().getTechnicianId());
            koDTO.setPrice(koEntity.getPrice());
            allKo.add(koDTO);
        }
        return allKo;
    }

    public KoDTO getKOByCriteria(Date date, String controlType, String category, Long technicianId) {
        Optional<KOEntity> koEntityOptional = Optional.empty();

        if (date != null) {
            koEntityOptional = koRepository.findByDate(date);
        } else if (controlType != null) {
            koEntityOptional = koRepository.findByControlType(controlType);
        } else if (category != null) {
            koEntityOptional = koRepository.findByCategory(category);
        } else if (technicianId != null) {
            koEntityOptional = koRepository.findByTechnicianId(technicianId);
        }

        return koEntityOptional.map(koEntity -> {
            KoDTO koDTO = new KoDTO();
            koDTO.setId(koEntity.getVINKO());
            koDTO.setDate(koEntity.getDate());
            koDTO.setControlType(koEntity.getControlType());
            koDTO.setCategory(koEntity.getCategory());
            koDTO.setTechnicianId(koEntity.getIdOfTechnician().getTechnicianId());
            return koDTO;
        }).orElse(null);
    }

    public void putKO(Long technicianId, KoDTO koDTO) {

        KOEntity koEntity = koRepository.findById(technicianId).get();
        if (koEntity != null) {
            koEntity.setVINKO(koDTO.getId());
            koEntity.setDate(koDTO.getDate());
            koEntity.setControlType(koDTO.getControlType());
            koEntity.setCategory(koDTO.getCategory());
            koEntity.setPrice(koDTO.getPrice());
            koRepository.save(koEntity);
        }
    }
}
