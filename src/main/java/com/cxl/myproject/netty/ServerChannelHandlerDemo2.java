package com.cxl.myproject.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author cxl
 * @date 2023/3/29 & 10:27
 */
public class ServerChannelHandlerDemo2 implements ChannelHandler {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler demo 2 handlerAdd");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler demo 2 removed");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("handler demo 2 caught");
    }
}
