package com.cxl.myproject.netty;

import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.SocketAddress;

/**
 * @author cxl
 * @date 2023/3/29 & 10:27
 */
public class ServerChannelHandlerDemo implements ChannelHandler {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler demo 1 handlerAdd");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler demo 1 removed");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("handler demo 1 caught");
    }
}
