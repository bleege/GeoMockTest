package com.bradleege.geomocktest.model;

import android.support.annotation.NonNull;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.geocoding.v5.models.GeocodingResponse;

import rx.Observable;

public interface DataManager {
    Observable<GeocodingResponse> geocode(@NonNull String search) throws ServicesException;
}
