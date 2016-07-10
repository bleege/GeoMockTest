package com.bradleege.geomocktest.model;


import com.mapbox.services.geocoding.v5.models.FeatureGeometry;
import com.mapbox.services.geocoding.v5.models.GeocodingFeature;
import com.mapbox.services.geocoding.v5.models.GeocodingResponse;
import java.util.Arrays;

public class TestDataManager {

    public static GeocodingResponse makeEmptyGeocodingResponse() {
        return new GeocodingResponse();
    }

    public static GeocodingResponse makeGeocodingResponseWithSingleFeature() {

        GeocodingFeature gf = new GeocodingFeature();
        gf.setId("poi.12374802425618070");
        gf.setType("Feature");
        gf.setText("Lambeau Field");
        gf.setPlaceName("Lambeau Field, 1265 Lombardi Ave, Green Bay, Wisconsin 54304, United States");
        gf.setCenter(new double[]{-88.061874, 44.501213});
        FeatureGeometry fg = new FeatureGeometry();
        fg.setType("Point");
        fg.setCoordinates(Arrays.asList(-88.061874, 44.501213));
        gf.setGeometry(fg);
        Arrays.asList(gf);

        GeocodingResponse gr = new GeocodingResponse();
        gr.setFeatures(Arrays.asList(gf));

        return gr;
    }
}
