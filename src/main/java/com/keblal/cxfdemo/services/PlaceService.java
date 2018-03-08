package com.keblal.cxfdemo.services;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.keblal.cxfdemo.models.Place;

@Service
public interface PlaceService {

	Place  filterPlaceByCountry(String name) throws JsonParseException, JsonMappingException, UnsupportedOperationException, IOException;

}
