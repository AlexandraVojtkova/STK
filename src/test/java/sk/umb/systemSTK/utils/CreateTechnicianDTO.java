package sk.umb.systemSTK.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTechnicianDTO {
    Long id;
    String name;
    String lastName;
    List<TechnicianControlIdentificatorsDTO> identificators;
}