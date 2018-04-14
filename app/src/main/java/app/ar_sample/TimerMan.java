package app.ar_sample;

import android.os.CountDownTimer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;


final class TimerMan {

    private static final String PATTERN_FORMAT_TIME = "HH:mm:ss.SSS";
    private static final long INTERVAL_ON_TICK__MILLIS = 111;

    private final Callback mCallback;
    private CntDownTimer mCntDownTimer;

    private long mCurrentMillis = 0;
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
        if ((mCurrentMillis + seconds * 1000) <= 0) {
            return;
        }
        if (isAdditionSeconds)  {
            mAddSecsCount += 1;
        }
        mCurrentMillis += seconds * 1000;
        stopTimer();
        startNewTimer(mCurrentMillis);
    }

    public long getAddSecsCount() {
        return mAddSecsCount;
    }

    public String getTimerStrFormat() {
        //TODO: да, может тут и нужно было сделать проще.? ;)
        getCalendar().setTimeInMillis(mCurrentMillis);
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
            mSimpleDateFormat = new SimpleDateFormat(PATTERN_FORMAT_TIME, Locale.getDefault());
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

    private class CntDownTimer extends CountDownTimer {

        private CntDownTimer(long millis) {
            super(millis, INTERVAL_ON_TICK__MILLIS);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mCurrentMillis = millisUntilFinished;
            //TODO: onTimerTick только после изменений??
            mCallback.onTimerTick();
        }

        @Override
        public void onFinish() {
            mCurrentMillis = 0;
            mCallback.onTimerTick();
            mCallback.onTimerFinish();
        }
    }

    public interface Callback {
        void onTimerTick();
        void onTimerFinish();
    }
}
