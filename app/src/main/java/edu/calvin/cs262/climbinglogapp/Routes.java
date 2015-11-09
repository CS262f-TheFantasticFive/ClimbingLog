package edu.calvin.cs262.climbinglogapp;

import android.os.Bundle;
import android.widget.Button;

/**
 * Created by jbu2 on 11/4/2015.
 * Routes page
 */
public class Routes extends BaseActivity {

    //onCreate() method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routes);
        Button disableRoutes = (Button) findViewById(R.id.routes_button);  //Disable the corresponding button
        disableRoutes.setEnabled(false);  //To keep people from creating the same activity over and over again
    }

    //onPause() method
    protected void onPause() {
        super.onPause();
    }
}
