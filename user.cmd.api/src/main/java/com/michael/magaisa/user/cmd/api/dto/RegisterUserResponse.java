package com.michael.magaisa.user.cmd.api.dto;

import com.michael.magaisa.user.core.dto.BaseResponse;

public class RegisterUserResponse extends BaseResponse {
    private String id;

    public RegisterUserResponse(String message){
        super(message);
    }
}
