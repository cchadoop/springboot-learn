package com.netty;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

@Component
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

	@Resource
	private DiscardServerHandler discardServerHandler;
	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline().addLast(discardServerHandler);
	}

}
