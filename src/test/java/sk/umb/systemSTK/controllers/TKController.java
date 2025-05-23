package sk.umb.systemSTK.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.umb.systemSTK.services.TKService;
import sk.umb.systemSTK.utils.TkDTO;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TKController {
    @Autowired
    TKService tkService;

    @GetMapping("/api/getAllTk")
    public List<TkDTO> getAllTk() {
        return tkService.getAllTk();
    }
    @GetMapping("/api/getTkByVIN/{VIN}")
    public ResponseEntity<List<TkDTO>> getTkByVIN(@PathVariable String VIN) {
        List<TkDTO> list = tkService.getTkByVIN(VIN);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/api/getTk/date/{date}")
    public List<TkDTO> getTkDate(@PathVariable Date date) {
        return tkService.getTKByCriteria(date, null, null,null, null, null, null, null);
    }
    @GetMapping("/api/getTk/controlType/{controlType}")
    public List<TkDTO> getTkControlType(@PathVariable String controlType) {
        return tkService.getTKByCriteria(null, controlType, null,null, null, null, null, null);
    }
    @GetMapping("/api/getTk/evaluationOfVehicle/{evaluationOfVehicle}")
    public List<TkDTO> getTkEvaluationOfVehicle(@PathVariable String evaluationOfVehicle) {
        return tkService.getTKByCriteria(null, null, evaluationOfVehicle,null, null, null, null, null);
    }
    @GetMapping("/api/getTk/ECV/{ECV}")
    public List<TkDTO> getTkECV(@PathVariable String ECV) {
        return tkService.getTKByCriteria(null, null, null,ECV, null, null, null, null);
    }
    @GetMapping("/api/getTk/category/{category}")
    public List<TkDTO> getTkCategory(@PathVariable String category) {
        return tkService.getTKByCriteria(null, null, null,null, category, null, null, null);
    }
    @GetMapping("/api/getTk/brand/{brand}")
    public List<TkDTO> getTkBrand(@PathVariable String brand) {
        return tkService.getTKByCriteria(null, null, null,null, null, brand, null, null);
    }
    @GetMapping("/api/getTk/model/{model}")
    public List<TkDTO> getTkModel(@PathVariable String model) {
        return tkService.getTKByCriteria(null, null, null,null, null, null, model, null);
    }
    @GetMapping("/api/getTk/technicianId/{technicianId}")
    public List<TkDTO> getTkTechnicianId(@PathVariable String technicianId) {
        return tkService.getTKByCriteria(null, null, null,null, null, null, null,  technicianId);
    }

    @PostMapping("/api/putTk")
    public ResponseEntity<Void> createTk(@RequestBody TkDTO tkDTO) {
        tkService.createTK(tkDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
