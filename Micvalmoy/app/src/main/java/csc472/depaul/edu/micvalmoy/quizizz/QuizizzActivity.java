package csc472.depaul.edu.micvalmoy.quizizz;

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


public class QuizizzActivity extends AppCompatActivity  implements QuizizzRecyclerViewAdapter.ActionCallback {

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
            /*

            if (intent.hasExtra(IntentUtil.EXTRA_SEARCH_PARAMETER)) {
                quizizzSearchParm =  intent.getStringExtra(IntentUtil.EXTRA_SEARCH_PARAMETER);
            }*/
        }

        Timber.d("quizizzSearchParm");

        //** moya-  Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        QuizizzSearchFragment quizizzSearchFragment =  QuizizzSearchFragment.newInstance(quizizzSearchParm);
        ft.replace(R.id.searchQuizizzFrameLayout, quizizzSearchFragment);
        ft.commit();
    }

    @Override
    public void onEdit(QuizInfo quizInfo) {
        Intent intent = new Intent(this, EditQuizizzActivity.class);
        intent.putExtras(EditQuizizzActivity.newInstanceBundle(quizInfo.getId()));
        startActivity(intent);
    }
}



