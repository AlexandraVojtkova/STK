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
public class KO {
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
    private Technician idOfTechnician;

    @Column(unique=true)
    private String price;
}
