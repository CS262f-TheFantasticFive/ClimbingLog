package edu.calvin.cs262.climbinglogapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by Chris on 11/2/2015.
 * This is our NavBar! :D
 */
public class NavBar extends Fragment {

    /**
     * This is the method that creates the NavBar.
     * onClickListeners() are placed on each button so that when you click a button on the NavBar,
     * You are taken to that page. 
     * Adapted from StackOverflow... http://stackoverflow.com/questions/8977212/button-click-listeners-in-android
     *
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Create the NavBar view
        View NavBarView = inflater.inflate(R.layout.navbar_view, container, false);
        //Get the buttons
        Button profile = (Button) NavBarView.findViewById(R.id.profile_button);
        Button gyms = (Button) NavBarView.findViewById(R.id.gyms_button);
        Button friends = (Button) NavBarView.findViewById(R.id.friends_button);
        Button routes = (Button) NavBarView.findViewById(R.id.routes_button);

        //Set on click listeners for each button
        //Profile
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(getActivity(), Profile.class);
                startActivity(profileIntent);
            }
        });

        //Gyms
        gyms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gymIntent = new Intent(getActivity(), Gyms.class);
                startActivity(gymIntent);
            }
        });

        //Friends
        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent friendIntent = new Intent(getActivity(), Friends.class);
                startActivity(friendIntent);
            }
        });

        //Routes
        routes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent routeIntent = new Intent(getActivity(), Routes.class);
                startActivity(routeIntent);
            }
        });
        return NavBarView;
    }

    public void startProfile(View view) {
        Intent profileIntent = new Intent(getActivity(), Profile.class);
        startActivity(profileIntent);
    }

//    public void startGym(View view) {
////        Intent gymIntent = new Intent(getActivity(), Profile.class);
////        startActivity(gymIntent);
//    }
//
//    public void startFriends(View view) {
//        Intent profileIntent = new Intent(getActivity(), Profile.class);
//        startActivity(profileIntent);
//    }
//
//    public void startRoutes(View view){
//        Intent profileIntent = new Intent(getActivity(), Profile.class);
//        startActivity(profileIntent);
//    }
}
