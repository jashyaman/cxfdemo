package com.keblal.cxfdemo.services;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.keblal.cxfdemo.annotations.RestService;
import com.keblal.cxfdemo.models.Place;

@RestService
@Path("places")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class PlaceResource {
	
	@Autowired
	PlaceService placeService;
	
	@GET
	@Path("/filterByCountry/{countryname}")
	public List<Place> testService(@PathParam("countryname") String name) {
		
		
		try {
			return placeService.filterPlaceByCountry(name);
		} catch (UnsupportedOperationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
