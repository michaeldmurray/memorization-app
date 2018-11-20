package csc472.depaul.edu.micvalmoy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.question.QuestionActivity;
import csc472.depaul.edu.micvalmoy.stub.StubQuiz;
import csc472.depaul.edu.micvalmoy.tools.IntentUtil;
import timber.log.Timber;

public class QuizActivity extends DescendantActivity {
    Quiz quiz;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Enable timber for logging
        if (BuildConfig.DEBUG || BuildConfig.FLAVOR.equals("withlog")) {
            //Remove all planted trees.
            Timber.uprootAll();

            Timber.plant(new TimberDebugTree());
        }
        //TODO: Add a crash analytics tool for production
        //**************************************************************
        if (savedInstanceState != null) { return;}

        setContentView(R.layout.activity_quiz);

        // get Quiz from ID
        long quizId = getIntent().getLongExtra(IntentUtil.EXTRA_QUIZ_ID,0);
        if (quizId == 0) {
            Log.e("QuestionActivity", "Missing question ID");
            return;
        }
        quiz = StubQuiz.getQuiz(quizId);

        // Set up header
        this.setTitle(quiz.getName());
        setQuestionCount(quiz.getQuestionList().size());


        // Set up body
        final Button takeQuizButton = findViewById(R.id.button_take_quiz);
        if (takeQuizButton != null) { takeQuizButton.setOnClickListener(onClickTake);}

        //NOT supported for MVP
//        final Button practiceButton = findViewById(R.id.button_practice);
//        if (practiceButton != null) { practiceButton.setOnClickListener(onClickPractice);}

        final Button attemptsButton = findViewById(R.id.button_attempts);
        if (attemptsButton != null) {
            Log.d("Quiz", "onClickListener is set for Results button");
            attemptsButton.setOnClickListener(onClickAttempts);}

        // NOT supported for MVP
//        final TableRow classRow = findViewById(R.id.row_class);
//        if (classRow != null) { takeQuizButton.setOnClickListener(onClickClass);}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quiz,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // NOT supported for MVP
//            case R.id.change_class:
//                return true;
            case R.id.delete_quiz:
                // TODO set up "delete quiz" behavior
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private View.OnClickListener onClickTake = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), QuestionActivity.class);
            intent.putExtra(IntentUtil.EXTRA_QUIZ_ID, quiz.getId());
            intent.putExtra(IntentUtil.EXTRA_QUIZ_MODE, IntentUtil.EXTRA_QUIZ_MODE_EXAM);
            startActivity(intent);
            //TODO launch Quiz mode
        }
    };

    //NOT supported for MVP
//    private View.OnClickListener onClickPractice = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            // launch Practice mode
//        }
//    };

    private View.OnClickListener onClickAttempts = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("Quiz","Results clicked");
            Intent intent = new Intent(view.getContext(), QuizResultsActivity.class);
            startActivity(intent);
        }
    };

    // NOT supported for MVP
//    private View.OnClickListener onClickClass = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//        }
//    };


    private void setQuestionCount(int count) {
        TextView textView = findViewById(R.id.value_questions_count);
        if (textView != null) {
            textView.setText(String.valueOf(count));
        }
    }
}
