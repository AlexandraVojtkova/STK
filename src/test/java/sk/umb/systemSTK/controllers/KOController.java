package sk.umb.systemSTK.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.umb.systemSTK.services.KOService;
import sk.umb.systemSTK.utils.KoDTO;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class KOController {
    @Autowired
    KOService koService;

    @GetMapping("/api/getAllKo")
    public List<KoDTO> getAllKo() {
        return koService.getAllKo();
    }
    @GetMapping("/api/getKoByVIN/{VIN}")
    public ResponseEntity<List<KoDTO>> getKoByVIN(@PathVariable String VIN) {
        List<KoDTO> list = koService.getKoByVIN(VIN);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/api/getKo/date/{date}")
    public List<KoDTO> getKoDate(@PathVariable Date date) {
        return koService.getKOByCriteria(date, null, null,null);
    }
    @GetMapping("/api/getKo/controlType/{controlType}")
    public List<KoDTO> getKoControlType(@PathVariable String controlType) {
        return koService.getKOByCriteria(null, controlType, null,null);
    }
    @GetMapping("/api/getKo/category/{category}")
    public List<KoDTO> getKoCategory(@PathVariable String category) {
        return koService.getKOByCriteria(null, null, category, null);
    }
    @GetMapping("/api/getKo/technicianId/{technicianId}")
    public List<KoDTO> getKoTechnicianId(@PathVariable String technicianId) {
        return koService.getKOByCriteria(null, null, null, technicianId);
    }
    @PostMapping("/api/putKo")
    public ResponseEntity<Void> putKO(KoDTO koDTO) {
        koService.createKO(koDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
