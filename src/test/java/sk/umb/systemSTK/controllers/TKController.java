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
public class TKController {
    @Autowired
    TKService tkService;

    @GetMapping("/api/getAllTk")
    public List<TkDTO> getAllTk() {
        return tkService.getAllTk();
    }

    @GetMapping("api/getTk/date")
    public List<TkDTO> getTkDate(@RequestParam Date date) {
        return tkService.getTKByCriteria(date, null, null,null, null, null, null, null);
    }
    @GetMapping("api/getTk/controlType")
    public List<TkDTO> getTkControlType(@RequestParam String controlType) {
        return tkService.getTKByCriteria(null, controlType, null,null, null, null, null, null);
    }
    @GetMapping("api/getTKByCriteria/evaluationOfVehicle")
    public List<TkDTO> getTkEvaluationOfVehicle(@RequestParam String evaluationOfVehicle) {
        return tkService.getTKByCriteria(null, null, evaluationOfVehicle,null, null, null, null, null);
    }
    @GetMapping("api/getTk/ECV")
    public List<TkDTO> getTkECV(@RequestParam String ECV) {
        return tkService.getTKByCriteria(null, null, null,ECV, null, null, null, null);
    }
    @GetMapping("api/getTk/category")
    public List<TkDTO> getTkCategory(@RequestParam String category) {
        return tkService.getTKByCriteria(null, null, null,null, category, null, null, null);
    }
    @GetMapping("api/getTk/brand")
    public List<TkDTO> getTkBrand(@RequestParam String brand) {
        return tkService.getTKByCriteria(null, null, null,null, null, brand, null, null);
    }
    @GetMapping("api/getTk/model")
    public List<TkDTO> getTkModel(@RequestParam String model) {
        return tkService.getTKByCriteria(null, null, null,null, null, null, model, null);
    }
    @GetMapping("api/getTk/technicianId")
    public List<TkDTO> getTkTechnicianId(@RequestParam String technicianIdentifier) {
        return tkService.getTKByCriteria(null, null, null,null, null, null, null,  technicianIdentifier);
    }

    @PostMapping("api/putTk/{TkDTO}")
    public ResponseEntity<Void> createTk(@RequestBody TkDTO tkDTO) {
        tkService.createTK(tkDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
