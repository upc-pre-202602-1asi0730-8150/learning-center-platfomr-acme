package com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.repositories;

import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.entities.FleetDispatchPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository of {@link FleetDispatchPersistenceEntity}.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Repository
public interface FleetDispatchPersistenceRepository extends JpaRepository<FleetDispatchPersistenceEntity, Long> {

    @Query("select count(d) from FleetDispatchPersistenceEntity d "
            + "where d.dispatchCode = :dispatchCode and d.vehicleId = :vehicleId")
    long countByDispatchCodeAndVehicleId(@Param("dispatchCode") DispatchCode dispatchCode,
                                         @Param("vehicleId") VehicleId vehicleId);
}
