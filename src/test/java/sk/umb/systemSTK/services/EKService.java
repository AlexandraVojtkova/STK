package sk.umb.systemSTK.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.systemSTK.persistent.entity.EKEntity;
import sk.umb.systemSTK.persistent.entity.TechnicianControlIdentificatorsEntity;
import sk.umb.systemSTK.persistent.entity.TechnicianEntity;
import sk.umb.systemSTK.persistent.repository.*;
import sk.umb.systemSTK.utils.EkDTO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EKService {
    @Autowired
    private EKRepository ekRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechnicianControlIdentificatorsRepository technicianControlIdentificatorsRepository;

    public List<EkDTO> getAllEk() {
        List<EkDTO> allEk = new ArrayList<>();

        for (EKEntity ekEntity : ekRepository.findAll()) {
            EkDTO ekDTO = new EkDTO();
            ekDTO.setId(ekEntity.getVINEK());
            ekDTO.setDate(ekEntity.getDate());
            ekDTO.setControlType(ekEntity.getControlType());
            ekDTO.setEvaluationOfVehicle(ekEntity.getEvaluationOfVehicle());
            ekDTO.setECV(ekEntity.getECV());
            ekDTO.setCategory(ekEntity.getCategory());
            ekDTO.setBrand(ekEntity.getBrand());
            ekDTO.setModel(ekEntity.getModel());
            ekDTO.setSystemOfEmmission(ekEntity.getSystemOfEmissions());
            if (ekEntity.getTechnicianIdentifier() != null) {
                ekDTO.setTechnicianIdentifier(ekEntity.getTechnicianIdentifier().getIdentifier());
            } else {
                ekDTO.setTechnicianIdentifier(null);
            }
            ekDTO.setPrice(ekEntity.getPrice());
            allEk.add(ekDTO);
        }
        return allEk;
    }

    public List<EkDTO> getEkByVIN(String vin) {
        List<EkDTO> ekDTOList = new ArrayList<>();
        for (EKEntity ekEntity : ekRepository.findByVINEK(vin)) {
            EkDTO ekDTO = new EkDTO();
            ekDTO.setId(ekEntity.getVINEK());
            ekDTO.setDate(ekEntity.getDate());
            ekDTO.setControlType(ekEntity.getControlType());
            ekDTO.setEvaluationOfVehicle(ekEntity.getEvaluationOfVehicle());
            ekDTO.setECV(ekEntity.getECV());
            ekDTO.setCategory(ekEntity.getCategory());
            ekDTO.setBrand(ekEntity.getBrand());
            ekDTO.setModel(ekEntity.getModel());
            ekDTO.setSystemOfEmmission(ekEntity.getSystemOfEmissions());
            if (ekEntity.getTechnicianIdentifier() != null) {
                ekDTO.setTechnicianIdentifier(ekEntity.getTechnicianIdentifier().getIdentifier());
            } else {
                ekDTO.setTechnicianIdentifier(null);
            }
            ekDTO.setPrice(ekEntity.getPrice());
            ekDTOList.add(ekDTO);
        }
        return ekDTOList;
    }

    public List<EkDTO> getEKByCriteria(Date date, String controlType, String evaluationOfVehicle, String ECV, String category, String brand, String model, String systemOfEmmission, String technicianIdentifier) {
        List<EKEntity> results = new ArrayList<>();

        // Filtrovanie cez identifikátor a typ
        if (technicianIdentifier != null) {
            results = ekRepository.findByTechnicianIdentifier_IdentifierAndTechnicianIdentifier_ControlType(technicianIdentifier, "EK");
        } else {
            results = ekRepository.findAll(); // alebo iný fallback
        }

        // Dodatočné filtrovanie (napr. podľa dátumu, kategórie...)
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

        if (evaluationOfVehicle != null) {
            results = results.stream()
                    .filter(e -> e.getEvaluationOfVehicle().equals(evaluationOfVehicle))
                    .collect(Collectors.toList());
        }

        if (ECV != null) {
            results = results.stream()
                    .filter(e -> e.getECV().equals(ECV))
                    .collect(Collectors.toList());
        }

        if (category != null) {
            results = results.stream()
                    .filter(e -> e.getCategory().equals(category))
                    .collect(Collectors.toList());
        }

        if (brand != null) {
            results = results.stream()
                    .filter(e -> e.getBrand().equals(brand))
                    .collect(Collectors.toList());
        }

        if (model != null) {
            results = results.stream()
                    .filter(e -> e.getModel().equals(model))
                    .collect(Collectors.toList());
        }

        if (systemOfEmmission != null) {
            results = results.stream()
                    .filter(e -> e.getSystemOfEmissions().equals(systemOfEmmission))
                    .collect(Collectors.toList());
        }

        // Mapovanie na DTO
        return results.stream()
                .map(ekEntity -> {
                    EkDTO dto = new EkDTO();
                    dto.setId(ekEntity.getVINEK());
                    dto.setDate(ekEntity.getDate());
                    dto.setControlType(ekEntity.getControlType());
                    dto.setEvaluationOfVehicle(ekEntity.getEvaluationOfVehicle());
                    dto.setECV(ekEntity.getECV());
                    dto.setCategory(ekEntity.getCategory());
                    dto.setBrand(ekEntity.getBrand());
                    dto.setModel(ekEntity.getModel());
                    dto.setSystemOfEmmission(ekEntity.getSystemOfEmissions());
                    dto.setTechnicianIdentifier(ekEntity.getTechnicianIdentifier().getIdentifier());
                    dto.setPrice(ekEntity.getPrice());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public void createEK(EkDTO ekDTO) {
        EKEntity ekEntity = new EKEntity();
        ekEntity.setVINEK(ekDTO.getId());
        ekEntity.setDate(ekDTO.getDate());
        ekEntity.setControlType(ekDTO.getControlType());
        ekEntity.setEvaluationOfVehicle(ekDTO.getEvaluationOfVehicle());
        ekEntity.setECV(ekDTO.getECV());
        ekEntity.setCategory(ekDTO.getCategory());
        ekEntity.setBrand(ekDTO.getBrand());
        ekEntity.setModel(ekDTO.getModel());
        ekEntity.setSystemOfEmissions(ekDTO.getSystemOfEmmission());
        ekEntity.setPrice(ekDTO.getPrice());

        if (ekDTO.getTechnicianIdentifier() == null) {
            System.out.println("Záznam preskočený");
            return;
        }
        TechnicianControlIdentificatorsEntity technicianIdentifier =
                technicianControlIdentificatorsRepository
                        .findByIdentifier(ekDTO.getTechnicianIdentifier())
                        .orElseThrow(() -> new RuntimeException("Technician identifier not found: " + ekDTO.getTechnicianIdentifier()));
        ekEntity.setTechnicianIdentifier(technicianIdentifier);

        ekRepository.save(ekEntity);
    }



}
