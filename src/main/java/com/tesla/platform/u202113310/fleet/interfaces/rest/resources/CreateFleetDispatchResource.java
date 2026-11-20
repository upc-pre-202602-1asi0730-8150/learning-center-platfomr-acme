package com.tesla.platform.u202113310.fleet.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Request body to create a fleet dispatch (nested structure).
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@Schema(name = "CreateFleetDispatchRequest", description = "Request payload for creating a fleet dispatch")
public record CreateFleetDispatchResource(
        @Schema(description = "Dispatch code: DSP- followed by 6 digits", example = "DSP-123456")
        String dispatchCode,
        @Schema(description = "Vehicle identifier", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID vehicleId,
        @NotNull(message = "{validation.required}")
        RouteDataResource routeData,
        @NotNull(message = "{validation.required}")
        TemporalWindowResource temporalWindow,
        @NotNull(message = "{validation.required}")
        EnergyProfileResource energyProfile,
        @NotNull(message = "{validation.required}")
        @Size(min = 1, max = 5, message = "{validation.waypoints.size}")
        List<WaypointResource> waypoints,
        @Schema(description = "Optional. Defaults to PENDING", example = "PENDING")
        String status,
        @Schema(description = "Optional safety notes", example = "Avoid school zones")
        String safetyNotes
) {
    public record GeoCoordinatesResource(
            @Schema(example = "-12.0464") Double latitude,
            @Schema(example = "-77.0428") Double longitude) {
    }

    public record RouteDataResource(
            @NotNull(message = "{validation.required}") GeoCoordinatesResource originCoordinates,
            @NotNull(message = "{validation.required}") GeoCoordinatesResource destinationCoordinates,
            @Schema(example = "120.5") BigDecimal totalDistanceKm) {
    }

    public record TemporalWindowResource(
            @Schema(example = "2026-11-20T21:00:00") LocalDateTime departureTime,
            @Schema(example = "2026-11-21T01:00:00") LocalDateTime estimatedArrival) {
    }

    public record EnergyProfileResource(
            @Schema(example = "50.0") BigDecimal baseKwh,
            @Schema(example = "1.2") BigDecimal weatherMultiplier) {
    }

    public record WaypointResource(
            @Schema(example = "1") Integer stopSequence,
            @Schema(example = "Lima Hub") String locationName) {
    }
}
