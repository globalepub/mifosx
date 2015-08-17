package org.mifosplatform.portfolio.client.handler;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.client.api.ClientApiConstants;
import org.mifosplatform.portfolio.client.service.ClientChargeWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@CommandType(entity = ClientApiConstants.CLIENT_CHARGES_RESOURCE_NAME, action = ClientApiConstants.CLIENT_CHARGE_ACTION_PAY)
public class PayClientChargeCommandHandler implements NewCommandSourceHandler {

    private final ClientChargeWritePlatformService writePlatformService;

    @Autowired
    public PayClientChargeCommandHandler(final ClientChargeWritePlatformService writePlatformService) {
        this.writePlatformService = writePlatformService;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {
        return this.writePlatformService.payCharge(command.getClientId(), command.entityId(), command);
    }

}
