package csc472.depaul.edu.micvalmoy.stub;

import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;

public class StubQuestion {
    public static Question getQuestion(int id) {
        Question question = new Question();
        question.setId((long)id);
        switch (id) {
            case 1:
                question.setType("truefalse");
                question.setText("this is a true or false question");
                break;
            case 2:
                question.setType("multiple");
                question.setText("this is a multiple choice question with one correct answer");
                break;
            case 3:
                question.setType("multiple");
                question.setText("this is a multiple choice question with two correct answers");
                break;
            default:
                question.setText("something has gone horribly wrong");
        }
        return question;
    }

    public static List<QuestionAnswerOption> getOptions(int questionId) {
        List<QuestionAnswerOption> options = new ArrayList<>();
        switch(questionId) {
            case 1:
                QuestionAnswerOption option1 = new QuestionAnswerOption();
                option1.setId((long) 1);
                option1.setQuestionId((long) questionId);
                option1.setAnswer(true);
                option1.setText("true");

                QuestionAnswerOption option2 = new QuestionAnswerOption();
                option2.setId((long) 2);
                option2.setQuestionId((long) questionId);
                option2.setText("false");

                options.add(option1);
                options.add(option2);
                break;
            case 2:
                QuestionAnswerOption option3 = new QuestionAnswerOption();
                option3.setId((long) 3);
                option3.setQuestionId((long) questionId);
                option3.setAnswer(true);
                option3.setText("Option A");

                QuestionAnswerOption option4 = new QuestionAnswerOption();
                option4.setId((long) 4);
                option4.setQuestionId((long) questionId);
                option4.setText("Option B");

                QuestionAnswerOption option5 = new QuestionAnswerOption();
                option5.setId((long) 5);
                option5.setQuestionId((long) questionId);
                option5.setText("Option C");

                options.add(option3);
                options.add(option4);
                options.add(option5);
                break;
            case 3:
                QuestionAnswerOption option6 = new QuestionAnswerOption();
                option6.setId((long) 6);
                option6.setQuestionId((long) questionId);
                option6.setAnswer(true);
                option6.setText("Option D");

                QuestionAnswerOption option7 = new QuestionAnswerOption();
                option7.setId((long) 7);
                option7.setQuestionId((long) questionId);
                option7.setAnswer(true);
                option7.setText("Option E");

                QuestionAnswerOption option8 = new QuestionAnswerOption();
                option8.setId((long) 8);
                option8.setQuestionId((long) questionId);
                option8.setText("Option F");

                options.add(option6);
                options.add(option7);
                options.add(option8);
                break;
        }

        return options;
    }
}
