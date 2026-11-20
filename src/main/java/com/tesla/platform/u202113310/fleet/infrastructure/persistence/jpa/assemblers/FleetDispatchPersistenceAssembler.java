package com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.assemblers;

import com.tesla.platform.u202113310.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u202113310.fleet.domain.model.entities.Waypoint;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.RouteData;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.embeddables.EnergyProfilePersistenceEmbeddable;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.embeddables.RouteDataPersistenceEmbeddable;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.embeddables.TemporalWindowPersistenceEmbeddable;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.embeddables.WaypointPersistenceEmbeddable;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.entities.FleetDispatchPersistenceEntity;
import com.tesla.platform.u202113310.shared.domain.model.valueobjects.EnergyProfile;
import com.tesla.platform.u202113310.shared.domain.model.valueobjects.GeoCoordinates;
import com.tesla.platform.u202113310.shared.domain.model.valueobjects.TemporalWindow;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Maps between the {@link FleetDispatch} aggregate and its persistence entity.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
public final class FleetDispatchPersistenceAssembler {

    private FleetDispatchPersistenceAssembler() {
    }

    /**
     * @param entity persistence entity
     * @return the domain aggregate
     */
    public static FleetDispatch toDomainFromPersistence(FleetDispatchPersistenceEntity entity) {
        if (entity == null) {
            return null;
        }
        var route = entity.getRouteData();
        var window = entity.getTemporalWindow();
        var energy = entity.getEnergyProfile();
        return FleetDispatch.reconstitute(
                entity.getId(),
                entity.getDispatchCode(),
                entity.getVehicleId(),
                new RouteData(
                        new GeoCoordinates(route.getOriginLatitude(), route.getOriginLongitude()),
                        new GeoCoordinates(route.getDestinationLatitude(), route.getDestinationLongitude()),
                        route.getTotalDistanceKm()),
                new TemporalWindow(window.getDepartureTime(), window.getEstimatedArrival()),
                new EnergyProfile(energy.getBaseKwh(), energy.getWeatherMultiplier()),
                entity.getWaypoints().stream()
                        .map(w -> new Waypoint(w.getStopSequence(), w.getLocationName()))
                        .collect(Collectors.toSet()),
                entity.getStatus(),
                entity.getSafetyNotes());
    }

    /**
     * @param dispatch domain aggregate
     * @return the persistence entity
     */
    public static FleetDispatchPersistenceEntity toPersistenceFromDomain(FleetDispatch dispatch) {
        var route = dispatch.getRouteData();
        var entity = new FleetDispatchPersistenceEntity();
        entity.setId(dispatch.getId());
        entity.setDispatchCode(dispatch.getDispatchCode());
        entity.setVehicleId(dispatch.getVehicleId());
        entity.setRouteData(new RouteDataPersistenceEmbeddable(
                route.originCoordinates().latitude(),
                route.originCoordinates().longitude(),
                route.destinationCoordinates().latitude(),
                route.destinationCoordinates().longitude(),
                route.totalDistanceKm()));
        entity.setTemporalWindow(new TemporalWindowPersistenceEmbeddable(
                dispatch.getTemporalWindow().departureTime(),
                dispatch.getTemporalWindow().estimatedArrival()));
        entity.setEnergyProfile(new EnergyProfilePersistenceEmbeddable(
                dispatch.getEnergyProfile().baseKwh(),
                dispatch.getEnergyProfile().weatherMultiplier()));
        entity.setWaypoints(dispatch.getWaypoints().stream()
                .map(w -> new WaypointPersistenceEmbeddable(w.stopSequence(), w.locationName()))
                .collect(Collectors.toCollection(HashSet::new)));
        entity.setStatus(dispatch.getStatus());
        entity.setSafetyNotes(dispatch.getSafetyNotes());
        return entity;
    }
}
