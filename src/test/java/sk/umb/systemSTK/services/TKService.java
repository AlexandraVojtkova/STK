package sk.umb.systemSTK.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.systemSTK.persistent.entity.EKEntity;
import sk.umb.systemSTK.persistent.entity.TKEntity;
import sk.umb.systemSTK.persistent.repository.TKRepository;
import sk.umb.systemSTK.persistent.repository.TechnicianRepository;
import sk.umb.systemSTK.persistent.repository.UserRepository;
import sk.umb.systemSTK.utils.EkDTO;
import sk.umb.systemSTK.utils.TkDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TKService {

    @Autowired
    private TKRepository tkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

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
            tkDTO.setTechnicianId(tkEntity.getIdOfTechnician().getTechnicianId());
            tkDTO.setPrice(tkEntity.getPrice());
            allTk.add(tkDTO);
        }
        return allTk;
    }

    public TkDTO getTKByCriteria(Date date, String controlType, String evaluationOfVehicle, String ECV, String category, String brand, String model, Long technicianId) {
        Optional<TKEntity> tkEntityOptional = Optional.empty();

        if (date != null) {
            tkEntityOptional = tkRepository.findByDate(date);
        } else if (controlType != null) {
            tkEntityOptional = tkRepository.findByControlType(controlType);
        } else if (evaluationOfVehicle != null) {
            tkEntityOptional = tkRepository.findByEvaluationOfVehicle(evaluationOfVehicle);
        } else if (ECV != null) {
            tkEntityOptional = tkRepository.findByECV(ECV);
        } else if (category != null) {
            tkEntityOptional = tkRepository.findByCategory(category);
        } else if (brand != null) {
            tkEntityOptional = tkRepository.findByBrand(brand);
        } else if (model != null) {
            tkEntityOptional = tkRepository.findByModel(model);
        } else if (technicianId != null) {
            tkEntityOptional = tkRepository.findByTechnicianId(technicianId);
        }

        return tkEntityOptional.map(tkEntity -> {
            TkDTO tkDTO = new TkDTO();
            tkDTO.setId(tkEntity.getVINTK());
            tkDTO.setDate(tkEntity.getDate());
            tkDTO.setControlType(tkEntity.getControlType());
            tkDTO.setEvaluationOfVehicle(tkEntity.getEvaluationOfVehicle());
            tkDTO.setECV(tkEntity.getECV());
            tkDTO.setCategory(tkEntity.getCategory());
            tkDTO.setBrand(tkEntity.getBrand());
            tkDTO.setModel(tkEntity.getModel());
            tkDTO.setTechnicianId(tkEntity.getIdOfTechnician().getTechnicianId());
            return tkDTO;
        }).orElse(null);
    }

    public void putTK(Long technicianId, TkDTO ekDTO) {

        TKEntity tkEntity = tkRepository.findById(technicianId).get();
        if (tkEntity != null) {
            tkEntity.setVINTK(ekDTO.getId());
            tkEntity.setDate(ekDTO.getDate());
            tkEntity.setControlType(ekDTO.getControlType());
            tkEntity.setEvaluationOfVehicle(ekDTO.getEvaluationOfVehicle());
            tkEntity.setECV(ekDTO.getECV());
            tkEntity.setCategory(ekDTO.getCategory());
            tkEntity.setBrand(ekDTO.getBrand());
            tkEntity.setModel(ekDTO.getModel());
            tkEntity.setPrice(ekDTO.getPrice());
            tkRepository.save(tkEntity);
        }
    }
}
