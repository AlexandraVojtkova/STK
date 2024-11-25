package sk.umb.systemSTK.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.systemSTK.persistent.entity.TKEntity;
import sk.umb.systemSTK.persistent.repository.TKRepository;
import sk.umb.systemSTK.persistent.repository.TechnicianRepository;
import sk.umb.systemSTK.persistent.repository.UserRepository;
import sk.umb.systemSTK.utils.TkDTO;

import java.util.Date;
import java.util.Optional;

@Service
public class TKService {

    @Autowired
    private TKRepository tkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TechnicianRepository technicianRepository;

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
}
