package com.michael.magaisa.user.core.events;

import com.michael.magaisa.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UserUpdatedEvent {
    @TargetAggregateIdentifier
    private String id;
    private User user;
}
