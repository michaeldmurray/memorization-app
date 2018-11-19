package csc472.depaul.edu.micvalmoy;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;


public class MultipleChoiceQuiz extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        List<QuestionAnswerOption> answerchoices = new ArrayList<>();
        List<QuestionAnswerOption> correctAnswers = new ArrayList<>();


    }




    /**
     *
     * https://android.okhelp.cz/create-radiobutton-radiogroup-dynamically-android-sample/
     * toggle visibility
     * setVisibility(button.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE)
     */
    public LinkedHashMap<Integer,QuestionAnswerOption> setChoices( List<QuestionAnswerOption> answerchoices, List<QuestionAnswerOption> correctAnswers){
        LinearLayout layout = (LinearLayout) findViewById(R.id.questionAnswerChoice);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setVisibility(View.GONE);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinkedHashMap<Integer,QuestionAnswerOption> choiceButtons = new LinkedHashMap<>();

        int viewRowCount = answerchoices.size();

        int row=0;
        if(correctAnswers.size() > 1){
            for(QuestionAnswerOption answerchoice : answerchoices){
                Integer id = (row * 6)+2;
                choiceButtons.put(id, answerchoice);

                CheckBox cbxbtn = new CheckBox(context);
                cbxbtn.setId(id);
                cbxbtn.setText(answerchoice.getText());

                cbxbtn.setOnClickListener(mThisButtonListener);

                layout.addView(cbxbtn, p);
                row++;
            }
        }else{
            RadioGroup radioGroup = new RadioGroup(this);

            for(QuestionAnswerOption answerchoice : answerchoices){
                Integer id = (row * 6)+2;
                choiceButtons.put(id, answerchoice);

                RadioButton rdbtn = new RadioButton(context);
                rdbtn.setId(id);
                rdbtn.setText(answerchoice.getText());

                rdbtn.setOnClickListener(mThisButtonListener);
                radioGroup.addView(rdbtn, p);

                row++;
            }
            layout.addView(radioGroup, p);
        }

            layout.setVisibility(View.VISIBLE);
        return choiceButtons;
    }


    private View.OnClickListener mThisButtonListener = new View.OnClickListener() {
        public void onClick(View v) {

            RadioButton rdbtn = ((RadioButton) v);
            String s = ((RadioButton) v).getText().toString();
            Toast.makeText(getApplicationContext(), "Hello from 2!" + s,
                    Toast.LENGTH_LONG).show();
        }
    };



}
