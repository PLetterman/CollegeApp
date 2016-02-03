package org.pltw.examples.collegeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by wdumas on 12/23/2014.
 */
public class DoBPickerFragment extends DialogFragment {

    private static final String TAG = "DoBPickerFragment";
    private static final int WITHIN_8_YEARS = 2007;
    public static final String EXTRA_DOB =
            "org.pltw.examples.collegeapp.dob";

    private Date mDoB;

    public static DoBPickerFragment newInstance(Date dob) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_DOB, dob);

        DoBPickerFragment fragment = new DoBPickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    private void sendResult(int resultCode) {
        if (getTargetFragment() == null) return;
        try {
            if (mDoB.getYear() >= WITHIN_8_YEARS) {
                String message = NumberOutOfRangeException.joinMessageAndYear(
                    "Who are you, Michael Kearney?", mDoB.getYear() + 1900);
                throw new NumberOutOfRangeException(message);
            }
        } catch (NumberOutOfRangeException s) {
            Log.e(TAG, s.getMessage());
        }
        Intent i = new Intent();
        i.putExtra(EXTRA_DOB, mDoB);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, i);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DatePickerOnDateChangedListener mDatePickerOnDateChangedListener = new DatePickerOnDateChangedListener();
        mDoB = (Date)getArguments().getSerializable(EXTRA_DOB);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDoB);
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);

        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_dob, null);

        DatePicker DoBPicker = (DatePicker)v.findViewById(R.id.dialog_dob_dobPicker);
        DoBPicker.setCalendarViewShown(false);
        DoBPicker.init(year, monthOfYear, dayOfMonth, mDatePickerOnDateChangedListener);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.dob_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(Activity.RESULT_OK);
                            }
                        })
                .create();
    }

    private class DatePickerOnDateChangedListener implements DatePicker.OnDateChangedListener {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mDoB = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
            getArguments().putSerializable(EXTRA_DOB, mDoB);

        }

    }

}
