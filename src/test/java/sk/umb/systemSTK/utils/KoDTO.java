package sk.umb.systemSTK.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class KoDTO {
    Long id;
    Date date;
    String controlType;
    String category;
    TechnicianDTO technician;
    String price;
}
