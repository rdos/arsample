package app.ar_sample;

import android.os.CountDownTimer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


final class TimerMan {

    private final Callback mCallback;

    private CntDownTimer mCntDownTimer;
    private long mCurrentSecs = 0;
    private long mAddSecsCount = 0;

    private Calendar mCalendar;
    private SimpleDateFormat mSimpleDateFormat;

    public TimerMan(Callback callback, long seconds) {
        super();
        mCallback = callback;
        initSeconds(seconds);
    }

    public void initSeconds(long seconds) {
        addSeconds(seconds, false);
    }

    public void addSeconds(long seconds) {
        addSeconds(seconds, true);
    }

    private void addSeconds(long seconds, boolean isAdditionSeconds) {
        if ((mCurrentSecs + seconds) * 1000 <= 0) {
            return;
        }
        if (isAdditionSeconds)  {
            mAddSecsCount += 1;
        }
        mCurrentSecs += seconds;
        stopTimer();
        startNewTimer(mCurrentSecs);
    }

    public String getTimerStrFormat() {
        //TODO: может нужно было бы сделать тут проще? ;)
        getCalendar().setTimeInMillis(mCurrentSecs * 1000);
        return getSimpleDateFormat().format(getCalendar().getTime());
    }

    private Calendar getCalendar() {
        if (mCalendar == null) {
            mCalendar = Calendar.getInstance();
//            mCalendar.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        return mCalendar;
    }

    private SimpleDateFormat getSimpleDateFormat() {
        if (mSimpleDateFormat == null) {
            mSimpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
            mSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        return mSimpleDateFormat;
    }

    private void startNewTimer(long seconds) {
        mCntDownTimer = new CntDownTimer(seconds);
        mCntDownTimer.start();
    }

    private void stopTimer() {
        if (mCntDownTimer == null) {
            return;
        }
        mCntDownTimer.cancel();
    }

    public long getAddSecsCount() {
        return mAddSecsCount;
    }


    private class CntDownTimer extends CountDownTimer {

        private CntDownTimer(long secs) {
            super(secs * 1000, 1000);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mCurrentSecs = millisUntilFinished / 1000;
            //TODO: onTimerTick только после изменений??
            mCallback.onTimerTick();
        }

        @Override
        public void onFinish() {
            mCurrentSecs = 0;
            mCallback.onTimerTick();
            mCallback.onTimerFinish();
        }
    }

    public interface Callback {
        void onTimerTick();
        void onTimerFinish();
    }
}
