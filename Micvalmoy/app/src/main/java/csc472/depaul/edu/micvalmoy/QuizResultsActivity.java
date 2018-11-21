package csc472.depaul.edu.micvalmoy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TableRow;

public class QuizResultsActivity extends DescendantActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);
        this.setTitle(R.string.quiz_results_title);

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
