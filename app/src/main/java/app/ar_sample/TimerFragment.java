package app.ar_sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TimerFragment extends Fragment implements TimerMan.Callback, View.OnClickListener, DialogInterface.OnClickListener {

    private final long INIT_SECONDS = 20;
    private final long ADD_SECONDS = 5;

    private TextView mTimerTextView;
    private TextView mCountBtnPlusTextView;

    private TimerMan mTimerMan;
    private AlertDialog mAlertDialog;
    private boolean mIsShowAlertDialog = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mTimerMan = new TimerMan(this, INIT_SECONDS);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        
        mTimerTextView = (TextView) view.findViewById(R.id.text_timer);
        mCountBtnPlusTextView = (TextView) view.findViewById(R.id.text_count_btn_plus);
        Button btnPlusSeconds = (Button) view.findViewById(R.id.btn_plus_seconds);
        btnPlusSeconds.setText(getString(R.string.btn_plus_seconds, ADD_SECONDS));
        btnPlusSeconds.setOnClickListener(this);

        setFragmentData();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TODO: mAlertDialog and mIsShowAlertDialog in 2 method.?
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false)
                .setPositiveButton(getString(R.string.btn_plus_seconds, INIT_SECONDS), this)
                .setNegativeButton(R.string.btn_close, this);
        mAlertDialog = builder.create();
        if (mIsShowAlertDialog) {
            mAlertDialog.show();
        }
    }

    private void setFragmentData() {
        mTimerTextView.setText(mTimerMan.getTimerStrFormat());
        mCountBtnPlusTextView.setText(getString(R.string.text_count_btn_plus, mTimerMan.getAddSecsCount()));
    }

    @Override
    public void onTimerTick() {
        if (getActivity() == null) {
            return;
        }
        setFragmentData();
    }

    @Override
    public void onTimerFinish() {
        if (getActivity() == null) {
            return;
        }
        mAlertDialog.show();
        mIsShowAlertDialog = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_plus_seconds:
                mTimerMan.addSeconds(ADD_SECONDS);
                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        mIsShowAlertDialog = false;
        switch (which)
        {
            case DialogInterface.BUTTON_POSITIVE:
                mTimerMan.initSeconds(INIT_SECONDS);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                // No button clicked
                break;
        }
    }
}
