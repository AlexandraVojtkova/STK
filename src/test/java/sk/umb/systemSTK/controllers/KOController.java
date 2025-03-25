package sk.umb.systemSTK.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.umb.systemSTK.services.KOService;
import sk.umb.systemSTK.utils.KoDTO;

import java.util.Date;
import java.util.List;

@RestController
public class KOController {
    @Autowired
    KOService koService;

    @GetMapping("/api/getAllEk")
    public List<KoDTO> getAllKo() {
        return koService.getAllKo();
    }

    @GetMapping("api/getKo/date")
    public KoDTO getKoDate(Date date) {
        return koService.getKOByCriteria(date, null, null,null);
    }
    @GetMapping("api/getKo/controlType")
    public KoDTO getKoControlType(String controlType) {
        return koService.getKOByCriteria(null, controlType, null,null);
    }
    @GetMapping("api/getKo/category")
    public KoDTO getKoCategory(String category) {
        return koService.getKOByCriteria(null, null, category, null);
    }
    @GetMapping("api/getKo/technicianId")
    public KoDTO getKoTechnicianId(Long technicianId) {
        return koService.getKOByCriteria(null, null, null, technicianId);
    }

    @PostMapping("api/putKo/{id, koDTO}")
    public void putKO(Long id, KoDTO koDTO) {
        koService.putKO(id, koDTO);
    }
}
