package app.core.repositories;

import app.core.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepo extends JpaRepository<School, Integer> {

    boolean existsBySchoolName(String schoolName);
    boolean existsByPhone(String phone);

}
