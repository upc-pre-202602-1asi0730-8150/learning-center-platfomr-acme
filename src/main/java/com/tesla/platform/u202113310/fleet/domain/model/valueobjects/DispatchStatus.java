package com.tesla.platform.u202113310.fleet.domain.model.valueobjects;

import java.util.Locale;

/**
 * Lifecycle status of a fleet dispatch.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
public enum DispatchStatus {
    PENDING,
    EN_ROUTE,
    CHARGING,
    COMPLETED,
    CANCELLED;

    /**
     * Converts text into a status. Null or blank text yields {@link #PENDING}.
     *
     * @param value status name
     * @return the matching status
     * @throws IllegalArgumentException if the text is not a valid status
     */
    public static DispatchStatus fromText(String value) {
        if (value == null || value.isBlank()) {
            return PENDING;
        }
        try {
            return valueOf(value.trim().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("dispatchStatus.invalid");
        }
    }
}
