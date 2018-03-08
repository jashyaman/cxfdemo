package com.keblal.cxfdemo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

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
import com.keblal.cxfdemo.models.Country;
import com.keblal.cxfdemo.models.CountryElement;
import com.keblal.cxfdemo.models.Place;
import com.keblal.cxfdemo.models.State;
import com.keblal.cxfdemo.models.StateElement;

@Service
public class PlaceServiceImpl implements PlaceService {
	
	static Logger log = Logger.getLogger("PlaceServiceImpl");

	@Override
	public Place filterPlaceByCountry(String countryName) throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException {

		String countryRestApi = "https://raw.githubusercontent.com/jashyaman/cxfdemo/master/countries.json";
		HttpEntity countryEntity = getApiResponse(countryRestApi);
		Country country = new ObjectMapper().readValue(countryEntity.getContent(), Country.class);
		EntityUtils.consume(countryEntity);

		/* fetch required state JSON Data */
		String restApi = "https://raw.githubusercontent.com/jashyaman/cxfdemo/master/states.json";
		State state = null;
		HttpEntity entity = getApiResponse(restApi);
	    state = new ObjectMapper().readValue(entity.getContent(), State.class);
		EntityUtils.consume(entity);
		
		
		String countryIdTemp = null;    
		for(CountryElement element : country.getCountries()) {
			if(element.getName().equals(countryName)) {
			countryIdTemp = element.getId();
			log.info("-----------------------------------------------------------country id : " + element.getName());
			break;
			}
		}
		log.info("-----------------------------------------------------------country id : " + countryIdTemp);
		
		final String countryId = countryIdTemp;
		
		
		
		List<String> stateList = new ArrayList<>();
		for(StateElement stateElement : state.getStates()) {
			log.info("place : " + stateElement.getName() + " country id " + getCountryName(country,countryId) + " ");
			if(stateElement.getCountryId().equals(countryId)) {
				System.out.print("found place : " + stateElement.getName() + " country id " + 

				//country.getCountries().stream()
				//.filter(countryElement -> countryElement.getId().equals(countryId))
				//.collect(Collectors.toList())
				getCountryName(country,countryId)	
						+ " ");

				stateList.add(stateElement.getName());
			}
		}
		
		if(stateList.isEmpty()) {
			stateList.add("no data");
		}
		
		
		return new Place(getCountryName(country,countryId), stateList);
		    
		   
		
	}
	
	

	private String getCountryName(Country country, String countryId) {
		if (countryId == null) {
			return "country not recognized";
		}
		for(CountryElement ce : country.getCountries()) {
			if(ce.getId().equals(countryId)) {
				log.info("country returned is : " + ce.getName());
				return ce.getName();
			}
		}
		return "no country found for id " + countryId + " ";
		
	}



	private HttpEntity getApiResponse(String restApi) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(restApi);
		CloseableHttpResponse response1 = null;
			response1 = httpclient.execute(httpGet);
		
		    log.info(response1.getStatusLine()+"");
		    HttpEntity entity = response1.getEntity();
		    return entity;
	}

	

}
