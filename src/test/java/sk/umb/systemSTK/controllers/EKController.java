package sk.umb.systemSTK.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/api/getEk/date/{date}")
    public List<EkDTO> getEkDate(@RequestParam Date date) {
        return ekService.getEKByCriteria(date, null, null,null, null, null, null, null, null);
    }
    @GetMapping("/api/getEk/controlType/{controlType}")
    public List<EkDTO> getEkControlType(@RequestParam String controlType) {
        return ekService.getEKByCriteria(null, controlType, null,null, null, null, null, null, null);
    }
    @GetMapping("/api/getEk/evaluationOfVehicle/{evaluationOfVehicle}")
    public List<EkDTO> getEkEvaluationOfVehicle(@RequestParam String evaluationOfVehicle) {
        return ekService.getEKByCriteria(null, null, evaluationOfVehicle,null, null, null, null, null, null);
    }
    @GetMapping("/api/getEk/ECV/{ECV}")
    public List<EkDTO> getEkECV(@RequestParam String ECV) {
        return ekService.getEKByCriteria(null, null, null,ECV, null, null, null, null, null);
    }
    @GetMapping("/api/getEk/category/{category}")
    public List<EkDTO> getEkCategory(@RequestParam String category) {
        return ekService.getEKByCriteria(null, null, null,null, category, null, null, null, null);
    }
    @GetMapping("/api/getEk/brand/{brand}")
    public List<EkDTO> getEkBrand(@RequestParam String brand) {
        return ekService.getEKByCriteria(null, null, null,null, null, brand, null, null, null);
    }
    @GetMapping("/api/getEk/model/{model}")
    public List<EkDTO> getEkModel(@RequestParam String model) {
        return ekService.getEKByCriteria(null, null, null,null, null, null, model, null, null);
    }
    @GetMapping("/api/getEk/systemOfEmmission/{systemOfEmmission}")
    public List<EkDTO> getEkSystemOfEmmission(@RequestParam String systemOfEmmission) {
        return ekService.getEKByCriteria(null, null, null,null, null, null, null, systemOfEmmission, null);
    }
    @GetMapping("/api/getEk/technicianId/{technicianId}")
    public List<EkDTO> getEkTechnicianId(@RequestParam String technicianIdentifier) {
        return ekService.getEKByCriteria(null, null, null,null, null, null, null, null, technicianIdentifier);
    }

    @PostMapping("/api/putEk/{ekDTO}")
    public ResponseEntity<Void> createEK(@RequestBody EkDTO ekDTO) {
        ekService.createEK(ekDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
