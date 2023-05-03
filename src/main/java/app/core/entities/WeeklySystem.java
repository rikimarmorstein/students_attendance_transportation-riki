package app.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class WeeklySystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @JsonIgnore
//    @OneToMany(mappedBy = "teacher")
    private int teacher_id;
//    @JsonIgnore
//    @OneToMany(mappedBy = "numBus")
    private int numBus_id;
    private Day day;


    public enum Day{
        SUNDAY,MONDAY, TUESDAY,WEDNESDAY,THURSDAY,FRIDAY;
            }

}
