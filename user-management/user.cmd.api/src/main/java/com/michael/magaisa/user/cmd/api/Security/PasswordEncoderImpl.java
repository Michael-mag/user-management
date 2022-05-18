package com.michael.magaisa.user.cmd.api.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderImpl implements PasswordEncoder {

    public static final int PASSWORD_ENCODER_STRENGTH = 12;

    @Override
    public String hashedPassword(String password) {
        var encoder = new BCryptPasswordEncoder(PASSWORD_ENCODER_STRENGTH);
        return encoder.encode(password);
    }
}
