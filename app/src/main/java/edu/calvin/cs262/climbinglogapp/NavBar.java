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
     * Adapted from StackOverflow... http://stackoverflow.com/questions/8977212/button-click-listeners-in-android
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View NavBarView = inflater.inflate(R.layout.navbar_view, container, false);
        Button profile = (Button) NavBarView.findViewById(R.id.button3);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileIntent = new Intent(getActivity(), Profile.class);
                startActivity(profileIntent);
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
