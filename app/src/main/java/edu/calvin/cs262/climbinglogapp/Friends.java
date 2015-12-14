/**
 * Friends.java contains the code necessary in order to create our Friends page.
 */
package edu.calvin.cs262.climbinglogapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by javinunger, Fall 2015
 * Modified by cpd67, Fall 2015
 * Friends page
 */
public class Friends extends BaseActivity {

    ListView friendsList;  //Displays the friends list
    ArrayAdapter<String> adapter; //the adapter for the ListView

    /**
     * onCreate() method sets up our Friends page and gets the friend data for the user.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);
        ImageButton disableFriends = (ImageButton) findViewById(R.id.friends_button);  //Disable the corresponding button
        disableFriends.setEnabled(false);  //To prevent people from creating the same activity over and over again
        friendsList = (ListView) findViewById(R.id.friends_view);  //Get the display

        //Get the data
        new LongRunningGetIO().execute();
    }

    //URI for the friends GET method
    private static String FRIENDS_URI = "http://10.0.2.2:9998/climbingserver/friends/0";

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
            StringBuffer out = new StringBuffer();
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
            HttpGet httpGet = new HttpGet(FRIENDS_URI);
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
                result = results;
                String[] data = result.split(";");
                adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.simple_list_item_1, android.R.id.text1, data);
                friendsList.setAdapter(adapter);
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
                AlertDialog alertDialog = new AlertDialog.Builder(Friends.this).create();
                alertDialog.setTitle(getString(R.string.action_help));
                alertDialog.setMessage(getString(R.string.action_help_friends));
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