package csc472.depaul.edu.micvalmoy.question;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

import csc472.depaul.edu.micvalmoy.R;
import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;
import csc472.depaul.edu.micvalmoy.stub.StubQuestion;
import csc472.depaul.edu.micvalmoy.tools.IntentUtil;

public class MultipleChoiceFragment extends Fragment {
    Question question;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_multiple_choice, container, false);
        setQuestion();
        setOptions(view);
        return view;
    }

    private void setQuestion() {
        long questionId = getArguments().getLong(IntentUtil.EXTRA_QUESTION_ID);
        if (questionId < 0) {
        } else {
            question = StubQuestion.getQuestion(questionId);
        }
    }

    private void setOptions(View view) {
        if (question == null) {
        } else {
            List<QuestionAnswerOption> optionList = question.getOptions();
            RadioGroup optionContainer = view.findViewById(R.id.mc_options);
            for (int i = 0; i < optionList.size(); i++) {
                RadioButton button = new RadioButton(optionContainer.getContext());
                button.setText(optionList.get(i).getText());
                button.setSaveEnabled(true);
                button.setTextIsSelectable(true);
                optionContainer.addView(button);
            }
        }
    }
}
