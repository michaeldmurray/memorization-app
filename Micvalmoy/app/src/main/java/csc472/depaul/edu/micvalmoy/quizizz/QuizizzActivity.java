package csc472.depaul.edu.micvalmoy.quizizz;

/**
 * @author mrichards
 */


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import csc472.depaul.edu.micvalmoy.BuildConfig;
import csc472.depaul.edu.micvalmoy.R;
import csc472.depaul.edu.micvalmoy.TimberDebugTree;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;
import csc472.depaul.edu.micvalmoy.tools.IntentUtil;
import timber.log.Timber;


public class QuizizzActivity extends AppCompatActivity  {

    String quizizzSearchParm = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizizz);

        //Enable timber for logging
        if (BuildConfig.DEBUG || BuildConfig.FLAVOR.equals("withlog")) {
            //Remove all planted trees.
            Timber.uprootAll();

            Timber.plant(new TimberDebugTree());
        }
        //TODO: Add a crash analytics tool for production
        //**************************************************************

        if(savedInstanceState != null) {
            quizizzSearchParm = savedInstanceState.getString(IntentUtil.EXTRA_SEARCH_PARAMETER);
        }else{

            Intent intent = getIntent();
            Bundle bd = intent.getExtras();

            if (intent.hasExtra(IntentUtil.EXTRA_SEARCH_PARAMETER)) {
                quizizzSearchParm = (String) bd.get(IntentUtil.EXTRA_SEARCH_PARAMETER).toString();

            }
        }

        Timber.d(IntentUtil.EXTRA_SEARCH_PARAMETER);

        //** moya-  Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        QuizizzSearchFragment quizizzSearchFragment =  QuizizzSearchFragment.newInstance(quizizzSearchParm);
        ft.replace(R.id.searchQuizizzFrameLayout, quizizzSearchFragment);
        ft.commit();
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
        outState.putString(IntentUtil.EXTRA_SEARCH_PARAMETER, quizizzSearchParm);
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
            quizizzSearchParm = savedInstanceState.getString(IntentUtil.EXTRA_SEARCH_PARAMETER);

            //** populate title with the name of the Investment
            setTitle("Quizzizz search term:" + quizizzSearchParm);
        }
    }




}



