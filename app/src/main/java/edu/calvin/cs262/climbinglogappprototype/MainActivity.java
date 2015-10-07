package edu.calvin.cs262.climbinglogappprototype;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Taken from the tutorial on "Adding the Action Bar"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:  //If the action search button is pressed, call the search method
                openSearch();
                return true;
            case R.id.action_settings:  //Else, call the settings method
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //From the lab exercises
    //Search method
    private void openSearch(){
        startActivity(new Intent(SearchManager.INTENT_ACTION_GLOBAL_SEARCH));
    }

    //Settings method
    private void openSettings(){
        startActivity(new Intent(Settings.ACTION_SETTINGS));
    }
}
