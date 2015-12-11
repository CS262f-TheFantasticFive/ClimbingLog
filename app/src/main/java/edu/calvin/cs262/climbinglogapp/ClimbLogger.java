package edu.calvin.cs262.climbinglogapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chris on 11/2/2015.
 * This is the page that the user will see when the click on the big green button.
 */
public class ClimbLogger extends BaseActivity {

    EditText routeField, notesField;
    ExpandableListAdapter listAdapter;
    ExpandableListView listView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    String[] valueArray; //array to be sent to the database
    List<String> difficulty, type, color;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logger);

        //set the edittexts for later usage
        routeField = (EditText) findViewById(R.id.routeNameField);
        notesField = (EditText) findViewById(R.id.notesField);
        routeField.setBackgroundColor(getResources().getColor(R.color.ivory));
        notesField.setBackgroundColor(getResources().getColor(R.color.ivory));


        valueArray = new String[5]; //four values: type, route name, difficulty, color, and notes

        // get the listview
        listView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting difficulty list adapter
        listView.setAdapter(listAdapter);

        // Listview on child click listener
        listView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                //If the route name is entered
                if (groupPosition == 0) {    //else if the type field is selected
                        //add value to array
                        valueArray[1] = parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
                        //display the selected value
                        //Toast.makeText(getApplicationContext(), valueArray[1] + " added!", Toast.LENGTH_SHORT).show();
                        //collapse the group after a selection is made
                        listView.collapseGroup(groupPosition);

                        if (childPosition == 0) {
                            addTopRopeDiffData();
                        } else if (childPosition == 1) {
                            addBoulderDiffData();
                        }

                    listDataHeader.remove(0);
                    listDataHeader.add(0, "Type" + " - " + valueArray[1]);
                    listDataChild.put(listDataHeader.get(0), type); // Header, Child data


                } else if (groupPosition == 1) {    //else if the difficulty field is selected
                    //add value to array
                    valueArray[2] = parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
                    //display the selected value
                    //Toast.makeText(getApplicationContext(), valueArray[2] + " added!", Toast.LENGTH_SHORT).show();
                    //collapse the group after a selection is made
                    listView.collapseGroup(groupPosition);

                    if(!(valueArray[2].equals("Please enter a difficulty first."))) {
                        //this replaces the group header by:
                        listDataHeader.remove(1);//removing the old header
                        listDataHeader.add(1, "Difficulty" + " - " + valueArray[2]); //adding the selected data
                        listDataChild.put(listDataHeader.get(1), difficulty); //putting the new header and child data back into the list
                    }


                } else if (groupPosition == 2) {    //else if the color field is selected
                    //add value to array
                    valueArray[3] = parent.getExpandableListAdapter().getChild(groupPosition, childPosition).toString();
                    //display the selected value
                    //Toast.makeText(getApplicationContext(), valueArray[3] + " added!", Toast.LENGTH_SHORT).show();
                    //collapse the group after a selection is made
                    listView.collapseGroup(groupPosition);

                    //this replaces the group header by:
                    listDataHeader.remove(2); //removing the old header
                    listDataHeader.add(2, "Color" + " - " + valueArray[3]); //adding the selected data
                    listDataChild.put(listDataHeader.get(2), color); //putting the new header and child data back into the list
                }
                return false;
            }
        });


        /**
         * These two methods check for presses outside of the keyboard, and if there are, hide the keyboard
         */
        routeField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        notesField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }


    /*
     * Preparing the list data
     */

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding header data
        listDataHeader.add("Type");
        listDataHeader.add("Difficulty");
        listDataHeader.add("Color");

        // The types of climbs
        type = new ArrayList<String>();
        type.add("Top Rope");
        type.add("Boulder");

        difficulty = new ArrayList<String>();
        // Adding difficulty data
        difficulty.add(getString(R.string.default_diff));

        //adding color data
        color = new ArrayList<String>();
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

        listDataChild.put(listDataHeader.get(0), type); // Header, Child data
        listDataChild.put(listDataHeader.get(1), difficulty); // Header, Child data
        listDataChild.put(listDataHeader.get(2), color); // Header, Child data

    }



    // this gets called if type top rope gets selected
    public void addTopRopeDiffData() {
        //clear whatever is in the difficulty list
        difficulty.clear();
        //add the new data
        difficulty.add("5.0");
        difficulty.add("5.1");
        difficulty.add("5.2");
        difficulty.add("5.3");
        difficulty.add("5.4");
        difficulty.add("5.5");
        difficulty.add("5.6");
        difficulty.add("5.7");
        difficulty.add("5.8");
        difficulty.add("5.8+");
        difficulty.add("5.9");
        difficulty.add("5.9+");
        difficulty.add("5.10");
        difficulty.add("5.10a");
        difficulty.add("5.10b");
        difficulty.add("5.10c");
        difficulty.add("5.10d");
        difficulty.add("5.11");
        difficulty.add("5.11a");
        difficulty.add("5.11b");
        difficulty.add("5.11c");
        difficulty.add("5.11d");
        difficulty.add("5.12");
        difficulty.add("5.12a");
        difficulty.add("5.12b");
        difficulty.add("5.12c");
        difficulty.add("5.12d");
        difficulty.add("5.13");
        difficulty.add("5.13a");
        difficulty.add("5.13b");
        difficulty.add("5.13c");
        difficulty.add("5.13d");
        difficulty.add("5.14");
        difficulty.add("5.14a");
        difficulty.add("5.14b");
        difficulty.add("5.14c");
        difficulty.add("5.14d");
        difficulty.add("5.15");
        difficulty.add("5.15a");
        difficulty.add("5.15b");

        listDataChild.put(listDataHeader.get(1), difficulty); // Header, Child data
    }

    // this gets called if type boulder gets selected
    public void addBoulderDiffData() {
        //clear whatever is in the difficulty list
        difficulty.clear();
        //add the new data
        difficulty.add("VB");
        difficulty.add("V0-");
        difficulty.add("V0");
        difficulty.add("V0+");
        for(int i = 1; i < 17; i++){
            difficulty.add("V" + i);
        }

        listDataChild.put(listDataHeader.get(1), difficulty); // Header, Child data
    }

    //this method handles the submit button and sends the app back to the home page
    public void submit(View view) {

        //if the edittexts have something other than the default value or empty, then add them to the array
        if (!routeField.getText().toString().equals("Route Name") && routeField.getText().toString().trim().length() != 0) {
            valueArray[0] = routeField.getText().toString();
        }
        if (!notesField.getText().toString().equals("Notes") && notesField.getText().toString().trim().length() != 0) {
            valueArray[4] = notesField.getText().toString();
        }
        // if the default value for difficulty was selected, make it null
        if (valueArray[2].toString().equals(getString(R.string.default_diff))) {
            valueArray[2] = null;
        }

        //handle database stuff here
        //post valueArray
        new LongRunningGetIO().execute();
        //send the app back to the main activity
        Intent mainIntent = new Intent(ClimbLogger.this, MainActivity.class);
        ClimbLogger.this.startActivity(mainIntent);
        //some sort of submitted popup
        Toast.makeText(getApplicationContext(), valueArray[0] + valueArray[1] + valueArray[2] + valueArray[3] + valueArray[4] + " added!", Toast.LENGTH_SHORT).show();
    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /*
    @Override
    public void onPause() {
        super.onPause();
        AlertDialog.Builder leaveDialogBuilder = new AlertDialog.Builder(ClimbLogger.this);
        leaveDialogBuilder.setMessage("Some data has been entered. Are you sure you want to leave?");
        leaveDialogBuilder.setPositiveButton("Leave Anyway", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        leaveDialogBuilder.setNegativeButton("Keep Entering Data", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                onResume();
            }
        });

        AlertDialog leaveDialog = leaveDialogBuilder.create();
        leaveDialog.show();
    }
    */

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


    private static String NEW_CLIMBS_URI = "http://10.0.2.2:9998/climbingserver/climb";


    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        /**
         * This method issues the HTTP GET request.
         *
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();  //Create the HTTP Client
            HttpContext localContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost(NEW_CLIMBS_URI);  //Create the POST

            try {
                //0 = name, 1 = type, 2 = difficulty, 3 = color, 4 = notes
                //routeName color difficulty types notes
                //Get the data from the user
                String input = valueArray[0] + ":" + valueArray[3] + ":" + valueArray[2] + ":" + valueArray[1] + ":" + valueArray[4];
                StringEntity data = new StringEntity(input);  //Create a StringEntity object to hold the input data

                //Set the content type
                data.setContentType("text/plain");

                //Set the entity of the POST method
                httpPost.setEntity(data);

                // Execute HTTP Post Request
                httpClient.execute(httpPost, localContext);

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
            return "YAY";
        }

    }


}