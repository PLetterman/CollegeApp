package org.pltw.examples.collegeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wdumas on 2/18/2015.
 */
public class GuardianFragment extends Fragment implements FamilyMemberFragment{

    private static final String TAG = "GuardianFragment";
    private final String FILENAME = "guardian.json";
    private static final String DIALOG_DATE = "date";
    private static final int REQUEST_DOB = 0;
    private static final String KEY_FIRST_NAME = "firstname";

    private Guardian mGuardian;
    private TextView mFirstName;
    private TextView mLastName;
    private EditText mEnterFirstName;
    private EditText mEnterLastName;
    private TextView mOccupation;
    private EditText mEnterOccupation;
    private Context mAppContext;
    private TextView mAge;
    private Button mDoBButton;
    GuardianJSONStorer mStorer;

    public GuardianFragment() {
        /*Attempts to load information from the Profile. If information cannot be found
        catch the exception, create a new Profile, and print out the error code*/
        try {
            mGuardian = mStorer.load();
        } catch (Exception e) {
            mGuardian = new Guardian();
            Log.e(TAG, "Error loading Guardian: " + FILENAME, e);
        }
    }

    //Example comment for GitHub

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGuardian = new Guardian();

        if (savedInstanceState != null) {
            mGuardian.setFirstName(savedInstanceState.getString(KEY_FIRST_NAME));
            Log.i(TAG, "The name is " + mGuardian.getFirstName());
        }
    }

    private void updateDoB() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int a = year - 1900 - mGuardian.mDateOfBirth.getYear();
        mDoBButton.setText(mGuardian.dobToString());
        if ((mGuardian.mDateOfBirth.getMonth() > month) && (mGuardian.mDateOfBirth.getDay() >= day)) {
            mAge.setText("Age: " + a);
        } else {
            mAge.setText("Age: " + (a - 1));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DOB) {
            Date dob = (Date)data
                    .getSerializableExtra(DoBPickerFragment.EXTRA_DOB);
            mGuardian.setmDateOfBirth(dob);
            updateDoB();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_guardian, container, false);

        mGuardian = (Guardian)Family.get().getFamily().get(getActivity().
                getIntent().getIntExtra(FamilyMember.EXTRA_INDEX,0));

        mFirstName = (TextView)rootView.findViewById(R.id.first_name);


        mGuardian = new Guardian();

        mFirstName = (TextView)rootView.findViewById(R.id.first_name);
        mEnterFirstName = (EditText)rootView.findViewById(R.id.enter_first_name);
        mLastName = (TextView)rootView.findViewById(R.id.last_name);
        mEnterLastName = (EditText)rootView.findViewById(R.id.enter_last_name);
        mOccupation = (TextView)rootView.findViewById(R.id.occupation);
        mEnterOccupation = (EditText)rootView.findViewById(R.id.enter_occupation);
        mDoBButton = (Button)rootView.findViewById(R.id.dob_button);
        mAge = (TextView)rootView.findViewById(R.id.age);

        mFirstName.setText(mGuardian.getFirstName());
        mLastName.setText(mGuardian.getLastName());
        mOccupation.setText(mGuardian.getOccupation());


        FirstNameTextChanger firstNameTextChanger = new FirstNameTextChanger();
        LastNameTextChanger lastNameTextChanger = new LastNameTextChanger();
        OccupationTextChanger occupationTextChanger = new OccupationTextChanger();
        DoBButtonOnClickListener doBButtonOnClickListener = new DoBButtonOnClickListener();

        mEnterFirstName.addTextChangedListener(firstNameTextChanger);
        mEnterLastName.addTextChangedListener(lastNameTextChanger);
        mEnterOccupation.addTextChangedListener(occupationTextChanger);
        mDoBButton.setOnClickListener(doBButtonOnClickListener);

        mAppContext = this.getActivity();
        Log.d(TAG, "Context: " + mAppContext);
        mStorer = new GuardianJSONStorer(mAppContext, FILENAME);

        return rootView;
    }

    private class DoBButtonOnClickListener implements View.OnClickListener {
        public void onClick(View v) {
            FragmentManager fm = getActivity()
                    .getSupportFragmentManager();
            DoBPickerFragment dialog = DoBPickerFragment
                    .newInstance(mGuardian.getmDateOfBirth());
            dialog.setTargetFragment(GuardianFragment.this, REQUEST_DOB);
            dialog.show(fm, DIALOG_DATE);
        }
    }

    private class FirstNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mGuardian.setFirstName(s.toString());
            ArrayList<FamilyMember> family = Family.get().getFamily();
            int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX,0);
            family.set(index, (FamilyMember) mGuardian);
        }

        @Override
        public void afterTextChanged(Editable s) {
            mFirstName.setText(mGuardian.getFirstName());
        }



    }

    private class LastNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mGuardian.setLastName(s.toString());
            ArrayList<FamilyMember> family = Family.get().getFamily();
            int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX,0);
            family.set(index, (FamilyMember)mGuardian);
        }


        @Override
        public void afterTextChanged(Editable s) {
            mLastName.setText(mGuardian.getLastName());
        }
    }

    private class OccupationTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mGuardian.setOccupation(s.toString());
            ArrayList<FamilyMember> family = Family.get().getFamily();
            int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX,0);
            family.set(index, (FamilyMember)mGuardian);
        }

        @Override
        public void afterTextChanged(Editable s) {
            mOccupation.setText(mGuardian.getOccupation());
        }
    }

    public boolean saveGuardian() {
        try {
            mStorer.save(mGuardian);
            Log.d(TAG, "Guardian saved to file.");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving Guardian: ", e);
            return false;
        }
    }

    public boolean loadGuardian() {
        try {
            mGuardian = mStorer.load();
            Log.d(TAG, "Loaded " + mGuardian.getFirstName());
            mFirstName.setText(mGuardian.getFirstName());
            mLastName.setText(mGuardian.getLastName());
            updateDoB();
            return true;
        } catch (Exception e) {
            mGuardian = new Guardian();
            Log.e(TAG, "Error loading Guardian from: " + FILENAME, e);
            return false;
        }
    }

    @Override
    public void onStart() {
        /* Logs that the fragment has been started upon creation */
        super.onStart();
        Log.d(TAG, "Fragment started.");
    }

    @Override
    public void onPause() {
        /*Saves the information for Profile anytime the fragment is paused*/
        super.onPause();
        saveGuardian();
        Log.d(TAG, "Fragment paused.");
    }

    @Override
    public void onResume() {
        /*When the information is stored, upon resume, load the information and catch
        the exception if the program cannot properly load the file*/
        super.onResume();
        try {
            mGuardian = mStorer.load();
            Log.d(TAG, "Loaded " + mGuardian.getFirstName());
            mFirstName.setText(mGuardian.getFirstName());
            mLastName.setText(mGuardian.getLastName());
        } catch (Exception e) {
            mGuardian = new Guardian();
            Log.e(TAG, "Error loading Guardian from: " + FILENAME, e);
        }

        Log.d(TAG, "Fragment resumed.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Fragment stopped.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Fragment destroyed.");
    }



}
