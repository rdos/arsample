package app.ar_sample;

import android.os.CountDownTimer;

class TimerMan extends CountDownTimer {

    private final Callback mCallback;

    public TimerMan(Callback callback) {
        super(30000, 333);
        mCallback = callback;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mCallback.onTimerTick(millisUntilFinished / 1000);
    }

    @Override
    public void onFinish() {
        mCallback.onTimerFinish();
    }

    public interface Callback {
        void onTimerTick(long seconds);
        void onTimerFinish();
    }
}
