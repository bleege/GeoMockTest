package com.bradleege.geomocktest.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.bradleege.geomocktest.R;
import com.bradleege.geomocktest.presenters.MainPresenter;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements MainMVPView {

    private MainPresenter presenter;

    private TextView geocodeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Timber.plant(new Timber.DebugTree());
        Timber.tag("MainActivity");

        presenter = new MainPresenter();
        presenter.attachView(this);

        setContentView(R.layout.activity_main);

        Button geocodeButton = (Button) findViewById(R.id.geocodeButton);
        geocodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onGeocodeButtonClick();
            }
        });

        geocodeTextView = (TextView) findViewById(R.id.geocodeResultTextView);
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

    @Override
    public void displayGeocodeText(String text) {
        Timber.d("displayGeocodeText() called with text = ''%s'", text);
        geocodeTextView.setText(text);
    }
}
