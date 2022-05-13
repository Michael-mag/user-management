package com.michael.magaisa.user.cmd.api.controllers;

import com.michael.magaisa.user.cmd.api.commands.UpdateUserCommand;
import com.michael.magaisa.user.cmd.api.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/updateUser")
public class UpdateUserController {
    private final CommandGateway commandGateway;

    @Autowired
    public UpdateUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<BaseResponse> updateUser(
            @PathVariable(value="userId") String userId,
            @Valid @RequestBody UpdateUserCommand command) {
        try {
            command.setId(userId);
            commandGateway.send(command);
            return new ResponseEntity<>(new BaseResponse("User updated successfully"), HttpStatus.OK);
        } catch (Exception e) {
            var safeErrorMessage = "Error while updating user with id: " + command.getId();
            System.out.println(e.toString());
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
