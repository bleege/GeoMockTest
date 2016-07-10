package com.bradleege.geomocktest.presenters;

import com.bradleege.geomocktest.model.AppDataManager;
import com.bradleege.geomocktest.model.DataManager;
import com.bradleege.geomocktest.view.MainMVPView;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.geocoding.v5.models.GeocodingResponse;
import java.util.ArrayList;
import java.util.List;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainPresenter implements Presenter<MainMVPView> {

    private MainMVPView view;
    private DataManager dataManager;
    private ArrayList<String> geocodeLocations;
    private int geocodeIndex = 0;

    public MainPresenter() {
        super();
        init(null);
    }

    public MainPresenter(DataManager dataManager) {
        super();
        init(dataManager);
    }

    private void init(DataManager dataManager) {
        Timber.tag("MainPresenter");
        if (dataManager == null) {
            this.dataManager = new AppDataManager();
            Timber.i("AppDataManager set.");
        } else {
            this.dataManager = dataManager;
            Timber.i("Custom DataManager set: %s", this.dataManager.getClass().getSimpleName());
        }

        geocodeLocations = new ArrayList<>();
        geocodeLocations.add("1265 Lombardi Avenue");
        geocodeLocations.add("Seattle");
        geocodeLocations.add("Denver, Colorado");
    }

    @Override
    public void attachView(MainMVPView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public void onGeocodeButtonClick() {
        Timber.i("onGeocodeButtonClick");
        if (geocodeIndex == geocodeLocations.size()) {
            geocodeIndex = 0;
        }

        try {
            dataManager.geocode(geocodeLocations.get(geocodeIndex))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GeocodingResponse>() {
                @Override
                public void onCompleted() {
                    Timber.i("onCompleted()");
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e, "Error Subscribing to dataManager.geocode(): %s", e.getMessage());
                }

                @Override
                public void onNext(GeocodingResponse response) {
                    Timber.i("onNext() GeocodingResponse = %s", response);
                    if (response != null && response.getFeatures().size() > 0) {
                        List<Double> coords = response.getFeatures().get(0).getGeometry().getCoordinates();
                        view.displayGeocodeText("Latitude = " + coords.get(1) + ", Longitude = " + coords.get(0));
                    } else {
                        view.displayNoResults();
                    }
                }
            });
            geocodeIndex++;
        } catch (ServicesException e) {
            Timber.e(e, "Error while geocoding: %s", e.getMessage());
        }
    }
}
