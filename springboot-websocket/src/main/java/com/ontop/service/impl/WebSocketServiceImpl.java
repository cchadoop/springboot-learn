package com.ontop.service.impl;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;

/*
 * WebSocket：HTML5 开始提供的一种在单个 TCP 连接上进行全双工通讯的协议
 * WebSocket 使得客户端和服务器之间的数据交换变得更加简单，允许服务端主动向客户端推送数据
 * 在 WebSocket API 中，浏览器和服务器只需要完成一次握手，两者之间就直接可以创建持久性的连接，并进行双向数据传输
 */

//定义服务器站点
@ServerEndpoint(value = "/ws")
@Service
public class WebSocketServiceImpl {

	private static int onlineCount = 0;
	// concurrent Set 线程安全，用于存放客户端对应的WebSocketServiceImpl
	private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocket = new CopyOnWriteArraySet<>();
	// 与某个客户端的连接对话，需要通过它来给客户端发送数据
	private Session session;

	/*
	 * 连接建立时触发
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		webSocket.add(this);
		addOnlineCount();
		System.out.println("有新连接加入，当前在线人数为：" + getOnlineCount());
		try {
			sendMessage("有新的连接加入了！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 连接关闭时触发
	 */
	@OnClose
	public void onClose() {
		webSocket.remove(this);
		subOnlineCount();
		System.out.println("有一连接关闭！当前在线人数为：" + getOnlineCount());
	}

	/*
	 * 服务端接受客户端发送过来的数据触发
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws Exception {
		System.out.println("来自客户端的消息:" + message);
		for (WebSocketServiceImpl item : webSocket) {
			/*
			 * String username = item.session.getUserPrincipal().getName();
			 * System.out.println("当前用户名：" + username);
			 */
			item.sendMessage(message);
		}
	}

	/*
	 * 通信发生错误触发
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生异常");
		error.printStackTrace();
	}

	private static void subOnlineCount() {
		WebSocketServiceImpl.onlineCount--;
	}

	private void sendMessage(String message) throws Exception {
		this.session.getBasicRemote().sendText(message);
	}

	private static synchronized int getOnlineCount() {
		return WebSocketServiceImpl.onlineCount;
	}

	private static void addOnlineCount() {
		WebSocketServiceImpl.onlineCount++;
	}
}
