package com.videoplaza.thrifty;

import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServlet;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;

/**
 * Created by IntelliJ IDEA.
 * User: fredrik
 * Date: 2012-03-30
 * Time: 11:14
 */
public class ThriftyJsonServer {

	public static ThriftyServiceImpl serviceImpl;
	public static ThriftyService.Processor processor;

	public static void main(String[] args) {
		try {
			serviceImpl = new ThriftyServiceImpl();
			processor = new ThriftyService.Processor(serviceImpl);
			TJSONProtocol.Factory protocolFactory = new TJSONProtocol.Factory();
			HttpServlet servlet = new TServlet(processor, protocolFactory);
			Server server = new Server(8080);
			Context root = new Context(server,"/", Context.NO_SESSIONS);
			root.addServlet(new ServletHolder(servlet), "/*");
			System.out.println("Starting jetty on port 8080.");
			server.start();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
}


