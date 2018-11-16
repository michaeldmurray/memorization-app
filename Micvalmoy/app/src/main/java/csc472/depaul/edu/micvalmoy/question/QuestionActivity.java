package csc472.depaul.edu.micvalmoy.question;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;

import csc472.depaul.edu.micvalmoy.R;
import csc472.depaul.edu.micvalmoy.DescendantActivity;

public class QuestionActivity extends DescendantActivity {

    // Passing the below information through bundles seems lightweight.
    // We could instead make Question class implement Parcelable and pass the instance.
    public static final String MODE = "question mode";
    public static final String ID = "question id";
    private Bundle questionBundle;
    private Format format;
    private Mode mode;

    public enum Mode {
        QUIZ,
        RESULT
//      MVP not supporting this mode
//        , PRACTICE
    }

    //TODO replace this with question format from Question model
    private enum Format {
        TRUE_FALSE,
        MULTI_CHOICE,
        MULTI_ANSWER
        //MVP not supporting this type
//        , FILL_IN_THE_BLANK
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
            default:
                Log.e("QuestionFormat","This should not be reached. returning T/F");
                return Format.TRUE_FALSE;
        }
    }

    private String getQuestionFormatText(Format format) {
        switch (format) {
            case TRUE_FALSE:
                return "True or False";
            case MULTI_CHOICE:
                return "Multiple Choice";
            case MULTI_ANSWER:
                return "Multiple Answer";
            // MVP not supporting this type
//            case FILL_IN_THE_BLANK:
//                return "Fill in the Blank";
            default:
                return "ERROR";
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) { return;}

        // set question ID and mode
        questionBundle = getIntent().getExtras();
        if (!questionBundle.containsKey(ID) || !questionBundle.containsKey(MODE)) {
            Log.e("QuestionActivity","Missing question data");
            return;
        }
        format = getQuestionFormat(questionBundle.getInt(ID));
        mode = Mode.valueOf(questionBundle.getString(MODE));

        // Setting up Activity data
        setContentView(R.layout.activity_question);
        TextView questionFormatText = findViewById(R.id.value_question_format);
        if (questionFormatText != null) {
            questionFormatText.setText(getQuestionFormatText(format));
        }

        TextView questionPromptText = findViewById(R.id.value_question_text);
        if (questionPromptText != null) {
            // TODO set text from question object.
            questionPromptText.setText("This is a hardcoded question prompt that should be replaced.");
        }

        // Setting up Fragment data
        if (findViewById(R.id.answers_fragment_container) != null) {
            addAnswersFragment(mode, format, R.id.answers_fragment_container);
        }

        //insert question type text.
        //delegate to specific onCreates based on FORMAT
        //delegate to specific onCreates based on MODE
    }

    private void addAnswersFragment(Mode mode, Format format,int containerID) {
        Fragment firstFragment = null;

//        MVP not supporting this mode
//        if (mode == Mode.PRACTICE) {
//            // add practice button fragment
//        }

        switch(format) {
            case TRUE_FALSE:
                // Assuming this can use multiple choice fragment
            case MULTI_CHOICE:
                firstFragment = new MultipleChoiceFragment();
                break;
            case MULTI_ANSWER:
                //TODO add MA fragment
                firstFragment = new MultipleAnswerFragment();
                break;
//            MVP not supporting this type
//            case FILL_IN_THE_BLANK:
//                break;
        }

        if (firstFragment != null) {
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(containerID,firstFragment).commit();
        }
    }
}
