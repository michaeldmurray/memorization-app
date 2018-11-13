package csc472.depaul.edu.micvalmoy.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.Format;

import csc472.depaul.edu.micvalmoy.R;

public class QuestionActivity extends DescendantActivity {

    // Passing the below information through bundles seems lightweight.
    // We could instead make Question class implement Parcelable and pass the instance.
    public static final String MODE = "question mode";
    public static final String ID = "question id";

    private Format format;

    public enum mode {
        QUIZ,
        PRACTICE,
        RESULT
    }

    //TODO replace this with question format from Question model
    private enum Format {
        TRUE_FALSE,
        MULTI_CHOICE,
        MULTI_ANSWER,
        FILL_IN_THE_BLANK
    }

    //TODO replace this with actual analysis of Question instance
    private Format getQuestionFormat(int question) {
        switch (question) {
            case 1:
                return Format.TRUE_FALSE;
            case 2:
                return Format.MULTI_CHOICE;
            case 3:
                return Format.MULTI_ANSWER;
            case 4:
                return Format.FILL_IN_THE_BLANK;
            default:
                Log.e("QuestionFormat","This should not be reached. returning T/F");
                return Format.TRUE_FALSE;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        //need to pull in xml skeleton when built out.
        //insert question text.
        //obtain questionFormat
        //insert question type text.
        //delegate to specific onCreates based on FORMAT
        //delegate to specific onCreates based on MODE
    }
}
