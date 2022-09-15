package org.example.netty.common.dto;

public class UploadFileRequest extends BasicRequest {

    private final String fileName;

    private final byte[] filePartData;

    public UploadFileRequest(String token, String fileName, byte[] filePartData) {
        super(token);
        this.fileName = fileName;
        this.filePartData = filePartData;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFilePartData() {
        return filePartData;
    }


}
