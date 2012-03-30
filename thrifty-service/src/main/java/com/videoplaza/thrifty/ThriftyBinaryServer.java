package com.videoplaza.thrifty;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * Created by IntelliJ IDEA.
 * User: fredrik
 * Date: 2012-03-30
 * Time: 11:14
 */
public class ThriftyBinaryServer {
	public static ThriftyServiceImpl serviceImpl;
	public static ThriftyService.Processor processor;

	public static void main(String[] args) {
		try {
			serviceImpl = new ThriftyServiceImpl();
			processor = new ThriftyService.Processor(serviceImpl);

			Runnable binary = new Runnable() {
				public void run() {
					try {
						TServerTransport serverTransport = new TServerSocket(9090);
						final TServer binaryServer = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
						System.out.println("Starting the binary server on port 9090.");
						binaryServer.serve();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			new Thread(binary).start();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}


