package csc472.depaul.edu.micvalmoy.question;
/**
 * @author mrichards
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.Format;
import java.util.List;

import csc472.depaul.edu.micvalmoy.R;
import csc472.depaul.edu.micvalmoy.DescendantActivity;
import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.stub.StubQuestion;
import csc472.depaul.edu.micvalmoy.stub.StubQuiz;
import csc472.depaul.edu.micvalmoy.tools.IntentUtil;

public class QuestionActivity extends DescendantActivity {

    private Mode mode;
    private Quiz quiz;
    private Question currentQuestion;

    public enum Mode {
        QUIZ,
        RESULT
//      MVP not supporting this mode
//        , PRACTICE
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) { return;}

        // set question ID and mode
        setMode(getIntent());
        setQuiz(getIntent());
        initializeQuestion(getIntent());

        // Setting up Activity data
        setContentView(R.layout.activity_question);
        setTitle();
        setQuestionFormatText();
        setQuestionPromptText();
        if (mode == Mode.QUIZ) {
            setBackButtonOnClick();
            setNextButtonOnClick();
            setButtonStates();
        }

        // Setting up Fragment data
        if (findViewById(R.id.answers_fragment_container) != null) {
            addAnswersFragment();
        }

        //insert question type text.
        //delegate to specific onCreates based on FORMAT
        //delegate to specific onCreates based on MODE
    }

    private void setMode(Intent intent) {
        String intentMode = intent.getStringExtra(IntentUtil.EXTRA_QUIZ_MODE);
        switch (intentMode) {
            case IntentUtil.EXTRA_QUIZ_MODE_EXAM:
                mode = Mode.QUIZ;
                break;
            case IntentUtil.EXTRA_QUIZ_MODE_RESULT:
                mode = Mode.RESULT;
                break;
//            case IntentUtil.EXTRA_QUIZ_MODE_PRACTICE:
//                mode = Mode.PRACTICE;
//                break;
            default:
                Log.e("QUESION","Intent missing quiz mode");
        }
    }

    private void setQuiz(Intent intent) {
        long quizId = intent.getLongExtra(IntentUtil.EXTRA_QUIZ_ID,-1);
        if (quizId < 0) {
            Log.e("QUESTION","Intent missing quiz id");
            return;
        }
        quiz = StubQuiz.getQuiz(quizId);
    }

    private void initializeQuestion(Intent intent) {
        if (quiz == null || mode == null) {
            Log.e("QUESTION","Question cannot be set unless quiz and mode are known");
            return;
        }
        switch (mode) {
            case QUIZ:
                if (quiz.getQuestionList().isEmpty()) {
                    Log.e("QUESTION","Quiz Question List is empty");
                    return;
                } else {
                    currentQuestion = quiz.getQuestionList().get(0);
                }
                break;
            case RESULT:
                long questionId = intent.getLongExtra(IntentUtil.EXTRA_QUESTION_ID,-1);
                if (questionId < 0) {
                    Log.e("QUESTION","Question ID must be provided in RESULT MODE");
                    return;
                }
                Question qIn = StubQuestion.getQuestion(questionId);
                if (!quiz.getQuestionList().contains(qIn)) {
                    Log.e("QUESTION","Question ID does not belong to provided Quiz");
                    return;
                }
                currentQuestion = qIn;
                Log.d("QUESTION","Obtained id from intent: " + questionId);
                break;
        }
        Log.d("QUESTION","currentquestionId: " + currentQuestion.getId());
    }

    private void setTitle() {
        if(quiz != null && currentQuestion != null && quiz.getQuestionList().contains(currentQuestion)) {
            int qNumber = quiz.getQuestionList().indexOf(currentQuestion) + 1;
            switch (mode) {
                case QUIZ:
                    this.setTitle("Question " + qNumber + " of " + quiz.getQuestionList().size());
                    break;
                case RESULT:
                    this.setTitle("Question " + qNumber);
                    break;
                default:
                    this.setTitle("Quiz Question");
            }
        } else {
            Log.e("QUESTION","Unable to set question title");
        }
    }

    private String getQuestionFormatText() {
        if (currentQuestion == null || currentQuestion.getType() == null) {
            Log.e("QUESTION","currentQuestion must be set and have type");
            return "ERROR";
        }
        switch (currentQuestion.getType()) {
            case StubQuestion.TRUE_FALSE:
                return getResources().getString(R.string.question_type_header_tf);
            case StubQuestion.MULTI_CHOICE:
                return getResources().getString(R.string.question_type_header_mc);
            case StubQuestion.MULTI_ANSWER:
                return getResources().getString(R.string.question_type_header_ma);
            default:
                Log.e("QUESTION","currentQuestion's type is unknnown: " +
                        currentQuestion.getType());
                return "ERROR";
        }
    }

    private void setQuestionFormatText() {
        TextView questionFormatText = findViewById(R.id.value_question_format);
        if (questionFormatText != null) {
            questionFormatText.setText(getQuestionFormatText());
        }
    }

    private void setQuestionPromptText() {
        TextView questionPromptText = findViewById(R.id.value_question_text);

        if (questionPromptText != null && currentQuestion != null) {
            questionPromptText.setText(currentQuestion.getText());
        } else {
            Log.e("QUESTION","QuestionPromptElement or currentQuestion missing");
        }
    }

    private void setBackButtonOnClick() {
        final Button backButton = findViewById(R.id.button_back);
        if (backButton != null) {
            backButton.setOnClickListener(onClickBack);
        }
    }

    private void setNextButtonOnClick() {
        final Button nextButton = findViewById(R.id.button_next);
        if (nextButton != null) {
            int currentQuestionIndex = quiz.getQuestionList().indexOf(currentQuestion);
            if (currentQuestionIndex >= quiz.getQuestionList().size() - 1) {
                nextButton.setOnClickListener(onClickFinish);
            } else {
                nextButton.setOnClickListener(onClickNext);
            }
        }
    }

    private void setButtonStates() {
        final Button backButton = findViewById(R.id.button_back);
        final Button nextButton = findViewById(R.id.button_next);
        int currentIndex = quiz.getQuestionList().indexOf(currentQuestion);
        if (backButton != null) {
           if (currentIndex < 1) {
               backButton.setVisibility(View.INVISIBLE);
           } else {
               backButton.setVisibility(View.VISIBLE);
           }
        }
        if (nextButton != null) {
            if (currentIndex >= quiz.getQuestionList().size() - 1) {
                nextButton.setText(getResources().getString(R.string.button_finish_label));
            } else {
                nextButton.setText(getResources().getString(R.string.button_next_label));
            }
        }
    }

    private View.OnClickListener onClickBack = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int currentQuestionIndex = quiz.getQuestionList().indexOf(currentQuestion);
            if (currentQuestionIndex > 0) {
                currentQuestion = quiz.getQuestionList().get(currentQuestionIndex - 1);
                refreshView();
            }
        }
    };

    private View.OnClickListener onClickNext = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int currentQuestionIndex = quiz.getQuestionList().indexOf(currentQuestion);
            if (currentQuestionIndex < quiz.getQuestionList().size() - 1) {
                currentQuestion = quiz.getQuestionList().get(currentQuestionIndex + 1);
                refreshView();
            }
        }
    };

    private View.OnClickListener onClickFinish = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private void addAnswersFragment() {
        Fragment firstFragment = getAnswersFragment();

        if (firstFragment != null) {
            Bundle bundle = getIntent().getExtras();
            bundle.putLong(IntentUtil.EXTRA_QUESTION_ID,currentQuestion.getId());
            firstFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.answers_fragment_container,firstFragment).commit();
        }
    }


    private void replaceAnswersFragment() {
        Fragment newFragment = getAnswersFragment();
        if (newFragment != null) {
            Bundle bundle = getIntent().getExtras();
            bundle.putLong(IntentUtil.EXTRA_QUESTION_ID,currentQuestion.getId());
            newFragment.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.answers_fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private Fragment getAnswersFragment() {
        switch(currentQuestion.getType()) {
            case StubQuestion.TRUE_FALSE:
            case StubQuestion.MULTI_CHOICE:
                return new MultipleChoiceFragment();
            case StubQuestion.MULTI_ANSWER:
                return new MultipleAnswerFragment();
//            MVP not supporting this type
//            case FILL_IN_THE_BLANK:
//                break;
            default:
                Log.e("QUESTION","Improper question type");
                return null;
        }
    }

    private void refreshView() {
        setTitle();
        setQuestionFormatText();
        setQuestionPromptText();
        setNextButtonOnClick();
        setButtonStates();
        replaceAnswersFragment();
    }
}
