package com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Embeddable persistence representation of a waypoint (element collection item).
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WaypointPersistenceEmbeddable {

    @Column(name = "stop_sequence", nullable = false)
    private Integer stopSequence;

    @Column(name = "location_name", nullable = false, length = 100)
    private String locationName;
}
