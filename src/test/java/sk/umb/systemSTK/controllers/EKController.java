package sk.umb.systemSTK.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.umb.systemSTK.services.EKService;
import sk.umb.systemSTK.utils.EkDTO;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EKController {
    @Autowired
    EKService ekService;

    @GetMapping("/api/getAllEk")
    public List<EkDTO> getAllEk() {
        return ekService.getAllEk();
    }

    @GetMapping("api/getEkByVIN/{VIN}")
    public ResponseEntity<List<EkDTO>> getByVIN(@PathVariable String VIN) {
        List<EkDTO> list = ekService.getEkByVIN(VIN);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/api/getEk/date/{date}")
    public List<EkDTO> getEkDate(@PathVariable Date date) {
        return ekService.getEKByCriteria(date, null, null,null, null, null, null, null, null);
    }
    @GetMapping("/api/getEk/controlType/{controlType}")
    public List<EkDTO> getEkControlType(@PathVariable String controlType) {
        return ekService.getEKByCriteria(null, controlType, null,null, null, null, null, null, null);
    }
    @GetMapping("/api/getEk/evaluationOfVehicle/{evaluationOfVehicle}")
    public List<EkDTO> getEkEvaluationOfVehicle(@PathVariable String evaluationOfVehicle) {
        return ekService.getEKByCriteria(null, null, evaluationOfVehicle,null, null, null, null, null, null);
    }
    @GetMapping("/api/getEk/ECV/{ECV}")
    public List<EkDTO> getEkECV(@PathVariable String ECV) {
        return ekService.getEKByCriteria(null, null, null,ECV, null, null, null, null, null);
    }
    @GetMapping("/api/getEk/category/{category}")
    public List<EkDTO> getEkCategory(@PathVariable String category) {
        return ekService.getEKByCriteria(null, null, null,null, category, null, null, null, null);
    }
    @GetMapping("/api/getEk/brand/{brand}")
    public List<EkDTO> getEkBrand(@PathVariable String brand) {
        return ekService.getEKByCriteria(null, null, null,null, null, brand, null, null, null);
    }
    @GetMapping("/api/getEk/model/{model}")
    public List<EkDTO> getEkModel(@PathVariable String model) {
        return ekService.getEKByCriteria(null, null, null,null, null, null, model, null, null);
    }
    @GetMapping("/api/getEk/systemOfEmmission/{systemOfEmmission}")
    public List<EkDTO> getEkSystemOfEmmission(@PathVariable String systemOfEmmission) {
        return ekService.getEKByCriteria(null, null, null,null, null, null, null, systemOfEmmission, null);
    }
    @GetMapping("/api/getEk/technicianId/{technicianId}")
    public List<EkDTO> getEkTechnicianId(@PathVariable String technicianId) {
        return ekService.getEKByCriteria(null, null, null,null, null, null, null, null, technicianId);
    }

    @PostMapping("/api/putEk")
    public ResponseEntity<Void> createEK(@RequestBody EkDTO ekDTO) {
        ekService.createEK(ekDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
