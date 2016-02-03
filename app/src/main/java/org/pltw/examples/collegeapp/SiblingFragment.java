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
import java.util.Date;

/**
 * Created by PLTW on 1/20/2016.
 */
public class SiblingFragment extends Fragment implements FamilyMemberFragment{

    private static final String TAG = "SiblingFragment";
    private final String FILENAME = "sibling.json";
    private static final int REQUEST_DOB = 0;
    private static final String DIALOG_DATE = "date";

    private Sibling mSibling;
    private TextView mFirstName;
    private TextView mLastName;
    private EditText mEnterFirstName;
    private EditText mEnterLastName;
    private Button mDoBButton;
    private TextView mAge;
    private Context mAppContext;
    private static final String KEY_FIRST_NAME = "firstname";

    private void updateDoB() {
        mDoBButton.setText(mSibling.dobToString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DOB) {
            Date dob = (Date)data
                    .getSerializableExtra(DoBPickerFragment.EXTRA_DOB);
            mSibling.setmDateOfBirth(dob);
            updateDoB();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_sibling, container, false);

        mSibling = (Sibling)Family.get().getFamily().get(getActivity().
                getIntent().getIntExtra(FamilyMember.EXTRA_INDEX,0));

        mFirstName = (TextView)rootView.findViewById(R.id.first_name);


        mSibling = new Sibling();

        mFirstName = (TextView)rootView.findViewById(R.id.first_name);
        mEnterFirstName = (EditText)rootView.findViewById(R.id.enter_first_name);
        mLastName = (TextView)rootView.findViewById(R.id.last_name);
        mEnterLastName = (EditText)rootView.findViewById(R.id.enter_last_name);
        mDoBButton = (Button)rootView.findViewById(R.id.dob_button);

        mFirstName.setText(mSibling.getFirstName());
        mLastName.setText(mSibling.getLastName());

        FirstNameTextChanger firstNameTextChanger = new FirstNameTextChanger();
        LastNameTextChanger lastNameTextChanger = new LastNameTextChanger();
        DoBButtonOnClickListener doBButtonOnClickListener = new DoBButtonOnClickListener();

        mEnterFirstName.addTextChangedListener(firstNameTextChanger);
        mEnterLastName.addTextChangedListener(lastNameTextChanger);
        mDoBButton.setOnClickListener(doBButtonOnClickListener);
        mAppContext = this.getActivity();
        Log.d(TAG, "Context: " + mAppContext);
        /*mStorer = new GuardianJSONStorer(mAppContext, FILENAME);*/

        return rootView;
    }

    private class DoBButtonOnClickListener implements View.OnClickListener {
        public void onClick(View v) {
            FragmentManager fm = getActivity()
                    .getSupportFragmentManager();
            DoBPickerFragment dialog = DoBPickerFragment
                    .newInstance(mSibling.getmDateOfBirth());
            dialog.setTargetFragment(SiblingFragment.this, REQUEST_DOB);
            dialog.show(fm, DIALOG_DATE);
        }
    }

    private class FirstNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mSibling.setFirstName(s.toString());
            ArrayList<FamilyMember> family = Family.get().getFamily();
            int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX,0);
            family.set(index, (FamilyMember) mSibling);
        }

        @Override
        public void afterTextChanged(Editable s) {
            mFirstName.setText(mSibling.getFirstName());
        }



    }

    private class LastNameTextChanger implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mSibling.setLastName(s.toString());
            ArrayList<FamilyMember> family = Family.get().getFamily();
            int index = getActivity().getIntent().getIntExtra(FamilyMember.EXTRA_INDEX,0);
            family.set(index, (FamilyMember)mSibling);
        }


        @Override
        public void afterTextChanged(Editable s) {
            mLastName.setText(mSibling.getLastName());
        }
    }

    /*public boolean saveGuardian() {
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
            return true;
        } catch (Exception e) {
            mGuardian = new Guardian();
            Log.e(TAG, "Error loading Guardian from: " + FILENAME, e);
            return false;
        }
    }*/

    @Override
    public void onStart() {
        /* Logs that the fragment has been started upon creation */
        super.onStart();
        Log.d(TAG, "Fragment started.");
    }

    /*@Override
    public void onPause() {
        /*Saves the information for Profile anytime the fragment is paused
        super.onPause();
        saveGuardian();
        Log.d(TAG, "Fragment paused.");
    }*/

    /*@Override
    public void onResume() {
        /*When the information is stored, upon resume, load the information and catch
        the exception if the program cannot properly load the file
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
    }*/

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
