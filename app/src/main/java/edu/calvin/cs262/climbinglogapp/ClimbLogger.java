package edu.calvin.cs262.climbinglogapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chris on 11/2/2015.
 * This is the page that the user will see when the click on the big green button.
 */
public class ClimbLogger extends BaseActivity {

    private SlidingUpPanelLayout slideLayout;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private String[] difficulties = new String[4];
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logger);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        //set the new sliding layout
       // slideLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        //make it so that we can programmatically control the sliding
        //slideLayout.setTouchEnabled(false);

        //fill array with difficulties
        for(int i = 0; i < 3; i++) {
            difficulties[i] = "5." + i + "+";
        }


        TextView[] textViewArray = new TextView[4];

        for(int i = 0; i < 3; i++) {
            textViewArray[i] = new TextView(this);
            textViewArray[i].setText(difficulties[i]);

        }
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
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");


        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
    }

    //this method opens the sliding menu when the text is clicked
    public void slide(View view){
       // slideLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
       // slideLayout.setTouchEnabled(true);
    }

    //this method handles the submit button and sends the app back to the home page
    public void submit(View view) {
        //handle database stuff here

        //send the app back to the main activity
        Intent mainIntent = new Intent(ClimbLogger.this, MainActivity.class);
        ClimbLogger.this.startActivity(mainIntent);
    }


}