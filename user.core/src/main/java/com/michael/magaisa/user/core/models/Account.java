package com.michael.magaisa.user.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Size(min = 2, max = 255, message = "username must be between 2 and 255 characters")
    private String username;

    @Size(min = 7, message = "password must be at least 7 characters long")
    private String password;

    @NotNull(message = "Specify at least one user role")
    private List<Role> roles;
}
