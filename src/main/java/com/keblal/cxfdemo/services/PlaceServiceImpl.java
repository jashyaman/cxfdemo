package com.keblal.cxfdemo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.keblal.cxfdemo.models.Place;

@Service
public class PlaceServiceImpl implements PlaceService {

	@Override
	public List<Place> filterPlaceByCountry(String name) {

		// below code is trash. need to refactor.
		
		String restApi = "https://raw.githubusercontent.com/rohankoid/api-country-state-city/master/Countries-States-Cities-database-master/countries.sql";
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(restApi);
		CloseableHttpResponse response1 = null;
		try {
			response1 = httpclient.execute(httpGet);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
		    System.out.println(response1.getStatusLine());
		    HttpEntity entity1 = response1.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
				response1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return processResponse(name);
	}

	private List<Place> processResponse(String string) {
		List<Place> placeList = new ArrayList<Place>();
		placeList.add(new Place("Chennai", "India"));
		return placeList;
	}

}
