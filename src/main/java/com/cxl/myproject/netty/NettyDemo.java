package com.cxl.myproject.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @author cxl
 * @date 2023/3/27 & 17:46
 */
public class NettyDemo {


    public static void main(String[] args) {

        //创建main reactor
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建工作线程组
        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap
                .group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childOption(ChannelOption.TCP_NODELAY,true)
                //配置出入站事件handler
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //配置出入站channel
                        ch.pipeline().addLast(new ServerChannelHandlerDemo());
                        ch.pipeline().addLast(new ServerChannelHandlerDemo2());
                    }
                });
        //绑定端口
        int port = 8080;
        serverBootstrap.bind(port).addListener(future -> {
           if(future.isSuccess()){
               System.out.println(new Date()+": 端口["+port+"]绑定成功!");
           }else{
               System.out.println("端口["+port+"]绑定失败!");
           }
        });


    }

}
