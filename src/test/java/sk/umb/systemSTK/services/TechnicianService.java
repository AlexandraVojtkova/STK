package sk.umb.systemSTK.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.systemSTK.mappers.Mappers;
import sk.umb.systemSTK.persistent.entity.TechnicianEntity;
import sk.umb.systemSTK.persistent.repository.TechnicianRepository;
import sk.umb.systemSTK.utils.TechnicianDTO;

import java.util.stream.Collectors;

@Service
public class TechnicianService {
    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private Mappers mappers;

    public TechnicianDTO getTechnicianById(Long id) {
        TechnicianEntity technicianEntity = technicianRepository.findById(id).orElse(null);
            TechnicianDTO technicianDTO = new TechnicianDTO();
            technicianDTO.setId(technicianEntity.getTechnicianId());
            technicianDTO.setName(technicianEntity.getName());
            technicianDTO.setLastName(technicianEntity.getLastName());
            technicianDTO.setEkList(technicianEntity.getEkList()
                    .stream()
                    .map(mappers::toEKDto)
                    .collect(Collectors.toList()));
            technicianDTO.setTkList(technicianEntity.getTkList()
                    .stream()
                    .map(mappers::toTKDTO)
                    .collect(Collectors.toList()));
            technicianDTO.setKoList(technicianEntity.getKoList()
                    .stream()
                    .map(mappers::toKODTO)
                    .collect(Collectors.toList()));
        return technicianDTO;
    }

    public void putTechnician(TechnicianDTO technicianDTO) {
        TechnicianEntity technicianEntity = technicianRepository.findById(technicianDTO.getId())
                .orElse(new TechnicianEntity());

        technicianEntity.setTechnicianId(technicianDTO.getId());
        technicianEntity.setName(technicianDTO.getName());
        technicianEntity.setLastName(technicianDTO.getLastName());

        technicianRepository.save(technicianEntity);
    }
}
