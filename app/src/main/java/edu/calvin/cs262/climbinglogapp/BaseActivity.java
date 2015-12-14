/**
 * BaseActivity.java contains the code necessary in order to have an action bar and NavBar fragment.
 */
package edu.calvin.cs262.climbinglogapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;

import java.lang.reflect.Field;

/**
 * Created by abs25, Fall 2015
 * This base activity will be extended by every other class that needs the action bar.
 */
public class BaseActivity extends FragmentActivity {

    /**
     * onCreate() method sets up the action bar for every page.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enable the action bar home button
        getActionBar().setHomeButtonEnabled(true);

        //Set the right action bar key to show up
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");

            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        }
        catch (Exception e) {
            // presumably, not relevant
        }
    }

    /**
     * onCreateOptionsMenu() method creates the options menu for the action bar.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * onOptionsItemSelected() handles presses on the action bar items as appropriate.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //handle the action bar home button press
            case android.R.id.home:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                return true;
            case R.id.action_help:
                //simple dialog when the help setting is selected
                AlertDialog alertDialog = new AlertDialog.Builder(BaseActivity.this).create();
                alertDialog.setTitle(getString(R.string.action_help));
                alertDialog.setMessage(getString(R.string.team_name));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                return true;
            case R.id.action_credits:
                //simple dialog when the help setting is selected
                AlertDialog creditDialog = new AlertDialog.Builder(BaseActivity.this).create();
                creditDialog.setTitle(getString(R.string.action_credits));
                creditDialog.setMessage(getString(R.string.credits));
                creditDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                creditDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
