package edu.calvin.cs262.climbinglogapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by Chris on 11/2/2015.
 * This is the page that the user will see when the click on the big green button.
 */
public class ClimbLogger extends MainActivity {

    private SlidingUpPanelLayout slideLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logger);

        //set the new sliding layout
        slideLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        //make it so that we can programmatically control the sliding
        slideLayout.setTouchEnabled(false);



    }

    public void slide(View view){
        slideLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
        slideLayout.setTouchEnabled(true);
    }


}