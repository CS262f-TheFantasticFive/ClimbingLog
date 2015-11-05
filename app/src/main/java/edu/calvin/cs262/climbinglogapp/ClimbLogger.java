package edu.calvin.cs262.climbinglogapp;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Chris on 11/2/2015.
 * This is the page that the user will see when the click on the big green button.
 */
public class ClimbLogger extends FragmentActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logger);
    }


}
