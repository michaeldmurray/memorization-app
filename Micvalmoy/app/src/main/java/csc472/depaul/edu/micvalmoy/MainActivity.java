package csc472.depaul.edu.micvalmoy;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Quizizz;
import csc472.depaul.edu.micvalmoy.quizizz.QuizizzJson;
import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                Intent intent = new Intent(MainActivity.this, QuizizzActivity.class);
                intent.putExtra("QuizizzSearchParameter", etQuizizzSrchParm.getText());
                startActivity(intent);

            }
        });




    }
}



