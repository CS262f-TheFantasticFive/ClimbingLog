package edu.calvin.cs262.climbinglogapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.app.Activity;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbu2 on 11/4/2015.
 * Routes page
 */
public class Routes extends BaseActivity implements View.OnClickListener {

    //onCreate() method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routes);
        ImageButton disableRoutes = (ImageButton) findViewById(R.id.routes_button);  //Disable the corresponding button
        disableRoutes.setEnabled(false);  //To keep people from creating the same activity over and over again
        findViewById(R.id.get_routes_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        Button b = (Button) findViewById(R.id.get_routes_button);
        b.setClickable(false);
        new LongRunningGetIO().execute();
    }

    private static String CLIMBS_URI = "http://153.106.82.29:9998/climbingserver/climbs";

    private class LongRunningGetIO extends AsyncTask<Void, Void, String> {

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
            HttpGet httpGet = new HttpGet(CLIMBS_URI);
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
                EditText et = (EditText) findViewById(R.id.get_routes_text);
                et.setText(results);
            }
            Button b = (Button) findViewById(R.id.get_routes_button);
            b.setClickable(true);
        }

    }

    //onPause() method
    protected void onPause() {
        super.onPause();
    }
}
