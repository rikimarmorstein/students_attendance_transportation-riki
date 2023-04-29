package app.core.entities;
import lombok.*;

import javax.persistence.*;
//ghjgjk
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "students")
@ToString(exclude = "teacher")
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String phone;
    private int numClass;
    @Column(nullable = false)
    private boolean isTravel;
    private String pickupAddress;
    private int numBus;
    @Column(nullable = false)
    private Hour hour;
    private String remark;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public  enum  Hour{
        THIRTEEN, SIXTEEN;
    }
}

