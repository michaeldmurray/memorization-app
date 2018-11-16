package csc472.depaul.edu.micvalmoy.quizizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import csc472.depaul.edu.micvalmoy.BuildConfig;
import csc472.depaul.edu.micvalmoy.R;
import csc472.depaul.edu.micvalmoy.TimberDebugTree;
import csc472.depaul.edu.micvalmoy.tools.IntentUtil;
import timber.log.Timber;

public class EditQuizizzActivity extends AppCompatActivity {

    String quizinfoId = null;

    public static Bundle newInstanceBundle(String quizinfoId) {
        Bundle bundle = new Bundle();
        bundle.putString(IntentUtil.EXTRA_QUIZINFO_ID, quizinfoId);
        return bundle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quizizz);

        //Enable timber for logging
        if (BuildConfig.DEBUG || BuildConfig.FLAVOR.equals("withlog")) {
            //Remove all planted trees.
            Timber.uprootAll();

            Timber.plant(new TimberDebugTree());
        }
        //TODO: Add a crash analytics tool for production
        //**************************************************************

        Intent intent = getIntent();
        if (intent.hasExtra(IntentUtil.EXTRA_QUIZINFO_ID)) {
            quizinfoId = intent.getStringExtra(IntentUtil.EXTRA_QUIZINFO_ID);
        }

        Timber.d("quizizz quiz id:  %s", quizinfoId);
    }
}
