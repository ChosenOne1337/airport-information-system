package airport.services;

import airport.dtos.*;
import airport.filters.AirplaneFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

public interface AirplaneService extends Service<AirplaneDto, Long> {
    Page<RepairDto> getRepairs(Long airplaneId, Pageable pageable);

    Page<TechInspectionDto> getTechInspections(Long airplaneId, Pageable pageable);

    Page<AirplaneDto> search(AirplaneFilter filter, Pageable pageable);

//    Page<AirplaneDto> getAirplanesAtTheAirport(java.util.Date time, Pageable pageable);
}

