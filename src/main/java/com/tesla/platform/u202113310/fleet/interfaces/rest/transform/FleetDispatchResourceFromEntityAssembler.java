package com.tesla.platform.u202113310.fleet.interfaces.rest.transform;

import com.tesla.platform.u202113310.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u202113310.fleet.interfaces.rest.resources.FleetDispatchResource;

/**
 * Maps a {@link FleetDispatch} to its flattened {@link FleetDispatchResource}.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
public class FleetDispatchResourceFromEntityAssembler {

    /**
     * @param entity aggregate
     * @return flattened resource with computed fields
     */
    public static FleetDispatchResource toResourceFromEntity(FleetDispatch entity) {
        var route = entity.getRouteData();
        var window = entity.getTemporalWindow();
        return new FleetDispatchResource(
                entity.getId(),
                entity.getDispatchCode().code(),
                entity.getVehicleId().value().toString(),
                route.originCoordinates().latitude(),
                route.originCoordinates().longitude(),
                route.destinationCoordinates().latitude(),
                route.destinationCoordinates().longitude(),
                route.totalDistanceKm(),
                window.departureTime().toString(),
                window.estimatedArrival().toString(),
                entity.getEnergyProfile().calculateTotalRequiredEnergy(),
                entity.getWaypoints().size(),
                entity.getStatus().name(),
                entity.getSafetyNotes());
    }
}
