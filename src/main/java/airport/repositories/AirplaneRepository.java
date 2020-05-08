package airport.repositories;

import airport.entities.Airplane;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Collection;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

    @Query("select distinct a from Airplane a join a.repairs r " +
            "where function('date', r.startTime) between :minDate and :maxDate")
    Page<Airplane> findAllRepairedAirplanesBetween(
            @Param("minDate") Date minDate,
            @Param("maxDate") Date maxDate,
            Pageable pageable
    );

    @Query("select distinct a from Airplane a join a.techInspections t " +
            "where function('date', t.inspectionTime) between :minDate and :maxDate")
    Page<Airplane> findAllInspectedAirplanesBetween(
            @Param("minDate") Date minDate,
            @Param("maxDate") Date maxDate,
            Pageable pageable
    );

//    @Query(
//
//    )
//    Page<Airplane> findAllCurrentlyAtTheAirport(Pageable pageable);


}
