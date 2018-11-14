package csc472.depaul.edu.micvalmoy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import timber.log.Timber;


public class QuizSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_search);

        //Enable timber for logging
        if (BuildConfig.DEBUG || BuildConfig.FLAVOR.equals("withlog")) {
            //Remove all planted trees.
            Timber.uprootAll();

            Timber.plant(new TimberDebugTree());
        }
        //TODO: Add a crash analytics tool for production
        //**************************************************************


        Button btn = (Button) findViewById(R.id.btnSearchQuizizz);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText etQuizizzSrchParm = (EditText) findViewById(R.id.etQuizizzSrchParm);
                Intent intent = new Intent(QuizSearch.this, QuizizzActivity.class);
                intent.putExtra("QuizizzSearchParameter", etQuizizzSrchParm.getText());
                startActivity(intent);

            }
        });




    }
}



