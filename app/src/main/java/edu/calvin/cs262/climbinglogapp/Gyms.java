package edu.calvin.cs262.climbinglogapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;

/**
 * Created by jbu2 on 11/4/2015.
 * Gyms page
 */
public class Gyms extends FragmentActivity {

    //onCreate() method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyms);
        Button disableGyms = (Button) findViewById(R.id.gyms_button);  //Disable the corresponding button
        disableGyms.setEnabled(false);  //To keep people from creating the same activity over and over again
    }

    //onPause() method
    protected void onPause() {
        super.onPause();
    }
}
