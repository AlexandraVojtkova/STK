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
        @UniqueConstraint(columnNames = {"name", "lastName"})
})
public class TechnicianEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicianId;

    @Column(unique=true, nullable=false)
    private String name;

    @Column(unique=true, nullable=false)
    private String lastName;

    @OneToMany(mappedBy = "technician")
    private List<EKEntity> ekList;


    @OneToMany(mappedBy = "technician")
    private List<KOEntity> koList;


    @OneToMany(mappedBy = "technician")
    private List<TKEntity> tkList;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

}
