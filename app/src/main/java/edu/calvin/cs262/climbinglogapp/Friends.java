package edu.calvin.cs262.climbinglogapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by jbu2 on 11/4/2015.
 * Friends page
 */
public class Friends extends BaseActivity {

    //onCreate() method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);
        //ImageButton disableFriends = (ImageButton) findViewById(R.id.friends_button);  //Disable the corresponding button
        //disableFriends.setEnabled(false);  //To prevent people from creating the same activity over and over again
    }

    //onPause() method
    protected void onPause() {
        super.onPause();
    }
}
