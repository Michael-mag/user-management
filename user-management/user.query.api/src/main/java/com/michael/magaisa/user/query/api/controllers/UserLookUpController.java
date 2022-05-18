package com.michael.magaisa.user.query.api.controllers;

import com.michael.magaisa.user.query.api.dto.UserLookUpResponse;
import com.michael.magaisa.user.query.api.queries.FindAllUsersQuery;
import com.michael.magaisa.user.query.api.queries.FindUsersByIdQuery;
import com.michael.magaisa.user.query.api.queries.SearchUsersQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userLookup")
public class UserLookUpController {
    private final QueryGateway queryGateway;

    @Autowired
    public UserLookUpController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookUpResponse> getAllUsers() {
        try {
            var query = new FindAllUsersQuery();
            var response = queryGateway.query(query, UserLookUpResponse.class).join();
            if(response.getUsers().isEmpty()|| response.getUsers() == null || response == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete the get all users request";
            System.out.println(e.toString());
            return new ResponseEntity<>(new UserLookUpResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byId/{id}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookUpResponse> getUserById(@PathVariable("id") String id) {
        try {
            var query = new FindUsersByIdQuery(id);
            var response = queryGateway.query(query, UserLookUpResponse.class).join();
            if(response.getUsers().isEmpty()|| response.getUsers() == null || response == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complet the get user by id request";
            System.out.println(e.toString());
            return new ResponseEntity<>(new UserLookUpResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/byFilter/{filter}")
    @PreAuthorize("hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<UserLookUpResponse> getUserByFilter(@PathVariable("filter") String filter) {
        try {
            var query = new SearchUsersQuery(filter);
            var response = queryGateway.query(query, UserLookUpResponse.class).join();
            if(response.getUsers().isEmpty()|| response.getUsers() == null || response == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Failed to complete the get user by filter request";
            System.out.println(e.toString());
            return new ResponseEntity<>(new UserLookUpResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
