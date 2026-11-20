package com.tesla.platform.u202113310.fleet.application.commandservices;

import com.tesla.platform.u202113310.fleet.application.commands.CreateFleetDispatchCommand;
import com.tesla.platform.u202113310.fleet.domain.model.aggregates.FleetDispatch;
import com.tesla.platform.u202113310.shared.application.result.ApplicationError;
import com.tesla.platform.u202113310.shared.application.result.Result;

/**
 * Command service of fleet dispatches.
 *
 * @author Fabrizio Rafael Tello Palacios
 */
public interface FleetDispatchCommandService {

    /**
     * Handles the creation of a fleet dispatch.
     *
     * @param command creation command
     * @return Success with the created aggregate, or Failure with an {@link ApplicationError}
     */
    Result<FleetDispatch, ApplicationError> handle(CreateFleetDispatchCommand command);
}
