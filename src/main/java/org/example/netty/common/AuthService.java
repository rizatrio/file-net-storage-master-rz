package org.example.netty.common;

import static org.example.netty.client.NettyClient.TOKEN;

public class AuthService {
    public boolean auth(String token) {
        return TOKEN.equals(token);
    }
}
