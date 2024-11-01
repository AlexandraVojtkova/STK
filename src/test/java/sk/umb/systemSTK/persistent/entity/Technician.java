package sk.umb.systemSTK.persistent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Technician {
    @Id
    private Long technicianId;

    @Column(unique=true, nullable=false)
    private String name;

    @Column(unique=true, nullable=false)
    private String lastName;

    @OneToMany
    @JoinColumn
    private List<EK> ekList;


    @OneToMany
    @JoinColumn
    private List<KO> koList;


    @OneToMany
    @JoinColumn
    private List<TK> tkList;

    @ManyToOne
    @JoinTable(
            name = "techniciansOfStation",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "technicianId")
    )
    private User user;

}
