package com.ontop;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootNettyApplicationTests {

	@Test
	public void contextLoads() {
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

}
