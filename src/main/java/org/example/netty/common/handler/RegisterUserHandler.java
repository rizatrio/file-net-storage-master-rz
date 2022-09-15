package org.example.netty.common.handler;

import io.netty.channel.ChannelHandlerContext;
import org.example.netty.common.dto.RegisterUserRequest;
import org.example.netty.common.dto.RegisterUserResponse;

public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse> {

    @Override
    public RegisterUserResponse handle(RegisterUserRequest request, ChannelHandlerContext ctx) {
        // ... логика регистрации
        return new RegisterUserResponse("OK");
    }
}
