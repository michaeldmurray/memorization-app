package csc472.depaul.edu.micvalmoy.quizizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import csc472.depaul.edu.micvalmoy.BuildConfig;
import csc472.depaul.edu.micvalmoy.R;
import csc472.depaul.edu.micvalmoy.TimberDebugTree;
import timber.log.Timber;

public class QuizizzSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzizz_search);

        //Enable timber for logging
        if (BuildConfig.DEBUG || BuildConfig.FLAVOR.equals("withlog")) {
            //Remove all planted trees.
            Timber.uprootAll();

            Timber.plant(new TimberDebugTree());
        }
        //TODO: Add a crash analytics tool for production
        //**************************************************************


        Button btnSearchQuizizz = (Button) findViewById(R.id.btnSearchQuizizz);

        btnSearchQuizizz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText etQuizizzSrchParm = (EditText) findViewById(R.id.etQuizizzSrchParm);
                Intent intent = new Intent(QuizizzSearchActivity.this, QuizizzActivity.class);
                intent.putExtra("QuizizzSearchParameter", etQuizizzSrchParm.getText());
                startActivity(intent);

            }
        });





    }
}



