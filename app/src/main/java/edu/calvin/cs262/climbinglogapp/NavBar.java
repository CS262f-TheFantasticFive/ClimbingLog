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
public class NavBar extends Fragment implements OnClickListener {

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
        profile.setOnClickListener(this);
        gyms.setOnClickListener(this);
        friends.setOnClickListener(this);
        routes.setOnClickListener(this);

        return NavBarView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_button:
                Intent profileIntent = new Intent(getActivity(), Profile.class);
                startActivity(profileIntent);
                break;
            case R.id.gyms_button:
                Intent gymsIntent = new Intent(getActivity(), Gyms.class);
                startActivity(gymsIntent);
                break;
            case R.id.friends_button:
                Intent friendsIntent = new Intent(getActivity(), Friends.class);
                startActivity(friendsIntent);
                break;
            case R.id.routes_button:
                Intent routesIntent = new Intent(getActivity(), Routes.class);
                startActivity(routesIntent);
                break;
        }
    }
}
