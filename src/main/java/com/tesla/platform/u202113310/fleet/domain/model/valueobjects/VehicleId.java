package com.tesla.platform.u202113310.fleet.domain.model.valueobjects;

import java.util.UUID;

/**
 * Identifier of a vehicle, originated in another bounded context.
 *
 * @param value the vehicle UUID
 * @author Fabrizio Rafael Tello Palacios
 */
public record VehicleId(UUID value) {

    public VehicleId {
        if (value == null) {
            throw new IllegalArgumentException("vehicleId.required");
        }
    }
}
