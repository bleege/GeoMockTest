package com.bradleege.geomocktest.presenters;

import com.bradleege.geomocktest.model.DataManager;
import com.bradleege.geomocktest.model.TestDataManager;
import com.bradleege.geomocktest.util.RxSchedulersOverrideRule;
import com.bradleege.geomocktest.view.MainMVPView;
import com.mapbox.services.commons.ServicesException;
import com.mapbox.services.geocoding.v5.models.GeocodingResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rx.Observable;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock private MainMVPView mockMainMVPView;
    @Mock private DataManager mockDataManager;
    private MainPresenter mainPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mainPresenter = new MainPresenter(mockDataManager);
        mainPresenter.attachView(mockMainMVPView);
    }

    @After
    public void tearDown() {
        mainPresenter.detachView();
        mainPresenter = null;
    }

    @Test
    public void onGeocodeButtonClickNoResultsTest() throws ServicesException {
        GeocodingResponse testResponse = TestDataManager.makeEmptyGeocodingResponse();
        when(mockDataManager.geocode("1265 Lombardi Avenue")).thenReturn(Observable.just(testResponse));

        mainPresenter.onGeocodeButtonClick();
        verify(mockMainMVPView, never()).displayGeocodeText("XXX");
        verify(mockMainMVPView).displayNoResults();
    }

    @Test
    public void onGeocodeButtonClickTest() throws ServicesException {
        GeocodingResponse testResponse = TestDataManager.makeGeocodingResponseWithSingleFeature();
        when(mockDataManager.geocode("1265 Lombardi Avenue")).thenReturn(Observable.just(testResponse));

        mainPresenter.onGeocodeButtonClick();
        verify(mockMainMVPView, never()).displayNoResults();
        verify(mockMainMVPView).displayGeocodeText("Latitude = 44.501213, Longitude = -88.061874");
    }

}
