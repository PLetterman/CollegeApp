package org.pltw.examples.collegeapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ApplicantActivity extends ActionBarActivity {

    private static final String TAG = "ApplicantActivity";

    private String[] mApplicantViewTitles = { "Profile", "Family" }; //Do this with String resources
    private DrawerLayout mDrawerLayout;
    private NavActionBarDrawerToggle mDrawerToggle;
    private DrawerListItemClickListener mDrawerListItemClickListener;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicant);

        mApplicantViewTitles = mApplicantViewTitles; //meaningless without string resources
        mTitle = mDrawerTitle = getTitle();
        if (mTitle == null) mTitle = mDrawerTitle = "TestTitle";
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerToggle = new NavActionBarDrawerToggle(this);
        mDrawerListItemClickListener = new DrawerListItemClickListener();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList = (ListView)findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mApplicantViewTitles));

        mDrawerList.setOnItemClickListener(mDrawerListItemClickListener);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new ProfileFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

    private void selectItem(int position) {

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        switch (position) {
            case ApplicantData.PROFILE:
                fragment = new ProfileFragment();
                fm.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .commit();

                // Highlight the selected item, and close the drawer
                mDrawerList.setItemChecked(position, true);
                break;
            case ApplicantData.FAMILY:
                fragment = new FamilyListFragment();
                fm.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        .commit();

                // Highlight the selected item, and close the drawer
                mDrawerList.setItemChecked(position, true);
                break;
            default:
                break;
        }

        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_applicant, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        //menu.findItem(R.id.action_settings).setVisible(!drawerOpen); //example
        return super.onPrepareOptionsMenu(menu);
    }

    private class NavActionBarDrawerToggle extends ActionBarDrawerToggle {

        public NavActionBarDrawerToggle(ActionBarActivity actionBarActivity) {
            super(actionBarActivity, mDrawerLayout,
                    R.string.drawer_open, R.string.drawer_close);
        }

        /** Called when a drawer has settled in a completely closed state. */
        @Override
        public void onDrawerClosed(View view) {
            super.onDrawerClosed(view);
            //getActionBar().setTitle(mTitle);
            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
        }

        /** Called when a drawer has settled in a completely open state. */
        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            //getActionBar().setTitle(mDrawerTitle);
            invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
        }
    }

    private class DrawerListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "Activity started.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "Activity paused.");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Activity resumed.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Activity stopped.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Activity started.");
    }

}
