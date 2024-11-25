package sk.umb.systemSTK.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.systemSTK.persistent.entity.EKEntity;
import sk.umb.systemSTK.persistent.repository.EKRepository;
import sk.umb.systemSTK.persistent.repository.TechnicianRepository;
import sk.umb.systemSTK.persistent.repository.UserRepository;
import sk.umb.systemSTK.utils.EkDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EKService {
    @Autowired
    private EKRepository ekRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

    public List<EkDTO> getAllEk() {
        List<EkDTO> allEk = ekRepository.findAll();
        allEk.stream().map(
                entity -> new EkDTO(
                        entity.getId(),
                        entity.getDate(),
                        entity.getControlType(),
                        entity.getEvaluationOfVehicle(),
                        entity.getECV(),
                        entity.getCategory(),
                        entity.getBrand(),
                        entity.getModel(),
                        entity.getSystemOfEmmission(),
                        entity.getTechnicianId(),
                        entity.getPrice()
                )
        ).collect(Collectors.toList());
        return allEk;
    }

    public EkDTO getEKByCriteria( Date date, String controlType, String evaluationOfVehicle, String ECV, String category, String brand, String model, String systemOfEmmission, Long technicianId) {
        Optional<EKEntity> ekEntityOptional = Optional.empty();

        if (date != null) {
            ekEntityOptional = ekRepository.findByDate(date);
        } else if (controlType != null) {
            ekEntityOptional = ekRepository.findByControlType(controlType);
        } else if (evaluationOfVehicle != null) {
            ekEntityOptional = ekRepository.findByEvaluationOfVehicle(evaluationOfVehicle);
        } else if (ECV != null) {
            ekEntityOptional = ekRepository.findByECV(ECV);
        } else if (category != null) {
            ekEntityOptional = ekRepository.findByCategory(category);
        } else if (brand != null) {
            ekEntityOptional = ekRepository.findByBrand(brand);
        } else if (model != null) {
            ekEntityOptional = ekRepository.findByModel(model);
        } else if (systemOfEmmission != null) {
            ekEntityOptional = ekRepository.findBySystemOfEmissions(systemOfEmmission);
        } else if (technicianId != null) {
            ekEntityOptional = ekRepository.findByTechnicianId(technicianId);
        }

        return ekEntityOptional.map(ekEntity -> {
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
            ekDTO.setTechnicianId(ekEntity.getIdOfTechnician().getTechnicianId());
            return ekDTO;
        }).orElse(null);
    }
}
