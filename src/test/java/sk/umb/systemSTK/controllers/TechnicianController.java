package sk.umb.systemSTK.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sk.umb.systemSTK.services.ExcelProcessor;
import sk.umb.systemSTK.services.TechnicianService;
import sk.umb.systemSTK.utils.CreateTechnicianDTO;
import sk.umb.systemSTK.utils.TechnicianDTO;

import java.util.List;

@RestController
public class TechnicianController {
    @Autowired
    TechnicianService technicianService;

    @Autowired
    ExcelProcessor processor;

    @GetMapping("/api/getAllTechnicians")
    public ResponseEntity<List<CreateTechnicianDTO>> getAllTechnicians() {
        List<CreateTechnicianDTO> technicians = technicianService.getAllTechnicians();
        return ResponseEntity.ok(technicians);
    }
    @GetMapping("/api/search")
    public ResponseEntity<List<TechnicianDTO>> searchTechnicians(@RequestParam String term) {
        List<TechnicianDTO> results = technicianService.findByNameOrLastName(term);
        return ResponseEntity.ok(results);
    }
    @GetMapping("/api/{id}")
    public ResponseEntity<CreateTechnicianDTO> getTechnicianById(@PathVariable Long id) {
        CreateTechnicianDTO technician = technicianService.findById(id);
        return ResponseEntity.ok(technician);
    }
    @PostMapping("/api/createTechnician")
    public ResponseEntity<Long> createTechnician(@RequestBody CreateTechnicianDTO createTechnicianDTO) {
        technicianService.createTechnician(createTechnicianDTO);
        Long technicianId = technicianService.createTechnician(createTechnicianDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(technicianId);
    }
    @PutMapping("/api/{id}")
    public ResponseEntity<Void> updateTechnician(
            @PathVariable Long id,
            @RequestBody CreateTechnicianDTO updatedDTO
    ) {
        technicianService.updateTechnician(id, updatedDTO);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/api/{id}")
    public ResponseEntity<Void> deleteTechnician(@PathVariable Long id) {
        technicianService.deleteTechnician(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/api/uploadData/ek")
    public ResponseEntity<?> uploadExcelFileForEk(@RequestParam("file") MultipartFile file) {
        processor.processUploadedExcelFileForEk(file);
        return ResponseEntity.ok("Upload successful");
    }
    @PostMapping("/api/uploadData/tk")
    public ResponseEntity<?> processUploadedPdfForTk(@RequestParam("file") MultipartFile file) {
        processor.processUploadedPdfForTk(file);
        return ResponseEntity.ok("Upload successful");
    }
    @PostMapping("/api/uploadData/ko")
    public ResponseEntity<?> uploadExcelFileForKo(@RequestParam("file") MultipartFile file) {
        processor.processUploadedExcelFileForKo(file);
        return ResponseEntity.ok("Upload successful");
    }
}
