package org.example.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import javafx.application.Platform;
import org.example.ObjectRegistry;
import org.example.PrimaryController;
import org.example.netty.common.dto.BasicResponse;
import org.example.netty.common.dto.GetFilesListResponse;
import org.example.netty.common.dto.UploadFileRequest;
import org.example.netty.common.dto.UploadFileResponse;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        BasicResponse response = (BasicResponse) msg;
        PrimaryController instance = ObjectRegistry.getInstance(PrimaryController.class);
        if (response instanceof GetFilesListResponse) {
            GetFilesListResponse getFilesListResponse = (GetFilesListResponse) response;
            String dirs = getFilesListResponse.getList()
                    .stream()
                    .map(line -> line + "\n")
                    .reduce(String::concat)
                    .orElse("null");

            Platform.runLater( () -> {
                instance.setLabelText(dirs);
            });

        } else if (response instanceof UploadFileResponse) {
            Platform.runLater( () -> {
                String message = response.getErrorMessage();
                instance.setLabelText(message);
            });
        } else {
            System.out.println(response.getErrorMessage());
        }
    }
}