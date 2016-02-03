package org.pltw.examples.collegeapp;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by PLTW on 1/7/2016.
 */
public class Sibling extends FamilyMember implements ApplicantData {

    public Sibling() {
        super();
        setFirstName("Bob");
        setLastName("Ross");
        mDateOfBirth = new Date(83, 0, 24);
        setRelation(SIBLING);
    }

    public Sibling(JSONObject json) throws JSONException {
        mFirstName = json.getString(JSON_F_FIRST_NAME);
        mLastName = json.getString(JSON_F_LAST_NAME);
        mDateOfBirth = new Date(json.getLong(JSON_F_DOB));
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_F_FIRST_NAME, mFirstName);
        json.put(JSON_F_LAST_NAME, mLastName);
        json.put(JSON_F_DOB, mDateOfBirth.getTime());
        return json;
    }

    public Sibling(String firstName, String lastName, Date date) {
        super();
        setFirstName(firstName);
        setLastName(lastName);
        setmDateOfBirth(new Date(83, 0, 24));
    }

    public  String toString() {
        return "Sibling: " + mFirstName + " " + mLastName;
    }

    @Override
    public int compareTo(FamilyMember another) {
        if (mFirstName.equals(another.mFirstName) && mLastName.equals(another.mLastName)) {
            return 0;
        } else {
            return 1;
        }
    }
}
