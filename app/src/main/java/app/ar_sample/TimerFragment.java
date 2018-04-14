package app.ar_sample;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.TimerTask;

public class TimerFragment extends Fragment implements TimerMan.Callback {

    private TextView mTimerTextView;
    private TimerMan mTimerMan;
    private MyCountDownTimer mTimer1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        //mTimerMan = new TimerMan(this);
        mTimer1 = new MyCountDownTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        mTimerTextView = (TextView) view.findViewById(R.id.tv_timer);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onTimerTick(long seconds) {
        mTimerTextView.setText("Осталось: " + seconds);
    }

    @Override
    public void onTimerFinish() {
        mTimerTextView.setText("onFinish!");
    }

    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer() {
            super(40000, 500);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTimerTextView.setText("Осталось111: " + millisUntilFinished / 1000);
        }

        @Override
        public void onFinish() {
            mTimerTextView.setText("onFinish!1111111");
        }
    }
}
