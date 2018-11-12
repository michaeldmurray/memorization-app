package csc472.depaul.edu.micvalmoy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import csc472.depaul.edu.micvalmoy.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button classesButton = findViewById(R.id.go_to_classes);
        final Button quizzesButton = findViewById(R.id.go_to_quizzes);

        if (classesButton != null) { classesButton.setOnClickListener(onClickClasses); }
        if (quizzesButton != null) { quizzesButton.setOnClickListener(onClickQuizzes); }

        // TODO remove buttons below when no longer needed
        final Button tempButton = findViewById(R.id.temp_quiz_search_button);
        if (tempButton != null) { tempButton.setOnClickListener(onClickTemp); }

        final Button quizButton = findViewById(R.id.temp_quiz_button);
        if (quizButton != null) { quizButton.setOnClickListener(onClickQuiz); }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //To add additional options to the overflow settings, add items in the correct menu xml file
        // and handle them in the onOptionsItemSelected below.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener onClickClasses = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ClassesActivity.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onClickQuizzes = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), QuizizzActivity.class);
            startActivity(intent);
        }
    };

    //TODO remove listeners below when no longer needed
    private View.OnClickListener onClickTemp = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), QuizSearch.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener onClickQuiz = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), QuizActivity.class);
            startActivity(intent);
        }
    };
}
