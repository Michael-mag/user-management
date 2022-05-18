package com.michael.magaisa.user.core.events;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class UserRemovedEvent {
    @TargetAggregateIdentifier
    private String id;
}
