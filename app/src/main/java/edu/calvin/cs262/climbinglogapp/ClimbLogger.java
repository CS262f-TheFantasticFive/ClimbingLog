package edu.calvin.cs262.climbinglogapp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chris on 11/2/2015.
 * This is the page that the user will see when the click on the big green button.
 */
public class ClimbLogger extends BaseActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String[] valueArray; //array to be sent to the database


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logger);

        valueArray = new String[4]; //four values: route name, difficulty, color, and notes

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                //add value to array
                valueArray[1] = parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
                //display the selected value
                Toast.makeText(getApplicationContext(), valueArray[1], Toast.LENGTH_SHORT).show();
                //collapse the group after a selection is made
                expListView.collapseGroup(groupPosition);
                return false;
            }
        });

    }


    /*
         * Preparing the list data
         */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Difficulty");
        listDataHeader.add("Color");

        // Adding child data
        List<String> difficulty = new ArrayList<String>();
        difficulty.add("5.0");
        difficulty.add("5.1");
        difficulty.add("5.2");
        difficulty.add("5.3");
        difficulty.add("5.4");
        difficulty.add("5.5");
        difficulty.add("5.6");
        difficulty.add("5.7");
        difficulty.add("5.8");
        difficulty.add("5.9");
        difficulty.add("5.10a");
        difficulty.add("5.10b");
        difficulty.add("5.10c");
        difficulty.add("5.10d");
        difficulty.add("5.11a");
        difficulty.add("5.11b");
        difficulty.add("5.11c");
        difficulty.add("5.11d");
        difficulty.add("5.12a");
        difficulty.add("5.12b");
        difficulty.add("5.12c");
        difficulty.add("5.12d");
        difficulty.add("5.13a");
        difficulty.add("5.13b");
        difficulty.add("5.13c");
        difficulty.add("5.13d");
        difficulty.add("5.14a");
        difficulty.add("5.14b");
        difficulty.add("5.14c");
        difficulty.add("5.14d");
        difficulty.add("5.15a");
        difficulty.add("5.15b");


        List<String> color = new ArrayList<String>();
        color.add("Red");
        color.add("Orange");
        color.add("Yellow");
        color.add("Green");
        color.add("Neon Green");
        color.add("Cyan");
        color.add("Blue");
        color.add("Turquoise");
        color.add("Violet");
        color.add("Pink");
        color.add("Magenta");
        color.add("Brown");
        color.add("White");
        color.add("Black");


        listDataChild.put(listDataHeader.get(0), difficulty); // Header, Child data
        listDataChild.put(listDataHeader.get(1), color);
    }


    //adding the route name to the array
    public void addRouteName(View view){
        EditText routeNameText   = (EditText)findViewById(R.id.routeNameField);
        valueArray[0] = routeNameText.getText().toString();
        Toast.makeText(getApplicationContext(), valueArray[0], Toast.LENGTH_SHORT).show();
    }
    //adding the notes to the array
    public void addNotes(View view){
        EditText routeNameText   = (EditText)findViewById(R.id.notesField);
        valueArray[3] = routeNameText.getText().toString();
        Toast.makeText(getApplicationContext(), valueArray[3], Toast.LENGTH_SHORT).show();
    }


    //this method handles the submit button and sends the app back to the home page
    public void submit(View view) {
        //handle database stuff here
        //post valueArray

        //send the app back to the main activity
        Intent mainIntent = new Intent(ClimbLogger.this, MainActivity.class);
        ClimbLogger.this.startActivity(mainIntent);
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
                AlertDialog alertDialog = new AlertDialog.Builder(ClimbLogger.this).create();
                alertDialog.setTitle(getString(R.string.action_help));
                alertDialog.setMessage(getString(R.string.action_help_logger));
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

}