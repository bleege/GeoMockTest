package com.bradleege.geomocktest.presenters;

import com.bradleege.geomocktest.view.MainMVPView;
import timber.log.Timber;

public class MainPresenter implements Presenter<MainMVPView> {

    private MainMVPView view;

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
    }
}
