package sk.umb.systemSTK.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.systemSTK.persistent.entity.EKEntity;
import sk.umb.systemSTK.persistent.entity.TKEntity;
import sk.umb.systemSTK.persistent.entity.TechnicianControlIdentificatorsEntity;
import sk.umb.systemSTK.persistent.repository.TKRepository;
import sk.umb.systemSTK.persistent.repository.TechnicianControlIdentificatorsRepository;
import sk.umb.systemSTK.persistent.repository.TechnicianRepository;
import sk.umb.systemSTK.persistent.repository.UserRepository;
import sk.umb.systemSTK.utils.TkDTO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TKService {

    @Autowired
    private TKRepository tkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechnicianControlIdentificatorsRepository technicianControlIdentificatorsRepository;

    public List<TkDTO> getAllTk() {
        List<TkDTO> allTk = new ArrayList<>();

        for (TKEntity tkEntity : tkRepository.findAll()) {
            TkDTO tkDTO = new TkDTO();
            tkDTO.setId(tkEntity.getVINTK());
            tkDTO.setDate(tkEntity.getDate());
            tkDTO.setControlType(tkEntity.getControlType());
            tkDTO.setEvaluationOfVehicle(tkEntity.getEvaluationOfVehicle());
            tkDTO.setECV(tkEntity.getECV());
            tkDTO.setCategory(tkEntity.getCategory());
            tkDTO.setBrand(tkEntity.getBrand());
            tkDTO.setModel(tkEntity.getModel());
            if (tkEntity.getTechnicianIdentifier() != null) {
                tkDTO.setTechnicianIdentifier(tkEntity.getTechnicianIdentifier().getIdentifier());
            } else {
                tkDTO.setTechnicianIdentifier(null);
            }
            tkDTO.setPrice(tkEntity.getPrice());
            allTk.add(tkDTO);
        }
        return allTk;
    }

    public List<TkDTO> getTKByCriteria(Date date, String controlType, String evaluationOfVehicle, String ECV, String category, String brand, String model, String technicianIdentifier) {
        List<TKEntity> results;

        if (technicianIdentifier != null) {
            results = tkRepository.findByTechnicianIdentifier_IdentifierAndTechnicianIdentifier_ControlType(technicianIdentifier, "TK");
        } else {
            results = Collections.emptyList(); // alebo tkRepository.findAll() ak chceš fallback
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

        return results.stream()
                .map(tkEntity -> {
                    TkDTO dto = new TkDTO();
                    dto.setId(tkEntity.getVINTK());
                    dto.setDate(tkEntity.getDate());
                    dto.setControlType(tkEntity.getControlType());
                    dto.setEvaluationOfVehicle(tkEntity.getEvaluationOfVehicle());
                    dto.setECV(tkEntity.getECV());
                    dto.setCategory(tkEntity.getCategory());
                    dto.setBrand(tkEntity.getBrand());
                    dto.setModel(tkEntity.getModel());
                    dto.setTechnicianIdentifier(tkEntity.getTechnicianIdentifier().getIdentifier());
                    dto.setPrice(tkEntity.getPrice());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    public void createTK(TkDTO tkDTO) {
        TKEntity tkEntity = new TKEntity();
        tkEntity.setVINTK(tkDTO.getId());
        tkEntity.setDate(tkDTO.getDate());
        tkEntity.setControlType(tkDTO.getControlType());
        tkEntity.setEvaluationOfVehicle(tkDTO.getEvaluationOfVehicle());
        tkEntity.setECV(tkDTO.getECV());
        tkEntity.setCategory(tkDTO.getCategory());
        tkEntity.setBrand(tkDTO.getBrand());
        tkEntity.setModel(tkDTO.getModel());
        tkEntity.setPrice(tkDTO.getPrice());

        TechnicianControlIdentificatorsEntity technicianIdentifier =
                technicianControlIdentificatorsRepository
                        .findByIdentifier(tkDTO.getTechnicianIdentifier())
                        .orElseThrow(() -> new RuntimeException("Technician identifier not found: " + tkDTO.getTechnicianIdentifier()));

        tkEntity.setTechnicianIdentifier(technicianIdentifier);

        tkRepository.save(tkEntity);
    }

}
