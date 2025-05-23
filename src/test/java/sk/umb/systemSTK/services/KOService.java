package sk.umb.systemSTK.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.systemSTK.persistent.entity.KOEntity;
import sk.umb.systemSTK.persistent.entity.TechnicianControlIdentificatorsEntity;
import sk.umb.systemSTK.persistent.repository.KORepository;
import sk.umb.systemSTK.persistent.repository.TechnicianControlIdentificatorsRepository;
import sk.umb.systemSTK.persistent.repository.TechnicianRepository;
import sk.umb.systemSTK.persistent.repository.UserRepository;
import sk.umb.systemSTK.utils.KoDTO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class KOService {
    @Autowired
    private KORepository koRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechnicianControlIdentificatorsRepository technicianControlIdentificatorsRepository;

    public List<KoDTO> getAllKo() {
        List<KoDTO> allKo = new ArrayList<>();

        for (KOEntity koEntity : koRepository.findAll()) {
            KoDTO koDTO = new KoDTO();
            koDTO.setId(koEntity.getVINKO());
            koDTO.setDate(koEntity.getDate());
            koDTO.setControlType(koEntity.getControlType());
            koDTO.setCategory(koEntity.getCategory());
            if (koEntity.getTechnicianIdentifier() != null) {
                koDTO.setTechnicianIdentifier(koEntity.getTechnicianIdentifier().getIdentifier());
            } else {
                koDTO.setTechnicianIdentifier(null);
            }
            koDTO.setPrice(koEntity.getPrice());
            allKo.add(koDTO);
        }
        return allKo;
    }

    public List<KoDTO> getKoByVIN(String vin) {
        List<KoDTO> listKoDTO = new ArrayList<>();
        for (KOEntity koEntity : koRepository.findByVINKO(vin)) {
            KoDTO koDTO = new KoDTO();
            koDTO.setId(koEntity.getVINKO());
            koDTO.setDate(koEntity.getDate());
            koDTO.setControlType(koEntity.getControlType());
            koDTO.setCategory(koEntity.getCategory());
            if (koEntity.getTechnicianIdentifier() != null) {
                koDTO.setTechnicianIdentifier(koEntity.getTechnicianIdentifier().getIdentifier());
            } else {
                koDTO.setTechnicianIdentifier(null);
            }
            koDTO.setPrice(koEntity.getPrice());
            listKoDTO.add(koDTO);
        }
        return listKoDTO;
    }

    public List<KoDTO> getKOByCriteria(Date date, String controlType, String category, String technicianIdentifier) {
        List<KOEntity> results;

        if (technicianIdentifier != null) {
            results = koRepository.findByTechnicianIdentifier_IdentifierAndTechnicianIdentifier_ControlType(technicianIdentifier, "KO");
        } else {
            results = Collections.emptyList(); // alebo koRepository.findAll() ak chceš fallback
        }

        if (date != null) {
            results = results.stream()
                    .filter(e -> e.getDate().equals(date))
                    .collect(Collectors.toList());
        }

        if (controlType != null) {
            results = results.stream()
                    .filter(e -> e.getControlType().equals(controlType))
                    .collect(Collectors.toList());
        }

        if (category != null) {
            results = results.stream()
                    .filter(e -> e.getCategory().equals(category))
                    .collect(Collectors.toList());
        }

        return results.stream()
                .map(koEntity -> {
                    KoDTO dto = new KoDTO();
                    dto.setId(koEntity.getVINKO());
                    dto.setDate(koEntity.getDate());
                    dto.setControlType(koEntity.getControlType());
                    dto.setCategory(koEntity.getCategory());
                    dto.setTechnicianIdentifier(koEntity.getTechnicianIdentifier().getIdentifier());
                    dto.setPrice(koEntity.getPrice());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public void createKO(KoDTO koDTO) {
        KOEntity koEntity = new KOEntity();

        koEntity.setVINKO(koDTO.getId());
        koEntity.setDate(koDTO.getDate());
        koEntity.setControlType(koDTO.getControlType());
        koEntity.setCategory(koDTO.getCategory());

        TechnicianControlIdentificatorsEntity technicianIdentifier =
                technicianControlIdentificatorsRepository
                        .findByIdentifier(koDTO.getTechnicianIdentifier())
                        .orElseThrow(() -> new RuntimeException("Technician identifier not found"));

        koEntity.setTechnicianIdentifier(technicianIdentifier);
        koEntity.setPrice(koDTO.getPrice());

        koRepository.save(koEntity);
    }

}
