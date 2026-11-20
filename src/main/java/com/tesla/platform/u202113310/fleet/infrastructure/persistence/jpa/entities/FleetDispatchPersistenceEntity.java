package com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.entities;

import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.DispatchCode;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.DispatchStatus;
import com.tesla.platform.u202113310.fleet.domain.model.valueobjects.VehicleId;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.converters.DispatchCodePersistenceConverter;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.converters.VehicleIdPersistenceConverter;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.embeddables.EnergyProfilePersistenceEmbeddable;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.embeddables.RouteDataPersistenceEmbeddable;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.embeddables.TemporalWindowPersistenceEmbeddable;
import com.tesla.platform.u202113310.fleet.infrastructure.persistence.jpa.embeddables.WaypointPersistenceEmbeddable;
import com.tesla.platform.u202113310.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Persistence entity of a fleet dispatch. Composite uniqueness: dispatch_code + vehicle_id.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Entity
@Table(name = "fleet_dispatches",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_fleet_dispatches_dispatch_code_vehicle_id",
                columnNames = {"dispatch_code", "vehicle_id"}))
@Getter
@Setter
@NoArgsConstructor
public class FleetDispatchPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Convert(converter = DispatchCodePersistenceConverter.class)
    @Column(name = "dispatch_code", nullable = false, length = 10)
    private DispatchCode dispatchCode;

    @Convert(converter = VehicleIdPersistenceConverter.class)
    @Column(name = "vehicle_id", nullable = false, length = 36)
    private VehicleId vehicleId;

    @Embedded
    private RouteDataPersistenceEmbeddable routeData;

    @Embedded
    private TemporalWindowPersistenceEmbeddable temporalWindow;

    @Embedded
    private EnergyProfilePersistenceEmbeddable energyProfile;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "fleet_dispatch_waypoints",
            joinColumns = @JoinColumn(name = "fleet_dispatch_id"))
    private Set<WaypointPersistenceEmbeddable> waypoints = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private DispatchStatus status;

    @Column(name = "safety_notes", length = 1000)
    private String safetyNotes;
}
