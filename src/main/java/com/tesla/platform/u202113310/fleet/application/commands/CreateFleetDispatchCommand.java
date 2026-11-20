package com.tesla.platform.u202113310.fleet.application.commands;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Command to create a fleet dispatch.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
public record CreateFleetDispatchCommand(
        String dispatchCode,
        UUID vehicleId,
        Double originLatitude,
        Double originLongitude,
        Double destinationLatitude,
        Double destinationLongitude,
        BigDecimal totalDistanceKm,
        LocalDateTime departureTime,
        LocalDateTime estimatedArrival,
        BigDecimal baseKwh,
        BigDecimal weatherMultiplier,
        List<WaypointItem> waypoints,
        String status,
        String safetyNotes
) {
    /**
     * Waypoint data carried by the command.
     *
     * @param stopSequence stop position
     * @param locationName location name
     */
    public record WaypointItem(Integer stopSequence, String locationName) {
    }
}
