package com.bradleege.geomocktest.presenters;

import com.bradleege.geomocktest.model.AppDataManager;
import com.bradleege.geomocktest.model.DataManager;
import com.bradleege.geomocktest.view.MainMVPView;
import com.mapbox.services.commons.ServicesException;
import java.util.ArrayList;
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
        } else {
            this.dataManager = dataManager;
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
        if (geocodeIndex == geocodeLocations.size() - 1) {
            geocodeIndex = 0;
        }

        try {
            dataManager.geocode(geocodeLocations.get(geocodeIndex));
        } catch (ServicesException e) {
            Timber.e(e, "Error while geocoding: %s", e.getMessage());
        }
    }
}
