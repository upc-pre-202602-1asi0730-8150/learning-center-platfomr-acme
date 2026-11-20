package com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.adapters;

import com.tesla.platform.u202113310.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u202113310.fleet.domain.repositories.FleetDispatchRepository;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.assemblers.FleetDispatchPersistenceAssembler;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.repositories.FleetDispatchPersistenceRepository;
import org.springframework.stereotype.Repository;

/**
 * Adapter that implements the domain repository on top of Spring Data JPA.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Repository
public class FleetDispatchRepositoryImpl implements FleetDispatchRepository {

    private final FleetDispatchPersistenceRepository persistenceRepository;

    public FleetDispatchRepositoryImpl(FleetDispatchPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public FleetDispatch save(FleetDispatch fleetDispatch) {
        var savedEntity = persistenceRepository.save(
                FleetDispatchPersistenceAssembler.toPersistenceFromDomain(fleetDispatch));
        return FleetDispatchPersistenceAssembler.toDomainFromPersistence(savedEntity);
    }

    @Override
    public boolean existsByDispatchCodeAndVehicleId(DispatchCode dispatchCode, VehicleId vehicleId) {
        return persistenceRepository.countByDispatchCodeAndVehicleId(dispatchCode, vehicleId) > 0;
    }
}
