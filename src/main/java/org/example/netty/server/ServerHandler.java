package org.example.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.ObjectRegistry;
import org.example.netty.common.AuthService;
import org.example.netty.common.dto.*;
import org.example.netty.common.handler.HandlerRegistry;
import org.example.netty.common.handler.RequestHandler;

public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        BasicRequest request = (BasicRequest) msg;
        AuthService authService = ObjectRegistry.getInstance(AuthService.class);
        String authToken = request.getAuthToken();
        if (!(request instanceof RegisterUserRequest) && !authService.auth(authToken)) {
            BasicResponse authErrorResponse = new BasicResponse("Not authenticated!");
            ctx.writeAndFlush(authErrorResponse);
        }

        RequestHandler handler = HandlerRegistry.getHandler(request.getClass());
        BasicResponse response = handler.handle(request, ctx);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
    }
}
