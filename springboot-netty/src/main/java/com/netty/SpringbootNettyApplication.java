package com.netty;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringbootNettyApplication implements CommandLineRunner{
	
	@Resource
	private DiscardServer discardServer;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNettyApplication.class, args);
		try {
			Socket socket = new Socket("localhost", 8888);
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream);
			printWriter.write("$tmb00035ET3318/08/22 11:5804029.94,027.25,20.00,20.00$");
			printWriter.flush();
			socket.shutdownOutput();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(String... args)throws Exception{
		discardServer.run(8888);
	}

}
