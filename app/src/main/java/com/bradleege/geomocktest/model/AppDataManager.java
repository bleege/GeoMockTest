package com.bradleege.geomocktest.model;

import android.support.annotation.NonNull;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.geocoding.v5.MapboxGeocoding;
import com.mapbox.services.geocoding.v5.models.GeocodingResponse;
import rx.Observable;
import timber.log.Timber;

public class AppDataManager implements DataManager {

    public AppDataManager() {
        super();
        Timber.tag("AppDataManager");
    }

    @Override
    public Observable<GeocodingResponse> geocode(@NonNull String search) throws ServicesException {
        Timber.i("geocode: search = '%s'", search);
        MapboxGeocoding client = new MapboxGeocoding.Builder()
                .setAccessToken("pk.eyJ1IjoiYmxlZWdlIiwiYSI6ImNpcWdyMW05OTA0M2dmdG5wNmUwNW9uZnIifQ.2pkBZdtZe_VWUVYpOpR3aw")
                .setLocation(search)
                .build();

        return client.getObservable();
    }

}
