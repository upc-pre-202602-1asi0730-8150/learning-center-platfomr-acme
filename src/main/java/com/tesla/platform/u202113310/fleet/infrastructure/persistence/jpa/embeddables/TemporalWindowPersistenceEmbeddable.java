package com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Embeddable persistence representation of a temporal window.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemporalWindowPersistenceEmbeddable {

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "estimated_arrival", nullable = false)
    private LocalDateTime estimatedArrival;
}
