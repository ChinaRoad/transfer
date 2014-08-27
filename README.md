# Transfer - A Lightweight Net Library #

**Transfer** 对java nio进行封装，使得Socket开发高性能网络通信程序变得简单。


## 目录 ##

- [目录](#directory)
- [结构](#structure)
- [用法](#usage)
- [下载](#down)

## <a name="structure"></a>结构 ##
![](img/architecture.jpg)

## <a name="usage"></a>用法 ##
### <a name="usage-server"></a>创建一个TCP/UDP Server ###

	SocketAcceptor acceptor = new SocketAcceptor(host, port);		// TCP Server Acceptor
	DatagramAcceptor acceptor = new DatagramAcceptor(host, port);	// UDP Server Acceptor

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

### <a name="usage-client"></a>创建一个TCP/UDP Client ###

	SocketConnector connector = new SocketConnector();		// TCP Client Connector
	DatagramConnector connector = new DatagramConnector();	// UDP Client Connector

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

### <a name="usage-handler"></a>Handler的使用 ###
在transport中所有逻辑的处理都是基于事件，我们有如下事件：

事件|描述
---|---
sessionCreated|连接对象被创建，物理链路未连接。 
sessionOpened|连接对象被创建，物理链路已连接。 
sessionIdle|连接读/写空闲。
sessionClosed|物理连接已断开。
exceptionCaught|异常被捕获，在这里处理所有其他事件中未处理的异常以及底层异常。
dataReceived|收到网络另一端发来数据，数据是Filter处理后的数据。
dataNotSent|数据发送失败，当数据仅部分发送或未发送触发。
dataSent|数据发送成功，待发送数据已全部写入网卡。
