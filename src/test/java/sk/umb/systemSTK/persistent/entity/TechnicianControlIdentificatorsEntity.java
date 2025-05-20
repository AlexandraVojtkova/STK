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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"identifier"})
})
public class TechnicianControlIdentificatorsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String identifier;

    @Column(nullable = false)
    private String controlType;

    @ManyToOne
    @JoinColumn(name = "technician_id", nullable = false)
    private TechnicianEntity technician;

    @OneToMany(mappedBy = "technicianIdentifier")
    private List<EKEntity> ekList;

    @OneToMany(mappedBy = "technicianIdentifier")
    private List<TKEntity> tkList;

    @OneToMany(mappedBy = "technicianIdentifier")
    private List<KOEntity> koList;
}
