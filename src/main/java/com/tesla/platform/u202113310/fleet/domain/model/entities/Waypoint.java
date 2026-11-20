package com.tesla.platform.u202113310.fleet.domain.model.entities;

/**
 * Intermediate stop of a fleet dispatch.
 *
 * @param stopSequence position of the stop, positive
 * @param locationName name of the location, not null or blank
 * @author Fabrizio Rafael Tello Palacios
 */
public record Waypoint(Integer stopSequence, String locationName) {

    public Waypoint {
        if (stopSequence == null || stopSequence <= 0) {
            throw new IllegalArgumentException("waypoint.stopSequence.invalid");
        }
        if (locationName == null || locationName.isBlank()) {
            throw new IllegalArgumentException("waypoint.locationName.required");
        }
    }
}
