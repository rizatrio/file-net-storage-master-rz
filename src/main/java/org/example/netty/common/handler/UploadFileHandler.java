package org.example.netty.common.handler;

import io.netty.channel.ChannelHandlerContext;
import org.example.netty.common.dto.UploadFileRequest;
import org.example.netty.common.dto.UploadFileResponse;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class UploadFileHandler implements RequestHandler<UploadFileRequest, UploadFileResponse> {

    private static final String SERVER_PATH = "C:\\Users\\Rizat.Orazalina\\Downloads\\file-net-storage-master\\file-net-storage-master\\server-dir\\";
    private static final Map<ChannelHandlerContext, RandomAccessFile> FILE_DESCRIPTOR_MAP = new HashMap<>();
     @Override
     public UploadFileResponse handle(UploadFileRequest request, ChannelHandlerContext ctx) {
         String fileName = request.getFileName();
         byte[] filePartData = request.getFilePartData();
         Path newFilePath = Paths.get(SERVER_PATH + fileName);
         File file = newFilePath.toFile();
         RandomAccessFile randomAccessFile = FILE_DESCRIPTOR_MAP.get(ctx);
         try {
             if (randomAccessFile == null) {
                 randomAccessFile = new RandomAccessFile(file, "rw");
                 FILE_DESCRIPTOR_MAP.put(ctx, randomAccessFile);
             }
             randomAccessFile.write(filePartData);
         } catch (IOException e) {
             return new UploadFileResponse("Не удалось сохранить файл на сервере.");
         }
         return new UploadFileResponse("OK");
    }
}
