package com.bradleege.geomocktest.view;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.bradleege.geomocktest.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import timber.log.Timber;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    public MainActivityTest() {
        super();
        Timber.tag("MainActivityTest");
    }

    @Test
    public void testSanity() {
        Timber.i("testSanity() started...");
        onView(withId(R.id.geocodeResultTextView)).check(matches(withText("Georesults Here!")));
        Timber.i("testSanity() finished.");
    }

}
