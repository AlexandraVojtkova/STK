package sk.umb.systemSTK.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.systemSTK.services.KOService;
import sk.umb.systemSTK.utils.KoDTO;

import java.util.Date;
import java.util.List;

@RestController
public class KOController {
    @Autowired
    KOService koService;

    @GetMapping("/api/getAllKo")
    public List<KoDTO> getAllKo() {
        return koService.getAllKo();
    }
    @GetMapping("api/getKo/date")
    public List<KoDTO> getKoDate(@RequestParam Date date) {
        return koService.getKOByCriteria(date, null, null,null);
    }
    @GetMapping("api/getKo/controlType")
    public List<KoDTO> getKoControlType(@RequestParam String controlType) {
        return koService.getKOByCriteria(null, controlType, null,null);
    }
    @GetMapping("api/getKo/category")
    public List<KoDTO> getKoCategory(@RequestParam String category) {
        return koService.getKOByCriteria(null, null, category, null);
    }
    @GetMapping("api/getKo/technicianId")
    public List<KoDTO> getKoTechnicianId(@RequestParam String technicianIdentifier) {
        return koService.getKOByCriteria(null, null, null, technicianIdentifier);
    }
    @PostMapping("api/putKo/{koDTO}")
    public ResponseEntity<Void> putKO(KoDTO koDTO) {
        koService.createKO(koDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
