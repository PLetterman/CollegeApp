package org.pltw.examples.collegeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by PLTW on 1/20/2016.
 */
public class FamilyMemberActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_member);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = null;// = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            if (getIntent().getIntExtra(FamilyMember.EXTRA_RELATION, 0) ==
                    FamilyMember.GUARDIAN) {
                fragment = new GuardianFragment();
                fm.beginTransaction()
                        .add(R.id.fragmentContainer, fragment)
                        .commit();
            }
            else if (getIntent().getIntExtra(FamilyMember.EXTRA_RELATION, 0) ==
                    FamilyMember.SIBLING) {
                fragment = new SiblingFragment();
                fm.beginTransaction()
                        .add(R.id.fragmentContainer, fragment)
                        .commit();
            }
        }
    }

}
