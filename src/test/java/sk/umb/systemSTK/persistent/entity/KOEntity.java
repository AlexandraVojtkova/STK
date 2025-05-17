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
public class KOEntity {
    @Id
    private String VINKO;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String controlType;

    @Column(nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "technicianId", nullable = false)
    private TechnicianEntity technician;

    @Column(nullable = false)
    private Float price;
}
