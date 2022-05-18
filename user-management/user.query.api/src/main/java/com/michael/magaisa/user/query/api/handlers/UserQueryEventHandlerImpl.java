package com.michael.magaisa.user.query.api.handlers;

import com.michael.magaisa.user.query.api.dto.UserLookUpResponse;
import com.michael.magaisa.user.query.api.queries.FindAllUsersQuery;
import com.michael.magaisa.user.query.api.queries.FindUsersByIdQuery;
import com.michael.magaisa.user.query.api.queries.SearchUsersQuery;
import com.michael.magaisa.user.query.api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserQueryEventHandlerImpl implements UserQueryEventHandler {
    private final UserRepository userRepository;

    @Autowired
    public UserQueryEventHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    @Override
    public UserLookUpResponse getUserById(FindUsersByIdQuery query) {
        var user = userRepository.findById(query.getId());
        return user.isPresent()? new UserLookUpResponse(user.get()): null;
    }

    @QueryHandler
    @Override
    public UserLookUpResponse searchUsers(SearchUsersQuery query) {
        var users = new ArrayList<>(userRepository.findByFilterRegex(query.getFilter()));
        return new UserLookUpResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookUpResponse getAllUsers(FindAllUsersQuery query) {
        var users = new ArrayList<>(userRepository.findAll());
        return new UserLookUpResponse(users);
    }
}
