package org.example.netty.common.dto;

public class GetFilesListRequest extends BasicRequest {

    private final String path;

    public GetFilesListRequest(String authToken, String path) {
        super(authToken);
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
