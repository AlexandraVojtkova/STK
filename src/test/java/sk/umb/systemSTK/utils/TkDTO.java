package sk.umb.systemSTK.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TkDTO {
    String id;
    Date date;
    String controlType;
    String evaluationOfVehicle;
    String ECV;
    String category;
    String brand;
    String model;
    String technicianIdentifier;
    Float price;
}
