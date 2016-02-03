package org.pltw.examples.collegeapp;



import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by wdumas on 12/22/2014.
 */
public class Profile implements ApplicantData {

    private static final String JSON_FIRST_NAME = "firstName";
    private static final String JSON_LAST_NAME = "lastName";
    private static final String JSON_DOB = "dob";
    private static final String JSON_CAREER_BOX = "careerBox";
    private String mFirstName;
    private String mLastName;
    private Date mDateOfBirth;
    private String mCareerBox;

    public Profile(JSONObject json) throws JSONException {
        mFirstName = json.getString(JSON_FIRST_NAME);
        mDateOfBirth = new Date(json.getLong(JSON_DOB));
        mLastName = json.getString(JSON_LAST_NAME);
        mCareerBox = json.getString(JSON_CAREER_BOX);
    }

    public String getFirstName() {
        return mFirstName;
    }
    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }
    public String getLastName() {
        return mLastName;
    }
    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }
    public Date getDateOfBirth() {
        return mDateOfBirth;
    }
    public String getCareerBox() {
        return mCareerBox;
    }
    public void setCareerBox(String mCareerBox) {
        this.mCareerBox = mCareerBox;
    }

    public String dobToString() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return df.format(mDateOfBirth.getTime());
    }

    public void setmDateOfBirth(Date mDateOfBirth) {
        this.mDateOfBirth = mDateOfBirth;
    }

    public Profile() {
        mFirstName = new String("Wyatt");
        mLastName = new String("Dumas");
        mDateOfBirth = new Date(83, 0, 24);
        mCareerBox = new String("Goals");
    }

    public String toString() {
        return mFirstName + " " + mLastName + " " + mDateOfBirth.getTime() + " " + mCareerBox;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_FIRST_NAME, mFirstName);
        json.put(JSON_LAST_NAME, mLastName);
        json.put(JSON_DOB, mDateOfBirth.getTime());
        json.put(JSON_CAREER_BOX, mCareerBox);
        System.out.println("Date of Birth Saved: " + mDateOfBirth);
        return json;
    }



}
