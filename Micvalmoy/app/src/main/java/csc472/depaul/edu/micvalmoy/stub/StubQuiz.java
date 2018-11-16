package csc472.depaul.edu.micvalmoy.stub;

import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.Quiz;

public class StubQuiz {

    public static Quiz getQuiz(long quizId) {
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        quiz.setName("Stub Quiz Name");
        quiz.setDescription("stub quiz description");
        quiz.setQuestionList(buildQuestionList());
        return quiz;
    }

    //I think these should just be question IDs, which can be built when needed.
    private static List<Question> buildQuestionList() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(getQuestion(1));
        questionList.add(getQuestion(2));
        questionList.add(getQuestion(3));
        return questionList;
    }

    //I think Questions should have a list of option IDs to fetch option data from DB?
    private static Question getQuestion(int id) {
        Question question = new Question();
        switch (id) {
            case 1:
                question.setId((long)id);
                question.setType("truefalse");
                question.setText("this is a true or false question");
                break;
            case 2:
                question.setId((long)id);
                question.setType("multiple");
                question.setText("this is a multiple choice question with one correct answer");
                break;
            case 3:
                question.setId((long)id);
                question.setType("multiple");
                question.setText("this is a multiple choice question with two correct answers");
                break;
            default:
                question.setText("something has gone horribly wrong");
        }
        return question;
    }
}
