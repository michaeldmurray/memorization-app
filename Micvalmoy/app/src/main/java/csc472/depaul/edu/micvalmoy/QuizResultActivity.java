package csc472.depaul.edu.micvalmoy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TableRow;

import csc472.depaul.edu.micvalmoy.question.QuestionActivity;

public class QuizResultActivity extends DescendantActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        // TODO replace these with programmatic rows
        final TableRow questionRow1 = findViewById(R.id.row_quiz_result);
        if (questionRow1 != null) { questionRow1.setOnClickListener(buildOnClickQuestionRow(1));}

        final TableRow questionRow2 = findViewById(R.id.row_quiz_result2);
        if (questionRow2 != null) { questionRow2.setOnClickListener(buildOnClickQuestionRow(2));}

        final TableRow questionRow3 = findViewById(R.id.row_quiz_result3);
        if (questionRow3 != null) { questionRow3.setOnClickListener(buildOnClickQuestionRow(3));}

        final TableRow questionRow4 = findViewById(R.id.row_quiz_result4);
        if (questionRow4 != null) { questionRow4.setOnClickListener(buildOnClickQuestionRow(4));}
    }

    private View.OnClickListener buildOnClickQuestionRow(final int questionID) {
        View.OnClickListener onClickQuestionRow = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), QuestionActivity.class);
                Bundle questionBundle = new Bundle();
                questionBundle.putString(QuestionActivity.MODE, QuestionActivity.Mode.RESULT.name());
                questionBundle.putInt(QuestionActivity.ID, questionID);
                intent.putExtras(questionBundle);
                startActivity(intent);
            }
        };
        return onClickQuestionRow;
    }
}
