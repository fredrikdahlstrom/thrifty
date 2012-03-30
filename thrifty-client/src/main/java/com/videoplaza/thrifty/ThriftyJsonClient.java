package com.videoplaza.thrifty;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;

/**
 * Created by IntelliJ IDEA.
 * User: fredrik
 * Date: 2012-03-30
 * Time: 11:34
 */
public class ThriftyJsonClient {

	public static void main(String[] args) {
		try {
			TTransport transport;
			transport = new THttpClient("http://localhost:8080", new DefaultHttpClient());
			transport.open();
			TProtocol protocol = new TJSONProtocol(transport);
			ThriftyService.Client client = new ThriftyService.Client(protocol);

			doRequest(client);

			transport.close();
		} catch (TException x) {
			x.printStackTrace();
		}
	}

	private static void doRequest(ThriftyService.Client client) throws TException {
		System.out.println("Service name: " + client.getName());
		System.out.println("Service version: " + client.getVersion());
		System.out.println("Service status: " + client.getStatus().name());

		try {
			System.out.println("whatIsHappening: " + client.whatIsHappening(new BigRequest("foobar")));
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		}
	}
}
