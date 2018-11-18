package csc472.depaul.edu.micvalmoy.quizizz;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.BuildConfig;
import csc472.depaul.edu.micvalmoy.R;
import csc472.depaul.edu.micvalmoy.TimberDebugTree;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;
import csc472.depaul.edu.micvalmoy.tools.IntentUtil;
import timber.log.Timber;

public class EditQuizizzActivity extends AppCompatActivity {
    private QuizizzViewModel viewModel;

    QuizizzRepository quizizzRepository = QuizizzRepository.getInstance();

    String quizInfoId = null;

    public static Bundle newInstanceBundle(String quizInfoId) {
        Bundle bundle = new Bundle();
        bundle.putString(IntentUtil.EXTRA_QUIZINFO_ID, quizInfoId);
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
        if(savedInstanceState != null) {
            quizInfoId = savedInstanceState.getString(IntentUtil.EXTRA_QUIZINFO_ID);
        }else{
            if (intent.hasExtra(IntentUtil.EXTRA_QUIZINFO_ID)) {
                quizInfoId = intent.getStringExtra(IntentUtil.EXTRA_QUIZINFO_ID);
                // quizInfoId = (String) bd.get(IntentUtil.EXTRA_QUIZINFO_ID).toString();
            }
        }


        Timber.d("quizizz quiz id:  %s", quizInfoId);


        //------------
        // Set up your view model
        viewModel = ViewModelProviders.of(this).get(QuizizzViewModel.class);

        //**************************************************************************************
        // Observe the view model
        viewModel.getQuizizzByIdLiveData(quizInfoId).observe(this, new Observer<List<QuizInfo>>() {
            @Override
            public void onChanged(@Nullable List<QuizInfo> quizInfos) {
                //mAdapter.submitList(quizInfos);

                Timber.d("listing the quizzes found on quizizz.com");
            }
        });


        /**
         * Start Background process
         * This will start the Background off-the-UI-thread work to search quizizz.com based on the search parameter provided
         */
        quizizzRepository.findQuizizzByTerm(quizInfoId);
    }
    /**
     * ---------------------------------------------------------------------------------
     * Moya - State to handle Rotation
     * onSaveInstanceState gets called before onStop but it is not guaranteed to be called before or after onPause.
     * Android will also only call it when the application needs to save temporary state which includes when
     * orientation changes occur and when the Activity is killed for its memory resources.
     * It will not be called in certain situations such as finishing an Activity normally or putting an Activity into the background
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IntentUtil.EXTRA_QUIZINFO_ID, quizInfoId);
    }


    /**
     * called after OnStart() has completed
     * The savedInstanceState Bundle is same as the one used in onCreate()
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            //** Restore  data after rotation from the savedInstanceState bundle
            quizInfoId = savedInstanceState.getString(IntentUtil.EXTRA_QUIZINFO_ID);

            //** populate title with the name of the Investment
            //setTitle("Quizzizz search term:" + quizInfoId);
        }
    }


}
