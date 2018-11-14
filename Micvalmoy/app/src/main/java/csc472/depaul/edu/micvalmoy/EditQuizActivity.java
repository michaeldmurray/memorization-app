package csc472.depaul.edu.micvalmoy;

        import android.arch.lifecycle.ViewModelProviders;
        import android.content.Intent;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Toast;

        import timber.log.Timber;

public class EditQuizActivity extends AppCompatActivity {

    private EditQuizViewModel viewModelEditQuiz;

    public static final String EXTRA_QUIZ_ID = "EXTRA_QUIZ_ID";
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
        //TODO: Add a crash analytics tool for production
        //**************************************************************


        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null && intent.hasExtra(EXTRA_QUIZ_ID)){
            quizId = bd.getLong(EXTRA_QUIZ_ID);
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
