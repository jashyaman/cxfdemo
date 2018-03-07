package com.keblal.cxfdemo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.keblal.cxfdemo.models.Place;

@Service
public interface PlaceService {

	List<Place>  filterPlaceByCountry(String name);

}
