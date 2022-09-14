package org.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(45001));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("The server started on port 45001, waiting for a connection...");

        while (true) {
            selector.select(); //blocking только один
            for(SelectionKey event : selector.selectedKeys()) {
                if (event.isValid()) {
                    try {
                        if (event.isAcceptable()) { // новое соединение
                            SocketChannel socketChannel = serverSocketChannel.accept(); // не блокрующий
                            socketChannel.configureBlocking(false);
                            System.out.println("Connected " + socketChannel.getRemoteAddress());
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } else if (event.isReadable()) { // готов к чтению
                            SocketChannel socketChannel = (SocketChannel) event.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                            int readBytes = socketChannel.read(byteBuffer);
                            if (readBytes == -1) {
                                socketChannel.close();
                            } else {
                                System.out.println(new String(byteBuffer.array()));
                            }
                        }
                    } catch (IOException ex) {
                        System.out.println("Ошибка " + ex.getMessage());
                    }
                }
            }
            selector.selectedKeys().clear();
        }
    }
}
