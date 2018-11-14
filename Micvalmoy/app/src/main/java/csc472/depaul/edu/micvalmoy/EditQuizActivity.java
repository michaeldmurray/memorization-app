package csc472.depaul.edu.micvalmoy;

        import android.arch.lifecycle.ViewModelProviders;
        import android.content.Intent;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Toast;

        import csc472.depaul.edu.micvalmoy.entity.Quiz;
        import csc472.depaul.edu.micvalmoy.tools.IntentUtil;
        import timber.log.Timber;

public class EditQuizActivity extends AppCompatActivity {

    private EditQuizViewModel viewModelEditQuiz;

    Quiz quiz = null;
    Long quizId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_quiz);


        //Enable timber for logging
        if (BuildConfig.DEBUG || BuildConfig.FLAVOR.equals("withlog")) {
            //Remove all planted trees.
            Timber.uprootAll();

            Timber.plant(new TimberDebugTree());
        }
        //**************************************************************


        //---------------------------------------------------------------------------------
        /**
         * make sure there is information in the Bundle or the restored state
         * when the activity is newly created, in the onCreate(Bundle savedInstanceState) method, the bundle savedInstanceState is null.
         * if it is null get the information from Intent;
         */

        if (savedInstanceState != null) {
            //** Restore  data after rotation from the savedInstanceState bundle
            quiz = savedInstanceState.getParcelable(IntentUtil.BUNDLE_KEY_QUIZ_OBJ);
        }
        else{
            //** extract data from the intent bundle
            Intent intent = getIntent();
            quiz = intent.getParcelableExtra(IntentUtil.BUNDLE_KEY_QUIZ_OBJ);
        }





        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null && intent.hasExtra(IntentUtil.EXTRA_QUIZ_ID)){
            quizId = bd.getLong(IntentUtil.EXTRA_QUIZ_ID);
        }

        if(quizId != null) {
            Toast.makeText(getApplicationContext(), "Please wait ... loading quiz details", Toast.LENGTH_LONG).show();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.quizEditFrameLayout, EditQuizFragment.newInstance(quizId));
            viewModelEditQuiz = ViewModelProviders.of(this).get(EditQuizViewModel.class);

            ft.commit();
        }else{
            Toast.makeText(getApplicationContext(), "cannot load quiz", Toast.LENGTH_LONG).show();
        }
    }
}
