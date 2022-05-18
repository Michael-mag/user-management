package com.michael.magaisa.user.cmd.api.Security;

public interface PasswordEncoder {
    String hashedPassword(String password);

}
