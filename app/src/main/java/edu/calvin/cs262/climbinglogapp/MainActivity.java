/**
 * MainActivity.java contains all of the code necessary in order to create our home page.
 */
package edu.calvin.cs262.climbinglogapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends BaseActivity {

    /**
     * onCreate() method sets up our home page.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method overrides the onOptionsItemSelected method in order to handle action bar things
     * specific to this page (eg the help dialog).
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_help:
                //simple dialog when the help setting is selected
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(getString(R.string.action_help));
                alertDialog.setMessage(getString(R.string.action_help_main));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
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