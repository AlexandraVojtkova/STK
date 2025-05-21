package sk.umb.systemSTK.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.systemSTK.mappers.Mappers;
import sk.umb.systemSTK.persistent.entity.TechnicianControlIdentificatorsEntity;
import sk.umb.systemSTK.persistent.entity.TechnicianEntity;
import sk.umb.systemSTK.persistent.repository.TechnicianRepository;
import sk.umb.systemSTK.utils.CreateTechnicianDTO;
import sk.umb.systemSTK.utils.TechnicianControlIdentificatorsDTO;
import sk.umb.systemSTK.utils.TechnicianDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TechnicianService {
    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private Mappers mappers;

    public List<CreateTechnicianDTO> getAllTechnicians() {
        List<CreateTechnicianDTO> technicians = new ArrayList<>();

        for (TechnicianEntity technician : technicianRepository.findAll()) {
            CreateTechnicianDTO createTechnicianDTO = new CreateTechnicianDTO();
            createTechnicianDTO.setName(technician.getName());
            createTechnicianDTO.setLastName(technician.getLastName());

            List<TechnicianControlIdentificatorsDTO> identificators = technician.getIdentifiers()
                    .stream()
                    .map(identifierEntity -> {
                        TechnicianControlIdentificatorsDTO identDTO = new TechnicianControlIdentificatorsDTO();
                        identDTO.setIdentificator(identifierEntity.getIdentifier());
                        identDTO.setControlType(identifierEntity.getControlType());
                        return identDTO;
                    })
                    .toList();

            createTechnicianDTO.setIdentificators(identificators);

            technicians.add(createTechnicianDTO);
        }

        return technicians;
    }

    public Long createTechnician(CreateTechnicianDTO createDTO) {
        TechnicianEntity technician = new TechnicianEntity();
        technician.setName(createDTO.getName());
        technician.setLastName(createDTO.getLastName());

        // uložíme technika najskôr (aby mal ID)
        technicianRepository.save(technician);

        // pridáme identifikátory
        List<TechnicianControlIdentificatorsEntity> identifiers = createDTO.getIdentificators().stream()
                .map(dto -> {
                    TechnicianControlIdentificatorsEntity ident = new TechnicianControlIdentificatorsEntity();
                    ident.setIdentifier(dto.getIdentificator());
                    ident.setControlType(dto.getControlType());
                    ident.setTechnician(technician);
                    return ident;
                }).collect(Collectors.toList());

        technician.setIdentifiers(identifiers);
        technicianRepository.save(technician);
        return technician.getTechnicianId();
    }

    public void deleteTechnician(Long id) {
        TechnicianEntity technician = technicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with id: " + id));
        technicianRepository.delete(technician);
    }

    public List<TechnicianDTO> findByNameOrLastName(String searchTerm) {
        List<TechnicianEntity> matched = technicianRepository
                .findByNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(searchTerm, searchTerm);

        return matched.stream()
                .map(mappers::toTechnicianDTO)
                .collect(Collectors.toList());
    }

    public CreateTechnicianDTO findById(Long id) {
        TechnicianEntity technician = technicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with id: " + id));

        CreateTechnicianDTO dto = new CreateTechnicianDTO();
        dto.setName(technician.getName());
        dto.setLastName(technician.getLastName());

        List<TechnicianControlIdentificatorsDTO> identifierDTOs = technician.getIdentifiers()
                .stream()
                .map(identifier -> {
                    TechnicianControlIdentificatorsDTO dtoItem = new TechnicianControlIdentificatorsDTO();
                    dtoItem.setIdentificator(identifier.getIdentifier());
                    dtoItem.setControlType(identifier.getControlType());
                    return dtoItem;
                })
                .collect(Collectors.toList());

        dto.setIdentificators(identifierDTOs);

        return dto;
    }


    public void updateTechnician(Long id, CreateTechnicianDTO updatedDTO) {
        TechnicianEntity technician = technicianRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with id: " + id));

        technician.setName(updatedDTO.getName());
        technician.setLastName(updatedDTO.getLastName());

        if (updatedDTO.getIdentificators() != null) {
            for (TechnicianControlIdentificatorsDTO identifierDTO : updatedDTO.getIdentificators()) {
                boolean alreadyExists = technician.getIdentifiers().stream()
                        .anyMatch(existing -> existing.getIdentifier().equals(identifierDTO.getIdentificator())
                                && existing.getControlType().equals(identifierDTO.getControlType()));
                if (!alreadyExists) {
                    TechnicianControlIdentificatorsEntity newIdent = new TechnicianControlIdentificatorsEntity();
                    newIdent.setIdentifier(identifierDTO.getIdentificator());
                    newIdent.setControlType(identifierDTO.getControlType());
                    newIdent.setTechnician(technician);
                    technician.getIdentifiers().add(newIdent);
                }
            }
        }

        technicianRepository.save(technician);
    }

}
