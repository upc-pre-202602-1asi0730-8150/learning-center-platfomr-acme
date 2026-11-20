package com.tesla.platform.u202113310.fleet.domain.model.aggregates;

import com.tesla.platform.u202113310.fleet.domain.model.entities.Waypoint;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.DispatchStatus;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.RouteData;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u202113310.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import com.tesla.platform.u202113310.shared.domain.model.valueobjects.EnergyProfile;
import com.tesla.platform.u202113310.shared.domain.model.valueobjects.TemporalWindow;
import lombok.Getter;

import java.util.Set;

/**
 * Aggregate root that represents the dispatch of an autonomous vehicle.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Getter
public class FleetDispatch extends AbstractDomainAggregateRoot<FleetDispatch> {

    private static final int MIN_WAYPOINTS = 1;
    private static final int MAX_WAYPOINTS = 5;

    private final Long id;
    private final DispatchCode dispatchCode;
    private final VehicleId vehicleId;
    private final RouteData routeData;
    private final TemporalWindow temporalWindow;
    private final EnergyProfile energyProfile;
    private final Set<Waypoint> waypoints;
    private final DispatchStatus status;
    private final String safetyNotes;

    /**
     * Creates a fleet dispatch enforcing its business rules.
     *
     * @param id             identifier, null for a new dispatch
     * @param dispatchCode   dispatch code
     * @param vehicleId      vehicle identifier
     * @param routeData      route data
     * @param temporalWindow temporal window
     * @param energyProfile  energy profile
     * @param waypoints      between 1 and 5 waypoints
     * @param status         status; PENDING when null
     * @param safetyNotes    optional safety notes
     * @throws IllegalArgumentException if a required value is missing or the waypoint count is invalid
     */
    public FleetDispatch(Long id, DispatchCode dispatchCode, VehicleId vehicleId, RouteData routeData,
                         TemporalWindow temporalWindow, EnergyProfile energyProfile,
                         Set<Waypoint> waypoints, DispatchStatus status, String safetyNotes) {
        if (dispatchCode == null || vehicleId == null || routeData == null
                || temporalWindow == null || energyProfile == null) {
            throw new IllegalArgumentException("fleetDispatch.required");
        }
        if (waypoints == null || waypoints.size() < MIN_WAYPOINTS || waypoints.size() > MAX_WAYPOINTS) {
            throw new IllegalArgumentException("waypoints.size.invalid");
        }
        this.id = id;
        this.dispatchCode = dispatchCode;
        this.vehicleId = vehicleId;
        this.routeData = routeData;
        this.temporalWindow = temporalWindow;
        this.energyProfile = energyProfile;
        this.waypoints = Set.copyOf(waypoints);
        this.status = status == null ? DispatchStatus.PENDING : status;
        this.safetyNotes = safetyNotes;
    }

    /**
     * Creates a new (not yet persisted) fleet dispatch.
     */
    public FleetDispatch(DispatchCode dispatchCode, VehicleId vehicleId, RouteData routeData,
                         TemporalWindow temporalWindow, EnergyProfile energyProfile,
                         Set<Waypoint> waypoints, DispatchStatus status, String safetyNotes) {
        this(null, dispatchCode, vehicleId, routeData, temporalWindow, energyProfile,
                waypoints, status, safetyNotes);
    }

    /**
     * Rebuilds a fleet dispatch from persisted data.
     */
    public static FleetDispatch reconstitute(Long id, DispatchCode dispatchCode, VehicleId vehicleId,
                                             RouteData routeData, TemporalWindow temporalWindow,
                                             EnergyProfile energyProfile, Set<Waypoint> waypoints,
                                             DispatchStatus status, String safetyNotes) {
        return new FleetDispatch(id, dispatchCode, vehicleId, routeData, temporalWindow,
                energyProfile, waypoints, status, safetyNotes);
    }
}
