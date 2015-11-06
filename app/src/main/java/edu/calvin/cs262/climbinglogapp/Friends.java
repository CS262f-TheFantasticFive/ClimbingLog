package edu.calvin.cs262.climbinglogapp;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;

import java.nio.BufferUnderflowException;

/**
 * Created by jbu2 on 11/4/2015.
 * Friends page
 */
public class Friends extends FragmentActivity {

    //onCreate() method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);
        Button disableFriends = (Button) findViewById(R.id.friends_button);
        disableFriends.setEnabled(false);
    }

    protected void onPause() {
        super.onPause();
    }
}
