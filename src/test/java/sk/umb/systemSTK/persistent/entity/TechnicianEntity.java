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
public class TechnicianEntity {
    @Id
    private Long technicianId;

    @Column(unique=true, nullable=false)
    private String name;

    @Column(unique=true, nullable=false)
    private String lastName;

    @OneToMany
    @JoinColumn
    private List<EKEntity> ekList;


    @OneToMany
    @JoinColumn
    private List<KOEntity> koList;


    @OneToMany
    @JoinColumn
    private List<TKEntity> tkList;

    @ManyToOne
    @JoinTable(
            name = "techniciansOfStation",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "technicianId")
    )
    private UserEntity user;

}
