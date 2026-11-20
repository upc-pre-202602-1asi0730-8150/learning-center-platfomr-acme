package com.tesla.platform.u202113310.fleet.interfaces.rest.transform;

import com.tesla.platform.u202113310.fleet.application.commands.CreateFleetDispatchCommand;
import com.tesla.platform.u202113310.fleet.interfaces.rest.resources.CreateFleetDispatchResource;

import java.util.List;

/**
 * Maps a {@link CreateFleetDispatchResource} to a {@link CreateFleetDispatchCommand}.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
public class CreateFleetDispatchCommandFromResourceAssembler {

    /**
     * @param resource request body
     * @return the equivalent command
     */
    public static CreateFleetDispatchCommand toCommandFromResource(CreateFleetDispatchResource resource) {
        var route = resource.routeData();
        var window = resource.temporalWindow();
        var energy = resource.energyProfile();
        List<CreateFleetDispatchCommand.WaypointItem> waypoints = resource.waypoints().stream()
                .map(w -> new CreateFleetDispatchCommand.WaypointItem(w.stopSequence(), w.locationName()))
                .toList();
        return new CreateFleetDispatchCommand(
                resource.dispatchCode(),
                resource.vehicleId(),
                route.originCoordinates().latitude(),
                route.originCoordinates().longitude(),
                route.destinationCoordinates().latitude(),
                route.destinationCoordinates().longitude(),
                route.totalDistanceKm(),
                window.departureTime(),
                window.estimatedArrival(),
                energy.baseKwh(),
                energy.weatherMultiplier(),
                waypoints,
                resource.status(),
                resource.safetyNotes());
    }
}
