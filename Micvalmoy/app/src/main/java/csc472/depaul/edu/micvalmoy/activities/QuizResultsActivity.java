package csc472.depaul.edu.micvalmoy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TableRow;

import csc472.depaul.edu.micvalmoy.R;

public class QuizResultsActivity extends DescendantActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        // TODO replace this with recycler for programmatic rows
        final TableRow resultsRow = findViewById(R.id.row_quiz_result);
        if (resultsRow != null) { resultsRow.setOnClickListener(onClickResultsRow);}
    }

    private View.OnClickListener onClickResultsRow = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), QuizResultActivity.class);
            startActivity(intent);
        }
    };
}
