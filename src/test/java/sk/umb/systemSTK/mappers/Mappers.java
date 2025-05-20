package sk.umb.systemSTK.mappers;

import org.springframework.stereotype.Component;
import sk.umb.systemSTK.persistent.entity.*;
import sk.umb.systemSTK.utils.EkDTO;
import sk.umb.systemSTK.utils.KoDTO;
import sk.umb.systemSTK.utils.TechnicianDTO;
import sk.umb.systemSTK.utils.TkDTO;

import java.util.stream.Collectors;

@Component
public class Mappers {
    public EkDTO toEKDto(EKEntity ekEntity) {
        if (ekEntity == null) {
            return null;
        }

        EkDTO ekDTO = new EkDTO();
        ekDTO.setId(ekEntity.getVINEK());
        ekDTO.setDate(ekEntity.getDate());
        ekDTO.setControlType(ekEntity.getControlType());
        ekDTO.setEvaluationOfVehicle(ekEntity.getEvaluationOfVehicle());
        ekDTO.setECV(ekEntity.getECV());
        ekDTO.setCategory(ekEntity.getCategory());
        ekDTO.setBrand(ekEntity.getBrand());
        ekDTO.setModel(ekEntity.getModel());
        ekDTO.setSystemOfEmmission(ekEntity.getSystemOfEmissions());
        TechnicianControlIdentificatorsEntity identifier = ekEntity.getTechnicianIdentifier();
        if (identifier != null) {
            ekDTO.setTechnicianIdentifier(identifier.getIdentifier());
        } else {
            ekDTO.setTechnicianIdentifier(null);
        }
        ekDTO.setPrice(ekEntity.getPrice());
        ekDTO.setPrice(ekEntity.getPrice());

        return ekDTO;
    }

    public KoDTO toKODTO(KOEntity koEntity){
        if (koEntity == null) {
            return null;
        }

        KoDTO koDTO = new KoDTO();
        koDTO.setId(koEntity.getVINKO());
        koDTO.setDate(koEntity.getDate());
        koDTO.setControlType(koEntity.getControlType());
        koDTO.setCategory(koEntity.getCategory());
        TechnicianControlIdentificatorsEntity identifier = koEntity.getTechnicianIdentifier();
        if (identifier != null) {
            koDTO.setTechnicianIdentifier(identifier.getIdentifier());
        } else {
            koDTO.setTechnicianIdentifier(null);
        }
        koDTO.setPrice(koEntity.getPrice());

        return koDTO;
    }

    public TkDTO toTKDTO(TKEntity tkEntity){
        if (tkEntity == null) {
            return null;
        }
        TkDTO tkDTO = new TkDTO();
        tkDTO.setId(tkEntity.getVINTK());
        tkDTO.setDate(tkEntity.getDate());
        tkDTO.setControlType(tkEntity.getControlType());
        tkDTO.setEvaluationOfVehicle(tkEntity.getEvaluationOfVehicle());
        tkDTO.setECV(tkEntity.getECV());
        tkDTO.setCategory(tkEntity.getCategory());
        tkDTO.setBrand(tkEntity.getBrand());
        tkDTO.setModel(tkEntity.getModel());
        TechnicianControlIdentificatorsEntity identifier = tkEntity.getTechnicianIdentifier();
        if (identifier != null) {
            tkDTO.setTechnicianIdentifier(identifier.getIdentifier());
        } else {
            tkDTO.setTechnicianIdentifier(null);
        }
        tkDTO.setPrice(tkEntity.getPrice());
        tkDTO.setPrice(tkEntity.getPrice());

        return tkDTO;
    }

    public TechnicianDTO toTechnicianDTO(TechnicianEntity technicianEntity) {
        TechnicianDTO technicianDTO = new TechnicianDTO();
        technicianDTO.setId(technicianEntity.getTechnicianId());
        technicianDTO.setName(technicianEntity.getName());
        technicianDTO.setLastName(technicianEntity.getLastName());

        technicianDTO.setEkList(
                technicianEntity.getIdentifiers().stream()
                        .flatMap(i -> i.getEkList().stream())
                        .map(this::toEKDto)
                        .collect(Collectors.toList())
        );

        technicianDTO.setTkList(
                technicianEntity.getIdentifiers().stream()
                        .flatMap(i -> i.getTkList().stream())
                        .map(this::toTKDTO)
                        .collect(Collectors.toList())
        );

        technicianDTO.setKoList(
                technicianEntity.getIdentifiers().stream()
                        .flatMap(i -> i.getKoList().stream())
                        .map(this::toKODTO)
                        .collect(Collectors.toList())
        );

        return technicianDTO;
    }

}
