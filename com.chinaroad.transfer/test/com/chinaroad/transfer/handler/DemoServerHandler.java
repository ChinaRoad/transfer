package com.chinaroad.transfer.handler;

import com.chinaroad.transfer.handler.Handler;
import com.chinaroad.transfer.session.IdleStatus;
import com.chinaroad.transfer.session.Session;

public class DemoServerHandler implements Handler {
	
	@Override
	public void sessionCreated(Session session) throws Exception {
		System.out.println("Created:" + session);
	}
	
	@Override
	public void sessionOpened(Session session) throws Exception {
		System.out.println("Opened:" + session);
		//session.setIdleTime(IdleStatus.READ_IDLE, 5000);
		//session.setIdleTime(IdleStatus.WRITE_IDLE, 10);
	}

	@Override
	public void sessionIdle(Session session, IdleStatus status)
			throws Exception {
		session.close();
	    //System.out.println("Idle:" + status + ", " + System.currentTimeMillis());
	}

	@Override
	public void sessionClosed(Session session) throws Exception {
		System.out.println("Closed:" + session);
	}
	
	@Override
	public void exceptionCaught(Session session, Throwable cause) {
		cause.printStackTrace();
	}

	@Override
	public void dataReceived(Session session, Object data) throws Exception {
		System.out.println("Received:" + new String((byte[]) data));
		//session.send(data);
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