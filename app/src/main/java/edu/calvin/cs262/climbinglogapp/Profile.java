package edu.calvin.cs262.climbinglogapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

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
 * Created by cpd67, Fall 2015
 * This is the page that will contain the profile of the user and will display a short blurb about
 * the user in addition the recent activity (the user's climbs)
 */
public class Profile extends BaseActivity {

    ListView profile_routes_List;
    ArrayAdapter<String> adapter;

    //onCreate() method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        ImageButton disableProfile = (ImageButton) findViewById(R.id.profile_button);  //Disable the corresponding Profile button
        disableProfile.setEnabled(false);  //To keep people from creating the Profile activity over and over again

        TextView bio_display = (TextView) findViewById(R.id.bio_display);

        profile_routes_List = (ListView) findViewById(R.id.routes_profile_display);
        /**
         * This method checks for presses outside of the keyboard, and if there are, hides the keyboard
         */
        bio_display.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        //get the data
        new LongRunningGetIO().execute();
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
                AlertDialog alertDialog = new AlertDialog.Builder(Profile.this).create();
                alertDialog.setTitle(getString(R.string.action_help));
                alertDialog.setMessage(getString(R.string.profile_label));
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

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private static String PROFILE_CLIMBS_URI = "http://10.0.2.2:9998/climbingserver/climbs/recent/0";

    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {
        String result;

        /**
         * This method extracts text from the HTTP response entity.
         *
         * @param entity
         * @return
         * @throws IllegalStateException
         * @throws IOException
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
         *
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(Void... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpContext localContext = new BasicHttpContext();
            HttpGet httpGet = new HttpGet(PROFILE_CLIMBS_URI);
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
         *
         * @param results
         */
        protected void onPostExecute(String results) {
            if (results != null) {
                result = results;
                String[] data = result.split(";");
                adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.simple_list_item_1, android.R.id.text1, data);
                profile_routes_List.setAdapter(adapter);
            }
        }

    }
    //onPause method
    protected void onPause() {
        super.onPause();
    }
}
