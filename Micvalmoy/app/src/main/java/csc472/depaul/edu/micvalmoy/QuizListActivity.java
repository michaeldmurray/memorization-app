package csc472.depaul.edu.micvalmoy;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import timber.log.Timber;

public class QuizListActivity extends AppCompatActivity {
    private QuizViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_list);


        //Enable timber for logging
        if (BuildConfig.DEBUG || BuildConfig.FLAVOR.equals("withlog")) {
            //Remove all planted trees.
            Timber.uprootAll();

            Timber.plant(new TimberDebugTree());
        }
        //TODO: Add a crash analytics tool for production
        //**************************************************************




        Toast.makeText(getApplicationContext(), "Please wait ... loading quizzes", Toast.LENGTH_LONG).show();

        // Set up your view model

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        QuizListFragment quizListFragment = new QuizListFragment();
        ft.replace(R.id.quizListFrameLayout, quizListFragment);

        ft.commit();
    }
}



