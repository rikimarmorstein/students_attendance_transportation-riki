package app.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "stations")
//@ToString(exclude = "students")
@Builder
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int numStation;
    private String nameStation;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="transportation_id")
    private Transportation transportation;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="school_id")
    private School school;
}
