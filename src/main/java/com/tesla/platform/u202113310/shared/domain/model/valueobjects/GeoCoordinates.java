package com.tesla.platform.u202113310.shared.domain.model.valueobjects;

/**
 * Geographic coordinates value object.
 *
 * @param latitude  latitude in degrees, between -90 and 90
 * @param longitude longitude in degrees, between -180 and 180
 * @author Fabrizio Rafael Tello Palacios
 */
public record GeoCoordinates(Double latitude, Double longitude) {

    public GeoCoordinates {
        if (latitude == null || !(latitude >= -90 && latitude <= 90)) {
            throw new IllegalArgumentException("geoCoordinates.latitude.invalid");
        }
        if (longitude == null || !(longitude >= -180 && longitude <= 180)) {
            throw new IllegalArgumentException("geoCoordinates.longitude.invalid");
        }
    }
}
