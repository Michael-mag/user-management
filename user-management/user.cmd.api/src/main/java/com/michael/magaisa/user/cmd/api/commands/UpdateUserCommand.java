package com.michael.magaisa.user.cmd.api.commands;

import com.michael.magaisa.user.core.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String id;

    @NotNull(message = "No user details were provided")
    @Valid
    private User user;
}
