package com.netty;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

@Component
@Sharable
public class DiscardServerHandler extends ChannelHandlerAdapter {
	@Resource
	private BaseServer baseServer;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try {
			ByteBuf in = (ByteBuf)msg;
			System.out.println("传输内容是");
			System.out.println(in.toString(CharsetUtil.UTF_8));
			//这里调用service服务
			baseServer.test();
		} finally {
			ReferenceCountUtil.release(msg);
		}
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
}
