package edu.calvin.cs262.climbinglogapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by jbu2 on 11/4/2015.
 * Gyms page
 */
public class Gyms extends FragmentActivity {

    //onCreate() method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyms);
    }

    protected void onPause() {
        super.onPause();
    }
}
