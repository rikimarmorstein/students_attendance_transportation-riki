package app.core.repositories;

import app.core.entities.Station;
import app.core.entities.Transportation;
import app.core.exception.SystemException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StationRepo extends JpaRepository<Station, Integer> {

    @Modifying
   @Query(value = "update `stations` set `num_station` =`num_station` +1 where `num_station`>=?;", nativeQuery = true)
    void updateNumStation(int numStation) throws SystemException;

    @Modifying
    @Query(value = "update `stations` set `num_station` =`num_station` -1 where `num_station`>=? and `num_station`<?;", nativeQuery = true)
    void updateNumStationBetweenlehaktin(int numStation) throws SystemException;
    @Modifying
    @Query(value = "update `stations` set `num_station` =`num_station` +1 where `num_station`>=? and `num_station`<?;", nativeQuery = true)
    void updateNumStationBetweenlehagdil(int numStation) throws SystemException;

//    List<Transportation> findAllBySchoolId(int schoolId);
}
