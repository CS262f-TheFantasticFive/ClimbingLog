/**
 * Routes.java contains all of the code necessary in order to create our Routes page.
 */
package edu.calvin.cs262.climbinglogapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.Activity;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Displays the user's climbing activity - all logged climbs
 */
public class Routes extends BaseActivity {
    ListView routesList; //the ListView for displaying the routes
    ArrayAdapter<String> adapter; //the adapter for the ListView

    /**
     * onCreate() sets up the android activity and gets the routes data.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routes);  //Set the layout for the page

        ImageButton disableRoutes = (ImageButton) findViewById(R.id.routes_button);  //Disable the corresponding button
        disableRoutes.setEnabled(false);  //To keep people from creating the same activity over and over again

        routesList = (ListView) findViewById(R.id.routesListView);  //Get the List View that will display the data

        //Get the data to put in that list view
        new LongRunningGetIO().execute();
    }

    //URI for the GET method for the climbs of a specific climber
    private static String CLIMBS_URI = "http://153.106.87.105:9998/climbingserver/climbs/climber/0";

    /**
     * LongRunningGetIO class contains the data necessary in order to do an IO task (GET, POST...).
     * Adapted from Lab09 code.
     */
    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {
        String result;

        /**
         * This method extracts text from the HTTP response entity.
         * Adapted from Lab09 code.
         */
        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
               InputStream in = entity.getContent();
               StringBuilder out = new StringBuilder();
               int n = 1;
              while (n > 0) {
                  byte[] b = new byte[4096];
                  n = in.read(b);
                  if (n > 0) out.append(new String(b, 0, n));
              }
            return out.toString();
        }

        /**
         * This method issues the HTTP GET request.
         * Adapted from Lab09 code.
         */
        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet(CLIMBS_URI);
            String text;
            try {
                HttpResponse response = httpClient.execute(httpGet, localContext);
                HttpEntity entity = response.getEntity();
                text = getASCIIContentFromEntity(entity);
            } catch (Exception e) {
                return e.getLocalizedMessage();
            }
            return text;
        }

        /**
         * The method takes the results of the request, when they arrive, and updates the interface.
         * Adapted from Lab09 code.
         * Credit goes to Team Raging Narwahls for figuring this out. (Code adapted from their app code).
         */
        protected void onPostExecute(String results) {
            if (results != null) {
                result = results;  //Store the results of the GET request.
                String[] data = result.split(";");  //Split the String up by semicolons (placed by the GET method)
                adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.simple_list_item_1, android.R.id.text1, data);
                routesList.setAdapter(adapter);  //Display the data in the List View
            }
        }
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
                AlertDialog alertDialog = new AlertDialog.Builder(Routes.this).create();
                alertDialog.setTitle(getString(R.string.action_help));
                alertDialog.setMessage(getString(R.string.action_help_routes));
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
     * onPause() method calls the parent onPause() method in order to pause the activity when not in scope.
     */
    protected void onPause() {
        super.onPause();
    }
}
