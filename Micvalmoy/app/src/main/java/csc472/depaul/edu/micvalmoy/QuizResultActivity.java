package csc472.depaul.edu.micvalmoy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TableRow;

import csc472.depaul.edu.micvalmoy.question.QuestionActivity;
import csc472.depaul.edu.micvalmoy.tools.IntentUtil;

public class QuizResultActivity extends DescendantActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        this.setTitle(R.string.quiz_result_title);

        // TODO replace these with programmatic rows
        final TableRow questionRow1 = findViewById(R.id.row_quiz_result);
        if (questionRow1 != null) { questionRow1.setOnClickListener(buildOnClickQuestionRow((long) 1));}

        final TableRow questionRow2 = findViewById(R.id.row_quiz_result2);
        if (questionRow2 != null) { questionRow2.setOnClickListener(buildOnClickQuestionRow((long) 2));}

        final TableRow questionRow3 = findViewById(R.id.row_quiz_result3);
        if (questionRow3 != null) { questionRow3.setOnClickListener(buildOnClickQuestionRow((long) 3));}
    }

    private View.OnClickListener buildOnClickQuestionRow(final long questionID) {
        View.OnClickListener onClickQuestionRow = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), QuestionActivity.class);
                intent.putExtra(IntentUtil.EXTRA_QUIZ_ID,(long)7);
                intent.putExtra(IntentUtil.EXTRA_QUIZ_MODE,IntentUtil.EXTRA_QUIZ_MODE_RESULT);
                // TODO replace this with a working communication of questionID. This seems to error.
                intent.putExtra(IntentUtil.EXTRA_QUESTION_ID, questionID);
                startActivity(intent);
            }
        };
        return onClickQuestionRow;
    }
}