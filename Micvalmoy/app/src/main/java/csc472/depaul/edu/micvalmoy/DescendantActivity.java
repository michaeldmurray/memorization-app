package csc472.depaul.edu.micvalmoy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import timber.log.Timber;


/**This activity houses logic to include a back button within the Action Bar that will navigate
 * to the previous activity, rather than the default "Up" functionality.
 * Subclasses of this activity must set contentView and handle any additional Options Items.
 * **/

public class DescendantActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Enable timber for logging
        if (BuildConfig.DEBUG || BuildConfig.FLAVOR.equals("withlog")) {
            //Remove all planted trees.
            Timber.uprootAll();

            Timber.plant(new TimberDebugTree());
        }
        //TODO: Add a crash analytics tool for production
        //**************************************************************

        // Set up header
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // overrides header back from "up" to "previous activity"
//                Log.d("Quiz","Back clicked");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
