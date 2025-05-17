package sk.umb.systemSTK.mappers;

import org.springframework.stereotype.Component;
import sk.umb.systemSTK.persistent.entity.EKEntity;
import sk.umb.systemSTK.persistent.entity.KOEntity;
import sk.umb.systemSTK.persistent.entity.TKEntity;
import sk.umb.systemSTK.utils.EkDTO;
import sk.umb.systemSTK.utils.KoDTO;
import sk.umb.systemSTK.utils.TkDTO;

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
        ekDTO.setTechnicianId(ekEntity.getTechnician().getTechnicianId());
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
        koDTO.setTechnicianId(koEntity.getTechnician().getTechnicianId());
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
        tkDTO.setTechnicianId(tkEntity.getTechnician().getTechnicianId());
        tkDTO.setPrice(tkEntity.getPrice());

        return tkDTO;
    }
}
