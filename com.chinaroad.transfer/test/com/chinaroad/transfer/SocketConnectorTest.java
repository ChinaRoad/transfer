package com.chinaroad.transfer;

import org.junit.Test;

import com.chinaroad.transfer.SocketConnector;
import com.chinaroad.transfer.handler.DemoClientHandler;

public class SocketConnectorTest {
	
	private String host = "192.168.16.231";
	private int port = 6008;

	@Test
	public void test() throws Exception {
		SocketConnector connector = new SocketConnector();
		
		// sometimes, you should init before...
		connector.init();
		
		// Custom setting...
		connector.setSelectTimeout(10000);
		connector.setReceiveBufferSize(1024);
		connector.setSendBufferSize(1024);
		
		// Inject the biz handler.
		connector.setHandler(new DemoClientHandler());

		// Open connection to host:port
		// Thread will be blocked in here.
		connector.open(host, port);
	}

}
