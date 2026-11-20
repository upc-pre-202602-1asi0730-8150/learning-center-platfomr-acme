package com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Embeddable persistence representation of an energy profile.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnergyProfilePersistenceEmbeddable {

    @Column(name = "base_kwh", nullable = false, precision = 12, scale = 3)
    private BigDecimal baseKwh;

    @Column(name = "weather_multiplier", nullable = false, precision = 6, scale = 3)
    private BigDecimal weatherMultiplier;
}
