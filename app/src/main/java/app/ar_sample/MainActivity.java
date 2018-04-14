package app.ar_sample;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_TIMER_FRAGMENT = TimerFragment.class.getSimpleName();
    private TimerFragment mTimerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find the retained fragment on activity restarts
        FragmentManager fm = getSupportFragmentManager();
        mTimerFragment = (TimerFragment) fm.findFragmentByTag(TAG_TIMER_FRAGMENT);

        // create the fragment and data the first time
        if (mTimerFragment == null) {
            // add the fragment
            mTimerFragment = new TimerFragment();
            fm.beginTransaction().add(R.id.fragment_container, mTimerFragment, TAG_TIMER_FRAGMENT).commit();
            // load data from a data source or perform any calculation
//            mRetainedFragment.setData(loadMyData());
        }

    }

}
