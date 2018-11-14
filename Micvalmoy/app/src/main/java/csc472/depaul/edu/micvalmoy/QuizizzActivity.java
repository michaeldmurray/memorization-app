package csc472.depaul.edu.micvalmoy;

        import android.content.Intent;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.ViewGroup;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;

        import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;
        import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Quizizz;
        import csc472.depaul.edu.micvalmoy.quizizz.QuizizzJson;
        import timber.log.Timber;



public class QuizizzActivity extends AppCompatActivity {

    private ListView lv;
    private ViewGroup mListHeaderView;


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

        lv = (ListView) findViewById(R.id.list);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();

        if(bd != null) {
            String qsParm = (String) bd.get("QuizizzSearchParameter").toString();

            //Start Background process to search quizizz.com based on the search parameter provided
            GetJSONData jData = new GetJSONData();
            jData.setSearchParameter(qsParm);
            jData.execute();
        }
    }

    private class GetJSONData extends AsyncTask<Void, Void, Void> {

        String quizizzSearchParm   = null;

        QuizizzJson quizizzContent = new QuizizzJson();
        ArrayList<QuizInfo> quizzes = new ArrayList<>();

        /**
         * set the search parameter used to get the list of quizzes from quizizz.com
         * @param  sp the search parameter
         */
        public void setSearchParameter(String sp){
            quizizzSearchParm = sp;
        }


        @Override
        /**
         * Show a pop up that data is being downloading
         */
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(QuizizzActivity.this,"Please wait ... loading quizzes",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {


            // Making a request to url and getting response

            Quizizz qz = quizizzContent.getJsonResponse(quizizzSearchParm);


            quizzes = qz.getQuizzes();

            Timber.d("json dataobject %s" , quizzes.toString());

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //** load custom list adapter to show the quizizz list of quizzes
            ListAdapter adapter = new quizizzAdapter(QuizizzActivity.this, quizzes);
            lv.setAdapter(adapter);
        }
    }
}



