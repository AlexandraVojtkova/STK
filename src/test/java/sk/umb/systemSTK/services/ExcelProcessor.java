package sk.umb.systemSTK.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import sk.umb.systemSTK.utils.EkDTO;
import sk.umb.systemSTK.utils.KoDTO;
import sk.umb.systemSTK.utils.TkDTO;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ExcelProcessor {

    @Autowired
    KOService koService;
    @Autowired
    TKService tkService;
    @Autowired
    EKService ekService;


    public void processUploadedExcelFileForEk(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                String vin = row.getCell(6).getStringCellValue();
                Date date = null;
                if (row.getCell(0).getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(row.getCell(0))) {
                    // Ak je to reálny dátum (číselný typ + formátovaný ako dátum)
                    date = row.getCell(0).getDateCellValue();
                } else if (row.getCell(0).getCellType() == CellType.STRING) {
                    // Ak je to textový reťazec
                    String dateStr = row.getCell(0).getStringCellValue().trim();
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        date = sdf.parse(dateStr);
                    } catch (ParseException e) {
                        System.err.println("Nesprávny formát dátumu: " + dateStr);
                    }
                }

                String controlType = row.getCell(1).getStringCellValue();
                String evaluationOfVehicle = row.getCell(3).getStringCellValue();
                String ECV = row.getCell(5).getStringCellValue();
                String category = row.getCell(7).getStringCellValue();
                String brand = row.getCell(8).getStringCellValue();
                String model = row.getCell(9).getStringCellValue();
                String systemOfEmmission = row.getCell(10).getStringCellValue();
                String technicianId = null;
                String code = row.getCell(4).getStringCellValue().trim();
                String[] parts = code.split("-");

                if (parts.length > 1) {
                    if (parts[1].matches("\\d+")) { // kontroluje, že obsahuje len číslice
                        technicianId = parts[1];
                    } else {
                        System.err.println("Technician ID nie je validné číslo: " + parts[1]);
                    }
                }
                Float price = null;
                if (row.getCell(12).getCellType() == CellType.NUMERIC) {
                    price = (float) row.getCell(12).getNumericCellValue();
                } else if (row.getCell(12).getCellType() == CellType.STRING) {
                    String priceStr = row.getCell(12).getStringCellValue().trim().replace(",", ".");
                    try {
                        price = Float.parseFloat(priceStr);
                    } catch (NumberFormatException e) {
                        System.err.println("Neplatná cena: " + priceStr);
                    }
                }

                EkDTO ekDTO = new EkDTO();
                ekDTO.setId(vin);
                ekDTO.setDate(date);
                ekDTO.setControlType(controlType);
                ekDTO.setEvaluationOfVehicle(evaluationOfVehicle);
                ekDTO.setECV(ECV);
                ekDTO.setCategory(category);
                ekDTO.setBrand(brand);
                ekDTO.setModel(model);
                ekDTO.setSystemOfEmmission(systemOfEmmission);
                ekDTO.setTechnicianIdentifier(technicianId);
                ekDTO.setPrice(price);

                this.ekService.createEK(ekDTO);
            }

        } catch (IOException e) {
            throw new RuntimeException("Chyba pri spracovaní súboru", e);
        }

    }

    public void processUploadedExcelFileForKo(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                String vin = row.getCell(6).getStringCellValue();

                Date date = null;
                if (row.getCell(3).getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(row.getCell(3))) {
                    // Ak je to reálny dátum (číselný typ + formátovaný ako dátum)
                    date = row.getCell(3).getDateCellValue();
                } else if (row.getCell(3).getCellType() == CellType.STRING) {
                    // Ak je to textový reťazec
                    String dateStr = row.getCell(3).getStringCellValue().trim();
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        date = sdf.parse(dateStr);
                    } catch (ParseException e) {
                        System.err.println("Nesprávny formát dátumu: " + dateStr);
                    }
                }

                String controlType = row.getCell(4).getStringCellValue();
                String category = row.getCell(5).getStringCellValue();

                String technicianId = null;
                String rawCode = row.getCell(2).getCellType() == CellType.NUMERIC
                        ? String.valueOf((long) row.getCell(2).getNumericCellValue())  // číslo → string (bez .0)
                        : row.getCell(2).getStringCellValue().trim();

                if (rawCode.length() >= 7) {
                    String technicianCode = rawCode.substring(4, 7);
                    if (technicianCode.matches("\\d+")) {
                        technicianId = technicianCode;
                    } else {
                        System.err.println("Technician ID nie je validné číslo: " + technicianCode);
                    }
                }

                Float price = null;
                if (row.getCell(6).getCellType() == CellType.NUMERIC) {
                    price = (float) row.getCell(6).getNumericCellValue();
                } else if (row.getCell(6).getCellType() == CellType.STRING) {
                    String priceStr = row.getCell(6).getStringCellValue().trim().replace(",", ".");
                    try {
                        price = Float.parseFloat(priceStr);
                    } catch (NumberFormatException e) {
                        System.err.println("Neplatná cena: " + priceStr);
                    }
                }

                KoDTO koDTO = new KoDTO();
                koDTO.setId(vin);
                koDTO.setDate(date);
                koDTO.setControlType(controlType);
                koDTO.setCategory(category);
                koDTO.setTechnicianIdentifier(technicianId);
                koDTO.setPrice(price);

                this.koService.createKO(koDTO);
            }

        } catch (IOException e) {
            throw new RuntimeException("Chyba pri spracovaní súboru", e);
        }

    }

    public void processUploadedPdfForTk(MultipartFile file) {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            String rawText = stripper.getText(document);

            String[] rows = rawText.split("\\r?\\n");

            for (int i = 1; i < rows.length; i++) { // preskočíme 1. riadok
                String line = rows[i].trim();
                if (line.isEmpty()) continue;

                // Prvý údaj (kód protokolu) je pred prvým znakom |:
                int firstPipeIndex = line.indexOf('|');
                if (firstPipeIndex == -1) continue; // neplatný riadok

                String protocolCode = line.substring(0, firstPipeIndex).trim();
                String remainingLine = line.substring(firstPipeIndex + 1).trim();

                // Extrahuj segment 005 z kódu protokolu
                String[] protocolParts = protocolCode.split("-");
                if (protocolParts.length < 2) continue;

                String technicianId = protocolParts[1];

                // Rozdelíme zvyšok riadku cez |, odstránime úvodzovky
                String[] fields = remainingLine.split("\\|");

                for (int j = 0; j < fields.length; j++) {
                    fields[j] = fields[j].replaceAll("^\"|\"$", "").trim();
                }

                // PRÍKLAD: vyber len určité polia
                String controlType = fields.length > 1 ? fields[1] : null;
                Float price = null;
                if (fields.length > 2) {
                    try {
                        String priceStr = fields[2].replace("\"", "").replace(" ", "").replace(",", ".").trim();
                        price = Float.parseFloat(priceStr);
                    } catch (NumberFormatException e) {
                        System.err.println("Chybný formát ceny: " + fields[2]);
                    }
                }
                String brand = fields.length > 3 ? fields[3] : null;
                String model = fields.length > 4 ? fields[4] : null;
                String category = fields.length > 5 ? fields[5] : null;
                Date date = null;
                if (fields.length > 6) {
                    try {
                        String fullDateStr = fields[6].replace("\"", "").trim();
                        String dateOnly = fullDateStr.split(" ")[0];
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        date = sdf.parse(dateOnly);
                    } catch (Exception e) {
                        System.err.println("Chybný formát dátumu: " + fields[6]);
                    }
                }
                String evaluationOfVehicle = fields.length > 7 ? fields[7] : null;
                String ECV = fields.length > 13 ? fields[13] : null;
                String vin = fields.length > 14 ? fields[14] : null;

                // Uloženie (napr. vytvorenie nového technika alebo nájdenie existujúceho)
                TkDTO tkDTO = new TkDTO();
                tkDTO.setId(vin);
                tkDTO.setDate(date);
                tkDTO.setControlType(controlType);
                tkDTO.setEvaluationOfVehicle(evaluationOfVehicle);
                tkDTO.setECV(ECV);
                tkDTO.setCategory(category);
                tkDTO.setBrand(brand);
                tkDTO.setModel(model);
                tkDTO.setTechnicianIdentifier(technicianId);
                tkDTO.setPrice(price);

                this.tkService.createTK(tkDTO);
//                tkRepository.save(tkDTO);
            }

        } catch (IOException e) {
            throw new RuntimeException("Chyba pri spracovaní PDF", e);
        }
    }

}
