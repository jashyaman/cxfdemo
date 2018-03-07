package com.keblal.cxfdemo.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.keblal.cxfdemo.annotations.RestService;

@RestService
@Path("cxf")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class RestServices {

	@GET
	@Path("/path/{name}")
	public String testService(@PathParam("name") String name) {
		
		return "Hello " + name;
	}
}
