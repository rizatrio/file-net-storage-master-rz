package org.example.netty.common.handler;

import io.netty.channel.ChannelHandlerContext;
import org.example.netty.common.dto.GetFilesListRequest;
import org.example.netty.common.dto.GetFilesListResponse;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class GetFilesListHandler implements RequestHandler<GetFilesListRequest, GetFilesListResponse> {
    @Override
    public GetFilesListResponse handle(GetFilesListRequest request, ChannelHandlerContext ctx) {
        String getFilesListRequestpath = request.getPath();
        Path path = Paths.get(getFilesListRequestpath);
        String[] list = path.toFile().list();
        return new GetFilesListResponse("OK", list != null ? List.of(list) : Collections.emptyList());
    }
}
