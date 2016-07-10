package com.bradleege.geomocktest.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.bradleege.geomocktest.R;
import com.bradleege.geomocktest.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainMVPView {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainPresenter();
        presenter.attachView(this);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
