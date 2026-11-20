package com.tesla.platform.u202113310.fleet.domain.model.valueobjects;

import com.tesla.platform.u202113310.shared.domain.model.valueobjects.GeoCoordinates;

import java.math.BigDecimal;

/**
 * Route information of a dispatch.
 *
 * @param originCoordinates      origin point
 * @param destinationCoordinates destination point
 * @param totalDistanceKm        total distance in km, strictly greater than zero
 * @author Fabrizio Rafael Tello Palacios
 */
public record RouteData(GeoCoordinates originCoordinates,
                        GeoCoordinates destinationCoordinates,
                        BigDecimal totalDistanceKm) {

    public RouteData {
        if (originCoordinates == null || destinationCoordinates == null) {
            throw new IllegalArgumentException("routeData.required");
        }
        if (totalDistanceKm == null || totalDistanceKm.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("routeData.totalDistanceKm.invalid");
        }
    }
}
