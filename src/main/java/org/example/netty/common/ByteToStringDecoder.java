package org.example.netty.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ByteToStringDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        while (byteBuf.isReadable()) {
            stringBuilder.append((char) byteBuf.readByte());
        }
        list.add(stringBuilder.toString());
    }
}
