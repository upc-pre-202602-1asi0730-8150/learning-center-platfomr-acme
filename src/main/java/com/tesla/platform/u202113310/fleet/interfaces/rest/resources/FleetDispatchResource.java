package com.tesla.platform.u202113310.fleet.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * Flattened representation of a fleet dispatch returned by the API.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Schema(name = "FleetDispatch", description = "Flattened fleet dispatch")
public record FleetDispatchResource(
        Long id,
        String dispatchCode,
        String vehicleId,
        Double originLat,
        Double originLng,
        Double destLat,
        Double destLng,
        BigDecimal totalDistanceKm,
        String departureTime,
        String estimatedArrival,
        BigDecimal totalRequiredEnergy,
        Integer waypointCount,
        String status,
        String safetyNotes
) {
}
