package com.michael.magaisa.user.query.api.handlers;

import com.michael.magaisa.user.query.api.dto.UserLookUpResponse;
import com.michael.magaisa.user.query.api.queries.FindAllUsersQuery;
import com.michael.magaisa.user.query.api.queries.FindUsersByIdQuery;
import com.michael.magaisa.user.query.api.queries.SearchUsersQuery;

public interface UserQueryEventHandler {
    UserLookUpResponse getUserById(FindUsersByIdQuery query);
    UserLookUpResponse searchUsers(SearchUsersQuery query);
    UserLookUpResponse getAllUsers(FindAllUsersQuery query);
}
