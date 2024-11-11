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
    private Long VINKO;

    @Column(unique=true)
    private Date date;

    @Column(unique=true)
    private String controlType;

    @Column(unique=true)
    private String category;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TechnicianEntity idOfTechnician;

    @Column(unique=true)
    private String price;
}
