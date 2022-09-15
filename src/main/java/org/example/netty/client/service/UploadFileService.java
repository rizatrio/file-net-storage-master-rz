package org.example.netty.client.service;

import org.example.ObjectRegistry;
import org.example.netty.client.NettyClient;
import org.example.netty.common.FileSaw;
import org.example.netty.common.dto.UploadFileRequest;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.function.Consumer;

import static org.example.netty.client.NettyClient.TOKEN;

public class UploadFileService {
    public void uploadFile(String pathToFile) {
        Path filePath = Paths.get(pathToFile);
        NettyClient nettyClient = ObjectRegistry.getInstance(NettyClient.class);
        String fileName = filePath.getFileName().toString();
        UUID uuid = UUID.randomUUID();
        Consumer<byte[]> filePartConsumer = filePartBytes -> {
            UploadFileRequest uploadFileRequest = new UploadFileRequest(TOKEN,fileName, filePartBytes);
            nettyClient.uploadFile(uploadFileRequest);
        };

        FileSaw fileSaw = new FileSaw();
        fileSaw.saw(filePath, filePartConsumer);

    }
}
