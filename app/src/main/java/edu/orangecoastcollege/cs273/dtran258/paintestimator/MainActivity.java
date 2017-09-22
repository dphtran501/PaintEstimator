package edu.orangecoastcollege.cs273.dtran258.paintestimator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This activity allows the user to input information about the dimensions of a room and the number
 * of doors and windows in that room. The user can then click a button to calculate the gallons of
 * paint needed to paint that room.
 * <p>
 *     In addition, the user can click a button that will display an explanation of the calculation
 *     for the surface area of the room and the gallons of paint required.
 * </p>
 *
 * @author Derek Tran
 * @version 1.0
 * @since September 19, 2017
 */
public class MainActivity extends AppCompatActivity
{

    // VIEW
    private EditText mLengthEditText;
    private EditText mWidthEditText;
    private EditText mHeightEditText;
    private EditText mDoorsEditText;
    private EditText mWindowsEditText;
    private TextView mGallonsTextView;

    // Model
    private InteriorRoom mRoom = new InteriorRoom();

    // SharedPreferences
    private SharedPreferences mPrefs;

    private void initializeViews()
    {
        // TODO: Connnect View member variables to ids in layout
    }

    private void loadSharedPreferences()
    {
        mPrefs = getSharedPreferences("edu.orangecoastcollege.cs273.dtran258.paintestimator", MODE_PRIVATE);
        if (mPrefs != null)
        {
            // Load all the room information
            mRoom.setDoors(mPrefs.getInt("doors", 0));
            mDoorsEditText.setText(String.valueOf(mRoom.getDoors()));
            mRoom.setHeight(mPrefs.getFloat("height", 0.0f));
            mHeightEditText.setText(String.valueOf(mRoom.getHeight()));
            mRoom.setLength(mPrefs.getFloat("length", 0.0f));
            mLengthEditText.setText(String.valueOf(mRoom.getLength()));
            // TODO: finish for other Views

        }
    }

    private void saveSharedPreferences()
    {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.clear();
        editor.putFloat("length", mRoom.getLength());
        editor.putFloat("height", mRoom.getHeight());
        editor.putFloat("width", mRoom.getWidth());
        editor.putInt("doors", mRoom.getDoors());
        editor.putInt("windows", mRoom.getWindows());
        // Save changes to SharedPreferences file
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadSharedPreferences();

    }

    protected void computeGallons(View v)
    {
        // Update model first, then calculate
        mRoom.setLength(Float.parseFloat(mLengthEditText.getText().toString()));
        mRoom.setHeight(Float.parseFloat(mHeightEditText.getText().toString()));
        // TODO: continue updating model

        mGallonsTextView.setText(getString(R.string.interior_surface_area_text) + mRoom.totalSurfaceArea()
                + "\n" + getString(R.string.gallons_needed_text) + mRoom.gallonsOfPaintRequired());
        saveSharedPreferences();
    }

    protected void goToHelp(View v)
    {
        // Construct EXPLICIT Intent to go to HelpActivity
        // Intent: specify where to start (context) and where we're going (next Activity)
        Intent helpIntent = new Intent(this, HelpActivity.class);
        helpIntent.putExtra("gallons", mRoom.gallonsOfPaintRequired());
        startActivity(helpIntent);
    }



}
