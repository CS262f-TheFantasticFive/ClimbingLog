package edu.calvin.cs262.climbinglogapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * Created by Chris on 11/2/2015.
 * Edited by Dave and Jacob on 11/12/2015
 * Added NavBar Icons
 * This is our NavBar! :D
 */
public class NavBar extends Fragment implements OnClickListener {

    /**
     * This method creates the NavBarView and adds onClickListeners() to each button.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Create the NavBar view
        View NavBarView = inflater.inflate(R.layout.navbar_view, container, false);
        //Get the buttons
        ImageButton profile = (ImageButton) NavBarView.findViewById(R.id.profile_button);
        ImageButton friends = (ImageButton) NavBarView.findViewById(R.id.friends_button);
        ImageButton routes = (ImageButton) NavBarView.findViewById(R.id.routes_button);

        //Set on click listeners for each button
        profile.setOnClickListener(this);  //Profile
        friends.setOnClickListener(this);   //Friends
        routes.setOnClickListener(this);  //Routes

        return NavBarView;
    }

    /**
     * This method sets OnClickListeners for each button. (It gets called in onCreateView(); see the call to setOnClickListener(this)).
     * Each listener activates the corresponding Activity.
     * Adapted from StackOverflow: http://stackoverflow.com/questions/25905086/multiple-buttons-onclicklistener-android.
     */
    @Override
    public void onClick(View v) {
        //Get the button that called setOnClickListener()
        //The process is the same for each button clicked:
        //Create an intent, start it up.
        switch (v.getId()) {
            case R.id.profile_button:  //Profile
                Intent profileIntent = new Intent(getActivity(), Profile.class);
                startActivity(profileIntent);
                break;
            case R.id.friends_button: //Friends
                Intent friendsIntent = new Intent(getActivity(), Friends.class);
                startActivity(friendsIntent);
                break;
            case R.id.routes_button: //Routes
                Intent routesIntent = new Intent(getActivity(), Routes.class);
                startActivity(routesIntent);
                break;
        }
    }
}
