package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.example.netty.client.NettyClient;
import org.example.netty.client.service.UploadFileService;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class PrimaryController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private void switchToSecondary() {
        NettyClient nettyClient = ObjectRegistry.getInstance(NettyClient.class);
        nettyClient.getFilesList();
    }

    public void uploadFile() {
        UploadFileService uploadFileService = ObjectRegistry.getInstance(UploadFileService.class);
        uploadFileService.uploadFile("C:\\Users\\Rizat.Orazalina\\Downloads\\file-net-storage-master\\file-net-storage-master\\client-dir\\lenta.png");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObjectRegistry.reg(this.getClass(), this);
    }

    public void setLabelText(String text) {
        label.setText(text);
    }
}
