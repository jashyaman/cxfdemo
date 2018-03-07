package com.keblal.cxfdemo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keblal.cxfdemo.models.Place;
import com.keblal.cxfdemo.models.State;
import com.kebsal.cxfdemo.models.Country;
import com.kebsal.cxfdemo.models.CountryElement;

@Service
public class PlaceServiceImpl implements PlaceService {

	@Override
	public List<Place> filterPlaceByCountry(String name) throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {

		// below code is trash. need to refactor.
		
		String restApi = "https://raw.githubusercontent.com/jashyaman/cxfdemo/master/states.json";
		State state = null;
		
		String countryRestApi = "https://raw.githubusercontent.com/jashyaman/cxfdemo/master/countries.json";
		
		HttpEntity countryEntity = getApiResponse(countryRestApi);
		Country country = new ObjectMapper().readValue(countryEntity.getContent(), Country.class);
		EntityUtils.consume(countryEntity);

		HttpEntity entity = getApiResponse(restApi);
	    state = new ObjectMapper().readValue(entity.getContent(), State.class);
		EntityUtils.consume(entity);
		
		String countryId = null;
		    
		for(CountryElement element : country.getCountries()) {
			element.getName().equals(name);
			countryId = element.getId();
			break;
		}
		if (countryId == null) {
			System.out.println("country not found");
			return Arrays.asList(new Place());
		}
		
		List<Place> finalList = new ArrayList<>();
		for(Place place : state.getStates()) {
			if(place.getCountryId().equals(countryId)) {
				finalList.add(place);
			}
		}
		
		
		return finalList;
		    
		   
		
	}

	private HttpEntity getApiResponse(String restApi) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(restApi);
		CloseableHttpResponse response1 = null;
			response1 = httpclient.execute(httpGet);
		
		    System.out.println(response1.getStatusLine());
		    HttpEntity entity = response1.getEntity();
		    return entity;
	}

	

}
