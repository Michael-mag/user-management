package com.michael.magaisa.user.query.api.handlers;

import com.michael.magaisa.user.core.events.UserRegisteredEvent;
import com.michael.magaisa.user.core.events.UserRemovedEvent;
import com.michael.magaisa.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
