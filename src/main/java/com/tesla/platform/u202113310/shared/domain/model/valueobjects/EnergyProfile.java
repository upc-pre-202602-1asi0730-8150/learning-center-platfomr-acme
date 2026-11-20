package com.tesla.platform.u202113310.shared.domain.model.valueobjects;

import java.math.BigDecimal;

/**
 * Energy consumption profile of a trip.
 *
 * @param baseKwh           base energy in kWh, strictly greater than zero
 * @param weatherMultiplier weather multiplier, greater than or equal to 1.0
 * @author Fabrizio Rafael Tello Palacios
 */
public record EnergyProfile(BigDecimal baseKwh, BigDecimal weatherMultiplier) {

    public EnergyProfile {
        if (baseKwh == null || baseKwh.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("energyProfile.baseKwh.invalid");
        }
        if (weatherMultiplier == null || weatherMultiplier.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException("energyProfile.weatherMultiplier.invalid");
        }
    }

    /**
     * Calculates the total required energy.
     *
     * @return baseKwh multiplied by weatherMultiplier
     */
    public BigDecimal calculateTotalRequiredEnergy() {
        return baseKwh.multiply(weatherMultiplier);
    }
}
