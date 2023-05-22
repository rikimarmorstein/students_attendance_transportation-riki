package app.core.repositories;

import app.core.entities.School;
import app.core.entities.Student;
import app.core.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    boolean existsByStudentId(String studentId);

    boolean existsByPhone(String phone);

    Student findByPhone(String phone);

    List<Student> findAllBySchoolId(int schoolId);

    List<Student> findAllByPhone(String phone);



    List<Student> findAllByNumClassAndSchoolId(int numClass, int schoolId);

    List<Student> findAllBySchoolIdAndIsTravelTrue(int schoolId);
    List<Student> findAllBySchoolIdAndNumBusIdAndIsTravelTrue(int schoolId, int numBus );

   //צריך למחוק את זה אבל לא מחקתי כי המתודות משתמשות בזה, אז צריך קודם לשנות אותן ואז להשתמש במתודה מעל ולא בזאתי
    List<Student> findAllByNumClass(int numClass);

    List<Student> findAllByNumBus(int bus);


    @Modifying
    @Query(value ="update `student` set isTravel = false  where id =?;", nativeQuery = true)
//    void NotTravel(int id);
    void isNotTravel(int id);

    @Modifying
    @Query(value ="update `student` set isTravel = true  where id =?;", nativeQuery = true)
//    void Travel(int id);
    void isTravel(int id);

    @Modifying
    @Query(value ="update `student` set cause = ?  where id =?;", nativeQuery = true)
    void whatCause(String cause ,int id);

    @Modifying
    @Query(value ="update `student` set hour = ?  where id =?;", nativeQuery = true)
    void whichHour(String hour ,int id);


}
