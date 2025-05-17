package sk.umb.systemSTK.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sk.umb.systemSTK.services.ExcelProcessor;
import sk.umb.systemSTK.services.TechnicianService;
import sk.umb.systemSTK.utils.TechnicianDTO;

@RestController
public class TechnicianController {
    @Autowired
    TechnicianService technicianService;

    @Autowired
    ExcelProcessor processor;

    @GetMapping("/api/getTechnicianById/{id}")
    public TechnicianDTO getTechnicianById(@PathVariable Long id) {return technicianService.getTechnicianById(id);}

    @PostMapping("/api/createTechnician")
    public void createTechnician(@RequestBody TechnicianDTO technicianDTO) {technicianService.putTechnician(technicianDTO);}

    @PostMapping("api/uploadData/ek")
    public ResponseEntity<?> uploadExcelFileForEk(@RequestParam("file") MultipartFile file) {
        processor.processUploadedExcelFileForEk(file);
        return ResponseEntity.ok("Upload successful");
    }
    @PostMapping("api/uploadData/tk")
    public ResponseEntity<?> processUploadedPdfForTk(@RequestParam("file") MultipartFile file) {
        processor.processUploadedPdfForTk(file);
        return ResponseEntity.ok("Upload successful");
    }
    @PostMapping("api/uploadData/ko")
    public ResponseEntity<?> uploadExcelFileForKo(@RequestParam("file") MultipartFile file) {
        processor.processUploadedExcelFileForKo(file);
        return ResponseEntity.ok("Upload successful");
    }
}
