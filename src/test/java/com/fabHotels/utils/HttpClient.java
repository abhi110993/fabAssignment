package com.fabHotels.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class HttpClient {

	public static String getHttpResponse(String resourceURL) {
		Client client = Client.create();
		WebResource webResource = client.resource(resourceURL);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		return response.getEntity(String.class);
	}
}
