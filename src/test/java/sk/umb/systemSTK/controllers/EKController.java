package sk.umb.systemSTK.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.systemSTK.services.EKService;
import sk.umb.systemSTK.utils.EkDTO;

import java.util.Date;
import java.util.List;

@RestController
public class EKController {
    @Autowired
    EKService ekService;

    @GetMapping("/api/getAllEk")
    public List<EkDTO> getAllEk() {
        return ekService.getAllEk();
    }

    @GetMapping("api/getEk/date")
    public EkDTO getEkDate(Date date) {
        return ekService.getEKByCriteria(date, null, null,null, null, null, null, null, null);
    }
    @GetMapping("api/getEk/controlType")
    public EkDTO getEkControlType(String controlType) {
        return ekService.getEKByCriteria(null, controlType, null,null, null, null, null, null, null);
    }
    @GetMapping("api/getEk/evaluationOfVehicle")
    public EkDTO getEkEvaluationOfVehicle(String evaluationOfVehicle) {
        return ekService.getEKByCriteria(null, null, evaluationOfVehicle,null, null, null, null, null, null);
    }
    @GetMapping("api/getEk/ECV")
    public EkDTO getEkECV(String ECV) {
        return ekService.getEKByCriteria(null, null, null,ECV, null, null, null, null, null);
    }
    @GetMapping("api/getEk/category")
    public EkDTO getEkCategory(String category) {
        return ekService.getEKByCriteria(null, null, null,null, category, null, null, null, null);
    }
    @GetMapping("api/getEk/brand")
    public EkDTO getEkBrand(String brand) {
        return ekService.getEKByCriteria(null, null, null,null, null, brand, null, null, null);
    }
    @GetMapping("api/getEk/model")
    public EkDTO getEkModel(String model) {
        return ekService.getEKByCriteria(null, null, null,null, null, null, model, null, null);
    }
    @GetMapping("api/getEk/systemOfEmmission")
    public EkDTO getEkSystemOfEmmission(String systemOfEmmission) {
        return ekService.getEKByCriteria(null, null, null,null, null, null, null, systemOfEmmission, null);
    }
    @GetMapping("api/getEk/technicianId")
    public EkDTO getEkTechnicianId(Long technicianId) {
        return ekService.getEKByCriteria(null, null, null,null, null, null, null, null, technicianId);
    }

    @PostMapping("api/putEK/{id, ekDTO}")
    public void putEK(Long id, EkDTO ekDTO) {
        ekService.putEK(id, ekDTO);
    }
}
