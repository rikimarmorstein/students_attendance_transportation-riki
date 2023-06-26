package app.core.repositories;

import app.core.entities.Teacher;
import app.core.entities.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransportationRepo extends JpaRepository<Transportation, Integer> {

    boolean existsByNumBusAndSchoolId(int numBus, int schoolId);
    List<Transportation> findAllBySchoolId(int schoolId);
    Transportation findByNumBus(int numBus);
    boolean existsByNumBus(int numBus);

}
