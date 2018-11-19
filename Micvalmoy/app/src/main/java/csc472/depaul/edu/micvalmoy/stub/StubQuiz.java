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
        questionList.add(StubQuestion.getQuestion(1));
        questionList.add(StubQuestion.getQuestion(2));
        questionList.add(StubQuestion.getQuestion(3));
        return questionList;
    }
}
