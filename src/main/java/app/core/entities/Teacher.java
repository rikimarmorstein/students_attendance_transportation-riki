package app.core.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "teachers")
@ToString(exclude = "students")
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String phone;
    private int numClass;
    @Column(nullable = false)
    private String password;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="school_id")
    private School school;
//    @ManyToOne
//    @JoinColumn(name="")
//    private WeeklySystem weeklySystem ;

//    @JsonIgnore
//    @OneToOne
//    @JoinTable(name = "transportation_teachers", joinColumns = @JoinColumn(name = "teacher_id"), inverseJoinColumns = @JoinColumn(name = "transportation_id"))
//    private Transportation transportation ;
//    @JsonIgnore
//    @OneToMany(mappedBy = "student")
//    private List<Student> students;///


}
