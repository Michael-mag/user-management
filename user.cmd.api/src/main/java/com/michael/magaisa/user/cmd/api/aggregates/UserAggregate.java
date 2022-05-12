package com.michael.magaisa.user.cmd.api.aggregates;

import com.michael.magaisa.user.cmd.api.Security.PasswordEncoder;
import com.michael.magaisa.user.cmd.api.Security.PasswordEncoderImpl;
import com.michael.magaisa.user.cmd.api.commands.RegisterUserCommand;
import com.michael.magaisa.user.cmd.api.commands.RemoveUserCommand;
import com.michael.magaisa.user.cmd.api.commands.UpdateUserCommand;
import com.michael.magaisa.user.core.events.UserRegisteredEvent;
import com.michael.magaisa.user.core.events.UserRemovedEvent;
import com.michael.magaisa.user.core.events.UserUpdatedEvent;
import com.michael.magaisa.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;
    private final PasswordEncoder passwordEncoder;

    public UserAggregate(){
        passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        var newUser = command.getUser();
        newUser.setId(command.getId());
        var password = newUser.getAccount().getPassword();
        passwordEncoder = new PasswordEncoderImpl();
        var hashedPassword = passwordEncoder.hashedPassword(password);
        newUser.getAccount().setPassword(hashedPassword);

        var event = UserRegisteredEvent.builder()
                .userId(command.getId())
                .user(newUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public UserAggregate(UpdateUserCommand command) {
        var updateUser = command.getUser();
        updateUser.setId(command.getId());
        passwordEncoder = new PasswordEncoderImpl();
        var hashedPassword = passwordEncoder.hashedPassword(updateUser.getAccount().getPassword());
        updateUser.getAccount().setPassword(hashedPassword);

        var event = UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(updateUser)
                .build();

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public UserAggregate(RemoveUserCommand command) {
        passwordEncoder = new PasswordEncoderImpl();
        var event = new UserRemovedEvent();
        event.setId(command.getUserId());
        AggregateLifecycle.apply(event);

    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getUserId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}

