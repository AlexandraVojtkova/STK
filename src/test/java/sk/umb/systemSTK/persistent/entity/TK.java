package sk.umb.systemSTK.persistent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TK {
    @Id
    private Long VINTK;

    @Column(unique=true)
    private Date date;

    @Column(unique=true)
    private String controlType;

    @Column(unique=true)
    private String EvaluationOfVehicle;

    @Column(unique=true)
    private String ECV;

    @Column(unique=true)
    private String category;

    @Column(unique=true)
    private String brand;

    @Column(unique=true)
    private String model;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Technician idOfTechnician;

    @Column(unique=true)
    private int price;
}
