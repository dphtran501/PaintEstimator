package edu.orangecoastcollege.cs273.dtran258.paintestimator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * This activity displays an explanation of how the calculations for the room's surface area and the
 * gallons of paint needed to paint it were performed in this application. This activity also allows
 * the user to go back to <code>MainActivity</code> by clicking a button.
 *
 * @author Derek Tran
 * @version 1.0
 * @since September 19, 2017
 */
public class HelpActivity extends AppCompatActivity
{

    // Decimal formatter
    private static final DecimalFormat oneDP = new DecimalFormat("0.0");

    // VIEW
    private TextView mGallonsTextView;

    /**
     * Initializes <code>HelpActivity</code> by inflating its UI.
     *
     * @param savedInstanceState Bundle containing the data it recently supplied in
     *                           onSaveInstanceState(Bundle) if activity was reinitialized after
     *                           being previously shut down. Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Receive the Intent from MainActivity
        Intent intentFromMain = getIntent();
        String gallons = oneDP.format(intentFromMain.getFloatExtra("gallons", 0.0f));

        mGallonsTextView = (TextView) findViewById(R.id.help_gallons_text_view);
        mGallonsTextView.setText(getString(R.string.gallons_needed_text) + " " + gallons);
    }

    /**
     * Ends this activity and returns to <code>MainActivity</code>.
     * @param v The <code>View</code> that called this method.
     */
    protected void goToMain(View v)
    {
        finish();
    }
}
