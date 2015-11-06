package edu.calvin.cs262.climbinglogapp;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by Chris on 11/2/2015.
 * This is the page that the user will see when the click on the big green button.
 */
public class ClimbLogger extends FragmentActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logger);

        final CheckBox route_box = (CheckBox) findViewById(R.id.route_box);
        if (!route_box.isChecked()) {
            route_box.setChecked(true);
        }
        final CheckBox difficulty_box = (CheckBox) findViewById(R.id.difficulty_box);
        final CheckBox color_box = (CheckBox) findViewById(R.id.color_box);
        final CheckBox notes_box = (CheckBox) findViewById(R.id.notes_box);
    }

    /**
     * This method determines what should happen when a checkbox is clicked.
     */
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.route_box:
                if (checked) {
                    //expand some menu? perhaps we should use contextual menus instead
                }
                else
                    //collapse menu - perhaps this branch isn't needed
                break;
            case R.id.difficulty_box:
                if (checked) {
                    //expand some menu
                }
                else
                    //collapse menu
                    break;
            case R.id.color_box:
                if (checked) {
                    //expand some menu
                }
                else
                    //collapse menu
                    break;
            case R.id.notes_box:
                if (checked) {
                    //expand some menu
                }
                else
                    //collapse menu
                    break;
            // TODO: "More" box
        }
    }


}
