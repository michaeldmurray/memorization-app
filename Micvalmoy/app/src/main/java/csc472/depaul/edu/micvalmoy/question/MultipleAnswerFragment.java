package csc472.depaul.edu.micvalmoy.question;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import csc472.depaul.edu.micvalmoy.R;
import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;
import csc472.depaul.edu.micvalmoy.stub.StubQuestion;
import csc472.depaul.edu.micvalmoy.tools.IntentUtil;

public class MultipleAnswerFragment extends Fragment {
    Question question;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_multiple_answer, container, false);
        setQuestion();
        setOptions(view);
        return view;
    }

    private void setQuestion() {
        Log.d("QUESTION_MA_FRAGMENT","progress point: B");
        long questionId = getArguments().getLong(IntentUtil.EXTRA_QUESTION_ID);
        if (questionId < 0) {
            Log.e("QUESTION_MA_FRAGMENT", "Missing question ID");
        } else {
            question = StubQuestion.getQuestion(questionId);
            Log.d("QUESTION_MA_FRAGMENT","progress point: C");
        }
    }

    private void setOptions(View view) {
        Log.d("QUESTION_MA_FRAGMENT","progress point: D");
        if (question == null) {
            Log.e("QUESTION_MA_FRAGMENT", "Missing question ref");
        } else {
            List<QuestionAnswerOption> optionList = question.getOptions();
            Log.d("QUESTION_MA_FRAGMENT","question: " + question.getText());
            Log.d("QUESTION_MA_FRAGMENT","question length: " + optionList.size());
            RadioGroup optionContainer = view.findViewById(R.id.ma_options);
            for (int i = 0; i < optionList.size(); i++) {
                CheckBox button = new CheckBox(optionContainer.getContext());
                button.setText(optionList.get(i).getText());
                button.setSaveEnabled(true);
                button.setTextIsSelectable(true);
                optionContainer.addView(button);
                Log.d("QUESTION_MA_FRAGMENT","option added: " + optionList.get(i).getText());
            }
            Log.d("QUESTION_MA_FRAGMENT","progress point: E");
        }
    }
}
