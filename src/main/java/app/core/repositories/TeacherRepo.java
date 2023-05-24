package app.core.repositories;

import app.core.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {

    boolean existsByPhone(String phone);
    boolean existsByPhoneAndPassword(String phone ,String password);
    Teacher findByPhoneAndPassword(String phone ,String password);

    List<Teacher> findAllBySchoolId(int schoolId);
}
