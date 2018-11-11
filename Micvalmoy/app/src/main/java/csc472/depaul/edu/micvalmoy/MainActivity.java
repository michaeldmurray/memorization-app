package csc472.depaul.edu.micvalmoy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button classesButton = findViewById(R.id.go_to_classes);
        final Button quizzesButton = findViewById(R.id.go_to_quizzes);

        // TODO remove button below when no longer needed
        final Button tempButton = findViewById(R.id.temp_quiz_search_button);

        if (tempButton != null) {
            tempButton.setOnClickListener(onClickTemp);
        }
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

    //TODO remove listener when no longer needed
    private View.OnClickListener onClickTemp = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), QuizSearch.class);
            startActivity(intent);
        }
    };
}
