package com.tesla.platform.u202113310.fleet.application.internal.commandservices;

import com.tesla.platform.u202113310.fleet.application.commandservices.FleetDispatchCommandService;
import com.tesla.platform.u202113310.fleet.application.commands.CreateFleetDispatchCommand;
import com.tesla.platform.u202113310.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u202113310.fleet.domain.model.entities.Waypoint;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.DispatchStatus;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.RouteData;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u202113310.fleet.domain.repositories.FleetDispatchRepository;
import com.tesla.platform.u202113310.shared.application.result.ApplicationError;
import com.tesla.platform.u202113310.shared.application.result.Result;
import com.tesla.platform.u202113310.shared.domain.model.valueobjects.EnergyProfile;
import com.tesla.platform.u202113310.shared.domain.model.valueobjects.GeoCoordinates;
import com.tesla.platform.u202113310.shared.domain.model.valueobjects.TemporalWindow;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link FleetDispatchCommandService}.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Service
public class FleetDispatchCommandServiceImpl implements FleetDispatchCommandService {

    private static final String RESOURCE = "FleetDispatch";

    private final FleetDispatchRepository fleetDispatchRepository;

    public FleetDispatchCommandServiceImpl(FleetDispatchRepository fleetDispatchRepository) {
        this.fleetDispatchRepository = fleetDispatchRepository;
    }

    @Override
    @Transactional
    public Result<FleetDispatch, ApplicationError> handle(CreateFleetDispatchCommand command) {
        FleetDispatch fleetDispatch;
        try {
            fleetDispatch = buildFleetDispatch(command);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError(RESOURCE, e.getMessage()));
        }
        if (fleetDispatchRepository.existsByDispatchCodeAndVehicleId(
                fleetDispatch.getDispatchCode(), fleetDispatch.getVehicleId())) {
            return Result.failure(ApplicationError.conflict(RESOURCE, "fleetDispatch.duplicate"));
        }
        return Result.success(fleetDispatchRepository.save(fleetDispatch));
    }

    private FleetDispatch buildFleetDispatch(CreateFleetDispatchCommand command) {
        if (command.waypoints() == null) {
            throw new IllegalArgumentException("waypoints.size.invalid");
        }
        Set<Waypoint> waypoints = new HashSet<>();
        command.waypoints().forEach(item ->
                waypoints.add(new Waypoint(item.stopSequence(), item.locationName())));
        return new FleetDispatch(
                new DispatchCode(command.dispatchCode()),
                new VehicleId(command.vehicleId()),
                new RouteData(
                        new GeoCoordinates(command.originLatitude(), command.originLongitude()),
                        new GeoCoordinates(command.destinationLatitude(), command.destinationLongitude()),
                        command.totalDistanceKm()),
                new TemporalWindow(command.departureTime(), command.estimatedArrival()),
                new EnergyProfile(command.baseKwh(), command.weatherMultiplier()),
                waypoints,
                DispatchStatus.fromText(command.status()),
                command.safetyNotes());
    }
}
