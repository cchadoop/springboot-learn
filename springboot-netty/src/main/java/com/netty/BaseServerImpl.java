package com.netty;

import org.springframework.stereotype.Service;

@Service
public class BaseServerImpl implements BaseServer{

	@Override
	public void test() {
		System.out.println("调用service服务");
	}

}
