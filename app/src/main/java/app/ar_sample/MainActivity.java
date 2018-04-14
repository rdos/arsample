package app.ar_sample;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_TIMER_FRAGMENT = TimerFragment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        TimerFragment timerFragment = (TimerFragment) fm.findFragmentByTag(TAG_TIMER_FRAGMENT);
        if (timerFragment == null) {
            timerFragment = new TimerFragment();
            fm.beginTransaction().add(R.id.fragment_container, timerFragment, TAG_TIMER_FRAGMENT).commit();
        }

    }

}
