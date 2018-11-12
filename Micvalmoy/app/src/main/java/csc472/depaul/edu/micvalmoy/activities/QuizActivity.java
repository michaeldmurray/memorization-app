package csc472.depaul.edu.micvalmoy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toolbar;

import csc472.depaul.edu.micvalmoy.R;

public class QuizActivity extends DescendantActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Set up body
        final Button takeQuizButton = findViewById(R.id.button_take_quiz);
        if (takeQuizButton != null) { takeQuizButton.setOnClickListener(onClickTake);}

        final Button practiceButton = findViewById(R.id.button_practice);
        if (practiceButton != null) { practiceButton.setOnClickListener(onClickPractice);}

        final Button attemptsButton = findViewById(R.id.button_attempts);
        if (attemptsButton != null) {
            Log.d("Quiz", "onClickListener is set for Results button");
            attemptsButton.setOnClickListener(onClickAttempts);}

        final TableRow classRow = findViewById(R.id.row_class);
        if (classRow != null) { takeQuizButton.setOnClickListener(onClickClass);}

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quiz,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_class:
                // TODO set up "change class" behavior
                return true;
            case R.id.delete_quiz:
                // TODO set up "delete quiz" behavior
//                Log.d("Quiz","Delete clicked");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private View.OnClickListener onClickTake = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO launch Quiz mode
        }
    };

    private View.OnClickListener onClickPractice = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO launch Practice mode
        }
    };

    private View.OnClickListener onClickAttempts = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("Quiz","Results clicked");
            Intent intent = new Intent(view.getContext(), QuizResultsActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onClickClass = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //TODO launch Class view
        }
    };
}
