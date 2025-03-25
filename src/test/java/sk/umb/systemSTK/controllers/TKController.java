package sk.umb.systemSTK.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public TkDTO getTkDate(Date date) {
        return tkService.getTKByCriteria(date, null, null,null, null, null, null, null);
    }
    @GetMapping("api/getTk/controlType")
    public TkDTO getTkControlType(String controlType) {
        return tkService.getTKByCriteria(null, controlType, null,null, null, null, null, null);
    }
    @GetMapping("api/getTKByCriteria/evaluationOfVehicle")
    public TkDTO getTkEvaluationOfVehicle(String evaluationOfVehicle) {
        return tkService.getTKByCriteria(null, null, evaluationOfVehicle,null, null, null, null, null);
    }
    @GetMapping("api/getTk/ECV")
    public TkDTO getTkECV(String ECV) {
        return tkService.getTKByCriteria(null, null, null,ECV, null, null, null, null);
    }
    @GetMapping("api/getTk/category")
    public TkDTO getTkCategory(String category) {
        return tkService.getTKByCriteria(null, null, null,null, category, null, null, null);
    }
    @GetMapping("api/getTk/brand")
    public TkDTO getTkBrand(String brand) {
        return tkService.getTKByCriteria(null, null, null,null, null, brand, null, null);
    }
    @GetMapping("api/getTk/model")
    public TkDTO getTkModel(String model) {
        return tkService.getTKByCriteria(null, null, null,null, null, null, model, null);
    }
    @GetMapping("api/getTk/technicianId")
    public TkDTO getTkTechnicianId(Long technicianId) {
        return tkService.getTKByCriteria(null, null, null,null, null, null, null,  technicianId);
    }

    @PostMapping("api/putTk/{id, TkDTO}")
    public void putTk(Long id, TkDTO TkDTO) {
        tkService.putTK(id, TkDTO);
    }
}
