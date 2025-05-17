package sk.umb.systemSTK.persistent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "EK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EKEntity {
    @Id
    private String VINEK;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String controlType;

    @Column(nullable = false)
    private String evaluationOfVehicle;

    @Column(nullable = false)
    private String ECV;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String systemOfEmissions;

    @ManyToOne
    @JoinColumn(name = "technicianId", nullable = false)
    private TechnicianEntity technician;

    @Column(nullable = false)
    private Float price;

}
