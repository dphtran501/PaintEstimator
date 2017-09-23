package edu.orangecoastcollege.cs273.dtran258.paintestimator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

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

    // Decimal formatter
    private static final DecimalFormat oneDP = new DecimalFormat("0.0");

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
        mLengthEditText = (EditText) findViewById(R.id.length_edit_text);
        mWidthEditText = (EditText) findViewById(R.id.width_edit_text);
        mHeightEditText = (EditText) findViewById(R.id.height_edit_text);
        mDoorsEditText = (EditText) findViewById(R.id.doors_edit_text);
        mWindowsEditText = (EditText) findViewById(R.id.windows_edit_text);
        mGallonsTextView = (TextView) findViewById(R.id.gallons_text_view);
    }

    private void loadSharedPreferences()
    {
        mPrefs = getSharedPreferences("edu.orangecoastcollege.cs273.dtran258.paintestimator", MODE_PRIVATE);
        if (mPrefs != null)
        {
            // Load all the room information
            mRoom.setLength(mPrefs.getFloat("length", 0.0f));
            mLengthEditText.setText(String.valueOf(mRoom.getLength()));
            mRoom.setWidth(mPrefs.getFloat("width", 0.0f));
            mWidthEditText.setText(String.valueOf(mRoom.getWidth()));
            mRoom.setHeight(mPrefs.getFloat("height", 0.0f));
            mHeightEditText.setText(String.valueOf(mRoom.getHeight()));
            mRoom.setDoors(mPrefs.getInt("doors", 0));
            mDoorsEditText.setText(String.valueOf(mRoom.getDoors()));
            mRoom.setWindows(mPrefs.getInt("windows", 0));
            mWindowsEditText.setText(String.valueOf(mRoom.getWindows()));
        }
    }

    private void saveSharedPreferences()
    {
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.clear();
        editor.putFloat("length", mRoom.getLength());
        editor.putFloat("width", mRoom.getWidth());
        editor.putFloat("height", mRoom.getHeight());
        editor.putInt("doors", mRoom.getDoors());
        editor.putInt("windows", mRoom.getWindows());
        // Save changes to SharedPreferences file
        editor.commit();
    }

    /**
     * Initializes <code>MainActivity</code> by inflating its UI. Also loads <code>EditText</code>
     * views with data from <code>SharedPreferences</code> file.
     *
     * @param savedInstanceState Bundle containing the data it recently supplied in
     *                           onSaveInstanceState(Bundle) if activity was reinitialized after
     *                           being previously shut down. Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        loadSharedPreferences();
    }

    /**
     * Computes the gallons of paint needed to paint the total surface area of the room with
     * dimensions specified by the user.
     * @param v The <code>View</code> that called this method.
     */
    protected void computeGallons(View v)
    {
        // Update model first, then calculate
        mRoom.setLength(Float.parseFloat(mLengthEditText.getText().toString()));
        mRoom.setWidth(Float.parseFloat(mWidthEditText.getText().toString()));
        mRoom.setHeight(Float.parseFloat(mHeightEditText.getText().toString()));
        mRoom.setDoors(Integer.parseInt(mDoorsEditText.getText().toString()));
        mRoom.setWindows(Integer.parseInt(mWindowsEditText.getText().toString()));

        mGallonsTextView.setText(getString(R.string.interior_surface_area_text)
                + " " + oneDP.format(mRoom.totalSurfaceArea())+ " feet"
                + "\n" + getString(R.string.gallons_needed_text) + " "
                + oneDP.format(mRoom.gallonsOfPaintRequired()));

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
