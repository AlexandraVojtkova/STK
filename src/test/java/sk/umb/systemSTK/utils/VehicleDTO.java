package sk.umb.systemSTK.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    String VINID;
    String ECV;
    String model;
    String brand;
    List<EkDTO> ekList;
    List<TkDTO> tkList;
    List<KoDTO> koList;
}
