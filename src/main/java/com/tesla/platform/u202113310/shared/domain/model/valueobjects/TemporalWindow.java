package com.tesla.platform.u202113310.shared.domain.model.valueobjects;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Temporal window of a trip.
 *
 * @param departureTime    departure date and time
 * @param estimatedArrival estimated arrival; must be after departure plus one hour
 * @author Fabrizio Rafael Tello Palacios
 */
public record TemporalWindow(LocalDateTime departureTime, LocalDateTime estimatedArrival) {

    private static final LocalTime NIGHT_START = LocalTime.of(20, 0);
    private static final LocalTime NIGHT_END = LocalTime.of(6, 0);

    public TemporalWindow {
        if (departureTime == null || estimatedArrival == null) {
            throw new IllegalArgumentException("temporalWindow.required");
        }
        if (!estimatedArrival.isAfter(departureTime.plusHours(1))) {
            throw new IllegalArgumentException("temporalWindow.arrival.invalid");
        }
    }

    /**
     * Tells whether the departure happens at night (between 20:00 and 06:00).
     *
     * @return true if the departure time is a night operation
     */
    public boolean isNightOperation() {
        LocalTime time = departureTime.toLocalTime();
        return !time.isBefore(NIGHT_START) || !time.isAfter(NIGHT_END);
    }
}
