package csc472.depaul.edu.micvalmoy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TableRow;

import csc472.depaul.edu.micvalmoy.R;

public class QuizResultActivity extends DescendantActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        // TODO replace these with programmatic rows
        final TableRow questionRow1 = findViewById(R.id.row_quiz_result);
        if (questionRow1 != null) { questionRow1.setOnClickListener(onClickQuestionRow);}

        final TableRow questionRow2 = findViewById(R.id.row_quiz_result2);
        if (questionRow2 != null) { questionRow2.setOnClickListener(onClickQuestionRow);}

        final TableRow questionRow3 = findViewById(R.id.row_quiz_result3);
        if (questionRow3 != null) { questionRow3.setOnClickListener(onClickQuestionRow);}

        final TableRow questionRow4 = findViewById(R.id.row_quiz_result4);
        if (questionRow4 != null) { questionRow4.setOnClickListener(onClickQuestionRow);}
    }

    private View.OnClickListener onClickQuestionRow = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), QuestionActivity.class);
            Bundle questionBundle = new Bundle();
            questionBundle.putString(QuestionActivity.MODE,QuestionActivity.mode.RESULT.name());
            questionBundle.putInt(QuestionActivity.ID,1);
            startActivity(intent);
        }
    };
}
