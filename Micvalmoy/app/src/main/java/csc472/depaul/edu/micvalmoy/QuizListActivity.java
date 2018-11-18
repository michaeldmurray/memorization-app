package csc472.depaul.edu.micvalmoy;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import csc472.depaul.edu.micvalmoy.repository.QuizViewModel;
import timber.log.Timber;

public class QuizListActivity extends AppCompatActivity implements QuizListEditDialogFragment.NoticeDialogListener{
    private QuizViewModel viewModel;

    FloatingActionButton fabAddNewQuiz;


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

        fabAddNewQuiz = findViewById(R.id.fab_new_quiz);
        if (fabAddNewQuiz != null) { fabAddNewQuiz.setOnClickListener(onClickAddNewQuiz); }

    }

    private View.OnClickListener onClickAddNewQuiz = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Timber.d("onClick -- button = onClickAddNewQuiz");

                //works ----------------------------------------------------------
/*            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            QuizListEditDialogFragment prev = (QuizListEditDialogFragment) getSupportFragmentManager().findFragmentByTag(QuizListEditDialogFragment.TAG);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            DialogFragment dialogFragment = QuizListEditDialogFragment.newInstance();
            dialogFragment.show(ft, "dialog");*/

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            QuizListEditDialogFragment prev = (QuizListEditDialogFragment) getSupportFragmentManager().findFragmentByTag(QuizListEditDialogFragment.TAG);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            DialogFragment dialogFragment = QuizListEditDialogFragment.newInstance(false, null, -1);
            dialogFragment.show(ft, "dialog");





          /*  QuizListFragment quizListFragment = (QuizListFragment) getSupportFragmentManager().findFragmentByTag(QuizListFragment.TAG);
            if (quizListFragment != null) {
               Timber.d("QuizListFragment loaded on screen");
                //quizListFragment.showQuizDialog(false, null, -1);
            }*/



/*            QuizListFragment fragment = (QuizListEditDialogFragment) getSupportFragmentManager().findFragmentByTag(QuizListEditDialogFragment.TAG);
            if (fragment != null) {


            }*/
        }
    };



    @Override
    public void onDialogPositiveClick(DialogFragment dialog, QuizListEditDialogFragment.DialogEventArgs dialogEventArgs) {
        Timber.d("onClick -- DialogFragment = onDialogPositiveClick");
        View view = dialog.getView();

        Long quizId =  dialogEventArgs.getId();
        String status = dialogEventArgs.getStatus();
        Timber.d("onClick -- onDialogPositiveClick quid id: %s %s", quizId,status );
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog, QuizListEditDialogFragment.DialogEventArgs dialogEventArgs) {
        Timber.d("onClick -- DialogFragment = onDialogNegativeClick");
    }
}



