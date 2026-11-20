package com.tesla.platform.u202113310.fleet.interfaces.rest;

import com.tesla.platform.u202113310.fleet.application.commandservices.FleetDispatchCommandService;
import com.tesla.platform.u202113310.fleet.interfaces.rest.resources.CreateFleetDispatchResource;
import com.tesla.platform.u202113310.fleet.interfaces.rest.resources.FleetDispatchResource;
import com.tesla.platform.u202113310.fleet.interfaces.rest.transform.CreateFleetDispatchCommandFromResourceAssembler;
import com.tesla.platform.u202113310.fleet.interfaces.rest.transform.FleetDispatchResourceFromEntityAssembler;
import com.tesla.platform.u202113310.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for fleet dispatches.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
@RestController
@RequestMapping(value = "/api/v1/fleet-dispatches", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Fleet Dispatches", description = "Fleet dispatch management endpoints")
public class FleetDispatchesController {

    private final FleetDispatchCommandService fleetDispatchCommandService;

    public FleetDispatchesController(FleetDispatchCommandService fleetDispatchCommandService) {
        this.fleetDispatchCommandService = fleetDispatchCommandService;
    }

    /**
     * Creates a fleet dispatch.
     *
     * @param resource request body
     * @return 201 with the created resource, or the problem detail that matches the failure
     */
    @PostMapping
    @Operation(
            summary = "Create a new fleet dispatch",
            description = "Registers a fleet dispatch with route, temporal window, energy profile and waypoints.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Fleet dispatch created successfully",
                    content = @Content(schema = @Schema(implementation = FleetDispatchResource.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409",
                    description = "Conflict - dispatch code and vehicle id combination already exists")
    })
    public ResponseEntity<?> createFleetDispatch(@Valid @RequestBody CreateFleetDispatchResource resource) {
        var command = CreateFleetDispatchCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = fleetDispatchCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                FleetDispatchResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED);
    }
}
