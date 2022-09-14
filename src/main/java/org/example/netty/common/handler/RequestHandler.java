package org.example.netty.common.handler;

import org.example.netty.common.dto.BasicRequest;
import org.example.netty.common.dto.BasicResponse;

public interface RequestHandler<REQUEST extends BasicRequest, RESPONSE extends BasicResponse> {
    RESPONSE handle(REQUEST request);
}
