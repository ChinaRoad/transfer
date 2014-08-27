package com.chinaroad.transfer.handler;

import java.nio.ByteBuffer;

import com.chinaroad.transfer.handler.Handler;
import com.chinaroad.transfer.session.IdleStatus;
import com.chinaroad.transfer.session.Session;

public class DemoClientHandler implements Handler {
	
	@Override
	public void sessionCreated(Session session) throws Exception {
		System.out.println("Create:" + session);
		//session.setIdleTime(IdleStatus.READ_IDLE, 1);
		//session.setIdleTime(IdleStatus.WRITE_IDLE, 1);
	}
	
	@Override
	public void sessionOpened(Session session) throws Exception {
		System.out.println("Open:" + session);
		session.send("\n".getBytes());
		// session.send(ByteBuffer.wrap((System.currentTimeMillis() + "A").getBytes()).array());
	}

	@Override
	public void sessionIdle(Session session, IdleStatus status)
			throws Exception {
		System.out.println("Idle:" + status);
		session.send(ByteBuffer.wrap((System.currentTimeMillis() + "A").getBytes()).array());
		session.send(ByteBuffer.wrap((System.currentTimeMillis() + "B").getBytes()).array());
		session.send(ByteBuffer.wrap((System.currentTimeMillis() + "C").getBytes()).array());
	}

	@Override
	public void sessionClosed(Session session) throws Exception {
		System.out.println("Closed:" + session);
	}
	
	@Override
	public void exceptionCaught(Session session, Throwable cause) {
		System.out.println("Exception:" + cause);
		cause.printStackTrace();
	}

	@Override
	public void dataReceived(Session session, Object data) throws Exception {
	    String rec = new String((byte[]) data);
	    if (rec.contains(". HELO ")) {
	        session.send(". AppName Demo\n".getBytes());
	    }
		System.out.println("Received:" + rec);
	}

	@Override
	public void dataNotSent(Session session, Object data) throws Exception {
		System.out.println("NotSent:" + new String((byte[]) data));
	}

	@Override
	public void dataSent(Session session, Object data) throws Exception {
		System.out.println("Sent:" + new String((byte[]) data));
	}
}