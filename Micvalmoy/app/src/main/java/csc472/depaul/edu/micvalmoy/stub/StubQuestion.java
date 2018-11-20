package csc472.depaul.edu.micvalmoy.stub;

import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;

public class StubQuestion {

    public static final String TRUE_FALSE = "truefalse";
    public static final String MULTI_CHOICE = "multiplechoice";
    public static final String MULTI_ANSWER = "multipleanswer";


    public static Question getQuestion(long id) {
        Question question = new Question();
        question.setId(id);
        question.setOptions(getOptions(id));
        switch ((int) id) {
            case 1:
                question.setType(TRUE_FALSE);
                question.setText("Android development is challenging and fun.");
                break;
            case 2:
                question.setType(MULTI_CHOICE);
                question.setText("Choose the answer which best describes Android development.");
                break;
            case 3:
                question.setType(MULTI_ANSWER);
                question.setText("What types of products can be built on Android's OS?");
                break;
            default:
                question.setText("something has gone horribly wrong");
        }
        return question;
    }

    public static List<QuestionAnswerOption> getOptions(long questionId) {
        List<QuestionAnswerOption> options = new ArrayList<>();
        switch((int) questionId) {
            case 1:
                QuestionAnswerOption option1 = new QuestionAnswerOption();
                option1.setId((long) 1);
                option1.setQuestionId(questionId);
                option1.setAnswer(true);
                option1.setText("true");

                QuestionAnswerOption option2 = new QuestionAnswerOption();
                option2.setId((long) 2);
                option2.setQuestionId(questionId);
                option2.setText("false");

                options.add(option1);
                options.add(option2);
                break;
            case 2:
                QuestionAnswerOption option3 = new QuestionAnswerOption();
                option3.setId((long) 3);
                option3.setQuestionId(questionId);
                option3.setAnswer(true);
                option3.setText("It has wide-ranging applications");

                QuestionAnswerOption option4 = new QuestionAnswerOption();
                option4.setId((long) 4);
                option4.setQuestionId(questionId);
                option4.setText("It isn't useful");

                QuestionAnswerOption option5 = new QuestionAnswerOption();
                option5.setId((long) 5);
                option5.setQuestionId(questionId);
                option5.setText("It is trivial");

                options.add(option3);
                options.add(option4);
                options.add(option5);
                break;
            case 3:
                QuestionAnswerOption option6 = new QuestionAnswerOption();
                option6.setId((long) 6);
                option6.setQuestionId(questionId);
                option6.setAnswer(true);
                option6.setText("Automated farming management");

                QuestionAnswerOption option7 = new QuestionAnswerOption();
                option7.setId((long) 7);
                option7.setQuestionId(questionId);
                option7.setAnswer(true);
                option7.setText("In-flight video streaming");

                QuestionAnswerOption option8 = new QuestionAnswerOption();
                option8.setId((long) 8);
                option8.setQuestionId(questionId);
                option8.setText("Disney's Frozen mobile gaming");

                options.add(option6);
                options.add(option7);
                options.add(option8);
                break;
        }

        return options;
    }
}
