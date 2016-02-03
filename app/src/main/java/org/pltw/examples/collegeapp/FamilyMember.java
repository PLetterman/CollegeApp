package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by PLTW on 12/10/2015.
 */
public abstract class FamilyMember implements ApplicantData, Comparable<FamilyMember> {

    protected static final String EXTRA_RELATION = "org.pltw.examples.collegeapp.relation";
    protected static final String EXTRA_INDEX = "org.pltw.examples.collegeapp.index";

    public static final int GUARDIAN = 0;
    public static final int SIBLING = 1;

    public int getRelation() {
        return mRelation;
    }

    public void setRelation(int mRelation) {
        this.mRelation = mRelation;
    }

    private int mRelation;
    protected static final String JSON_F_FIRST_NAME = "firstName";
    protected static final String JSON_F_LAST_NAME = "lastName";
    protected static final String JSON_F_OCCUPATION = "occupation";
    protected static final String JSON_F_DOB = "dob";
    protected String mFirstName;
    protected String mLastName;
    protected String mOccupation;
    public Date mDateOfBirth;

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_F_FIRST_NAME, mFirstName);
        json.put(JSON_F_LAST_NAME, mLastName);
        json.put(JSON_F_OCCUPATION, mOccupation);
        json.put(JSON_F_DOB, mDateOfBirth.getTime());
        return json;
    }

    protected String getFirstName() {
        return mFirstName;
    }

    protected void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    protected String getLastName() {
        return mLastName;
    }

    protected void setLastName(String lastName) {
        mLastName = lastName;
    }

    protected String getOccupation() {
        return mOccupation;
    }

    protected void setOccupation(String occupation) {
        mOccupation = occupation;
    }

    public Date getmDateOfBirth() {
        return mDateOfBirth;
    }

    public void setmDateOfBirth(Date mDateOfBirth) {
        this.mDateOfBirth = mDateOfBirth;
    }

    public String dobToString() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return df.format(mDateOfBirth.getTime());
    }
}
