package edu.calvin.cs262.climbinglogapp;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by cpd5 on 10/12/2015.
 * This is the page that will contain the profile of the user.
 */
public class Profile extends Activity {

    //onCreate() method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
    }

    protected void onPause() {
        super.onPause();
    }
}
