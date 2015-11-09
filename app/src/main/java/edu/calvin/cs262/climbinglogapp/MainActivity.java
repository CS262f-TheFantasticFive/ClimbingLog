package edu.calvin.cs262.climbinglogapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends FragmentActivity {

    //onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //enable the action bar home button
        getActionBar().setHomeButtonEnabled(true);

    }

    //Creating the options menu on the Action Bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Action Bar method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            //handle the action bar home button press
            case android.R.id.home:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This will start up the logger should a user click the green button.
     */
    public void startLog(View view) {
        Intent logIntent = new Intent(MainActivity.this, ClimbLogger.class);
        MainActivity.this.startActivity(logIntent);
    }

}
