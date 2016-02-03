package org.pltw.examples.collegeapp;

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by PLTW on 12/4/2015.
 */
public class GuardianJSONStorer extends JSONStorer {

    private static final String TAG = "GuardianJSONStorer";

    public GuardianJSONStorer(Context c, String f) {
        super(c, f);
    }

    public void save(ApplicantData applicantData) throws JSONException, IOException {
        if (applicantData instanceof Guardian) {
            Guardian guardian = (Guardian)applicantData;
            Writer writer = null;
            try {
                Log.d(TAG, "Profile in JSON: " + guardian.toJSON().toString() + " saved to: " +
                        mFilename);
                OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
                writer = new OutputStreamWriter(out);
                writer.write(guardian.toJSON().toString());
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
    }

    public Guardian load() throws IOException, JSONException {
        BufferedReader reader = null;
        Guardian guardian = null;
        try {
            Log.d(TAG, "Opening an input stream from: " + mFilename + " with Context:" +
                    mContext);
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ( (line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            guardian = new Guardian(new JSONObject(jsonString.toString()));
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Error loading Profile from: " + mFilename, e);
            guardian = new Guardian();
        } finally {
            if (reader != null)
                reader.close();
        }
        return guardian;
    }

    @Override
    public JSONObject toJSON() throws JSONException {
        return null;
    }
}
