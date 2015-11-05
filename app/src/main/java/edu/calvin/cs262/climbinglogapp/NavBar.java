package edu.calvin.cs262.climbinglogapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        return inflater.inflate(R.layout.navbar_view, container, false);

    }

}
