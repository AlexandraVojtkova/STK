package sk.umb.systemSTK.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.systemSTK.persistent.entity.EKEntity;
import sk.umb.systemSTK.persistent.repository.EKRepository;
import sk.umb.systemSTK.persistent.repository.TechnicianRepository;
import sk.umb.systemSTK.persistent.repository.UserRepository;
import sk.umb.systemSTK.utils.EkDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EKService {
    @Autowired
    private EKRepository ekRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

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
            ekDTO.setTechnicianId(ekEntity.getTechnician().getTechnicianId());
            ekDTO.setPrice(ekEntity.getPrice());
            allEk.add(ekDTO);
        }
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
            ekDTO.setTechnicianId(ekEntity.getTechnician().getTechnicianId());
            return ekDTO;
        }).orElse(null);
    }

    public void putEK(Long technicianId, EkDTO ekDTO) {

        EKEntity ekEntity = ekRepository.findById(technicianId).get();
        if (ekEntity != null) {
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
            ekRepository.save(ekEntity);
        }
    }
}
