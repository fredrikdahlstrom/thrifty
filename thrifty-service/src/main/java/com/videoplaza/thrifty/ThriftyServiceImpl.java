package com.videoplaza.thrifty;

import org.apache.thrift.TException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by IntelliJ IDEA.
 * User: fredrik
 * Date: 2012-03-30
 * Time: 11:17
 */
public class ThriftyServiceImpl implements ThriftyService.Iface{
	@Override
	public String getName() throws TException {
		return this.getClass().getCanonicalName();
	}

	@Override
	public String getVersion() throws TException {
		return "0.1.0-SNAPSHOT";
	}

	@Override
	public Status getStatus() throws TException {
		return Status.ALIVE;
	}

	@Override
	public BigResponse whatIsHappening(BigRequest request) throws InvalidRequestException, TException {
		String name = request.getName();
		if (name.equals("fred")) {
			throw new InvalidRequestException("Sweet music to my ears!");
		}

		BigResponse response = new BigResponse("hello");
		response.age = 66;
		response.language = "swahili";
		response.locationsByName = new HashMap<String, Location>();
		response.locationsByName.put("Stockholm", new Location(11.2, 222.3));
		response.locationsByName.put("London", new Location(666.6, 123.213));
		response.setOfStrings = new HashSet<String>();
		response.setOfStrings.add("one");
		response.setOfStrings.add("two");
		response.setOfStrings.add("three");
		response.listOfStrings = new ArrayList<String>();
		response.listOfStrings.addAll(Arrays.asList(new String[]{"fish", "bear", "stool", "toilet"}));
		System.out.println("Responding with: " + response);
		return response;
	}

}
