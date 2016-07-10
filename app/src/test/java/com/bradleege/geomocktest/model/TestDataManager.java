package com.bradleege.geomocktest.model;


import com.mapbox.services.geocoding.v5.models.GeocodingResponse;

public class TestDataManager {

    public static GeocodingResponse makeGeocodingResponse() {
        return new GeocodingResponse();
    }

}
