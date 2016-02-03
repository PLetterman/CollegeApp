package org.pltw.examples.collegeapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by PLTW on 12/14/2015.
 */
public class Family {

    private static final String TAG = Family.class.getName();
    ArrayList<FamilyMember> mFamily;
    private static Family sFamily;

    public ArrayList<FamilyMember> getFamily() {
        return mFamily;
    }

    public void setFamily(ArrayList<FamilyMember> mFamily) {
        this.mFamily = mFamily;
    }

    public static Family get() {
        if (sFamily == null) {
            try {
                sFamily = new Family();
            }
            catch (NullPointerException e) {
                Log.e(TAG, "Error loading Family");
            }
        }
        return sFamily;
    }

    public Family() {
        mFamily = new ArrayList<FamilyMember>();
        mFamily.add(new Guardian());
        mFamily.add(new Sibling());
    }

    public void addFamilyMember(FamilyMember familyMember) {
        mFamily.add(familyMember);
    }

    public void deleteFamilyMember(FamilyMember familyMember) {
        mFamily.remove(familyMember);
    }
}
