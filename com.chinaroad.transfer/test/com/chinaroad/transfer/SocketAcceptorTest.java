package com.chinaroad.transfer;

import org.junit.Test;

import com.chinaroad.transfer.SocketAcceptor;
import com.chinaroad.transfer.handler.DemoServerHandler;

public class SocketAcceptorTest {
	
	private String host = "0.0.0.0";
	private int port = 80;
	
	@Test
	public void test() throws Exception {
		SocketAcceptor acceptor = new SocketAcceptor(host, port);
		
		// sometimes, you should init before...
		acceptor.init();
		
		// Custom setting...
		acceptor.setSelectTimeout(10000);
		acceptor.setSendBufferSize(1024);
		acceptor.setReceiveBufferSize(1024);
		
		// Inject the biz handler.
		acceptor.setHandler(new DemoServerHandler());
		
		// Start service
		// Thread will be blocked in here.
		acceptor.start();
	}

}
