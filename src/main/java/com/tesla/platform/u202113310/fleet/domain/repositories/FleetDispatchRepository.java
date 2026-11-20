package com.tesla.platform.u202113310.fleet.domain.repositories;

import com.tesla.platform.u202113310.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.VehicleId;

/**
 * Domain repository of {@link FleetDispatch}.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
public interface FleetDispatchRepository {

    /**
     * Persists a fleet dispatch.
     *
     * @param fleetDispatch aggregate to persist
     * @return the persisted aggregate with its generated id
     */
    FleetDispatch save(FleetDispatch fleetDispatch);

    /**
     * Checks the composite uniqueness rule.
     *
     * @param dispatchCode dispatch code
     * @param vehicleId    vehicle id
     * @return true if a dispatch with that exact combination already exists
     */
    boolean existsByDispatchCodeAndVehicleId(DispatchCode dispatchCode, VehicleId vehicleId);
}
