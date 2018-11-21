package csc472.depaul.edu.micvalmoy.stub;

import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.Quiz;

public class StubQuiz {

    public static Quiz getQuiz(long quizId) {
        Quiz quiz = new Quiz();
        quiz.setId(quizId);
        quiz.setName("Quiz " + quizId);
        quiz.setDescription("Quiz description example");
        quiz.setQuestionList(buildQuestionList());
        return quiz;
    }

    public static List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(getQuiz(1));
        quizzes.add(getQuiz(2));
        quizzes.add(getQuiz(3));
        quizzes.add(getQuiz(4));
        quizzes.add(getQuiz(5));
        quizzes.add(getQuiz(6));
        quizzes.add(getQuiz(7));
        return quizzes;
    }

    //I think these should just be question IDs, which can be built when needed.
    private static List<Question> buildQuestionList() {
        List<Question> questionList = new ArrayList<>();
        questionList.add(StubQuestion.getQuestion((long) 1));
        questionList.add(StubQuestion.getQuestion((long) 2));
        questionList.add(StubQuestion.getQuestion((long) 3));
        return questionList;
    }
}
