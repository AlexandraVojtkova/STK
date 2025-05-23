package sk.umb.systemSTK.persistent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "technician_control_identificators_entity",
        uniqueConstraints = @UniqueConstraint(columnNames = {"identifier", "control_type"}))
public class TechnicianControlIdentificatorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String identifier;

    @Column(nullable = false)
    private String controlType;

    @ManyToOne
    @JoinColumn(name = "technician_id", nullable = true)
    private TechnicianEntity technician;

    @OneToMany(mappedBy = "technicianIdentifier")
    private List<EKEntity> ekList;

    @OneToMany(mappedBy = "technicianIdentifier")
    private List<TKEntity> tkList;

    @OneToMany(mappedBy = "technicianIdentifier")
    private List<KOEntity> koList;
}
