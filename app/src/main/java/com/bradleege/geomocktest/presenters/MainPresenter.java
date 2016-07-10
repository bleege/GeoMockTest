package com.bradleege.geomocktest.presenters;

import com.bradleege.geomocktest.view.MainMVPView;

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
}
