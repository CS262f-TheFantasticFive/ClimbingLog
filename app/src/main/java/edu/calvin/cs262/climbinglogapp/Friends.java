package edu.calvin.cs262.climbinglogapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
 * Created by jbu2 on 11/4/2015.
 * Friends page
 */
public class Friends extends BaseActivity {

    ListView friendsList;
    ArrayAdapter<String> adapter;

    //onCreate() method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);
        //ImageButton disableFriends = (ImageButton) findViewById(R.id.friends_button);  //Disable the corresponding button
        //disableFriends.setEnabled(false);  //To prevent people from creating the same activity over and over again
        friendsList = (ListView) findViewById(R.id.friends_view);

        //get the data
        new LongRunningGetIO().execute();
    }

    //Set to 0 for now
    private static String FRIENDS_URI = "http://10.0.2.2:9998/climbingserver/friends/0";

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
            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n > 0) {
                byte[] b = new byte[4096];
                n = in.read(b);
                if (n > 0) out.append(new String(b, 0, n));
            }
            /**   BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
             StringBuilder total = new StringBuilder();
             String[] data = new String[400];
             String holder = "";
             int i = 0;
             while((holder = reader.readLine()) != null) {
             myData[i] = holder;
             i++;
             } */
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
            HttpGet httpGet = new HttpGet(FRIENDS_URI);
            String text = null;
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
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, data);
                friendsList.setAdapter(adapter);
            }
        }

    }

    //onPause() method
    protected void onPause() {
        super.onPause();
    }
}
