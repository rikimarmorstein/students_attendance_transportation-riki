package app.core.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int id;

    @Column(nullable = false, unique = true)
    private String studentId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false , unique = true)
    private String phone;

    private int numClass;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="school_id")
    private School school;

    @Column(nullable = false )
    private boolean isTravel;

    @Enumerated(EnumType.STRING)
    private Cause cause;

    @Column(nullable = false)
    private String pickupAddress;
//@JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "bus_id")
//    private Transportation numBus;
    private int numBus;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Hour hour;

    private String remark;

    @Column(nullable = false)
    private String password;
//    @ManyToOne
//    @JoinColumn(name = "teacher_id")
//    private Teacher teacher;

    public  enum  Hour{
        THIRTEEN, SIXTEEN;
    }

    public  enum  Cause{
        RELEASE, ABSENCE, OTHER;
    }
}


