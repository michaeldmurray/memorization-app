package csc472.depaul.edu.micvalmoy.mock;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Category;
import csc472.depaul.edu.micvalmoy.entity.Course;
import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.entity.User;


public class FakeQuizData {

    private String FAKE_TITLE = "Fake title ";
    private String FAKE_DESC = "Fake description ";
    private String FAKE_NAME = "Fake name ";
    private String FAKE_QUESTION = "Fake Question";
    private String FAKE_HINT = "Fake hint";

    public  String FAKE_UPDATED_TITLE = "FAKE UPDATED TITLE";

    int quizCounter = 0;

    public List<Quiz> getFakeQuizzes(int size) {
        List<Quiz> quizList = new ArrayList<>();
        for(int i = 1; i <= size; i++ ) {
            Quiz quiz = new Quiz();
            quiz.setName(FAKE_NAME + " - Quiz " + i);
            quiz.setDescription(FAKE_DESC + " - Quiz " + i + System.currentTimeMillis());

            quizList.add(quiz);
        }
        return quizList;
    }


    public List<Category> getFakeCategories(int size) {
        List<Category> categoryList = new ArrayList<>();
        for(int i = 1; i <= size; i++ ) {
            Category category = new Category();
            category.setName(FAKE_NAME + " - Category " + i);
            categoryList.add(category);
        }
        return categoryList;
    }

    public List<Course> getFakeCourses(int size) {
        List<Course> courseList = new ArrayList<>();
        for(int i = 1; i <= size; i++ ) {
            Course course = new Course();
            course.setName(FAKE_NAME + " - Course " + i);
            courseList.add(course);
        }
        return courseList;
    }

    public List<User> getFakeUsers(int size) {
        List<User> userList = new ArrayList<>();
        for(int i = 1; i <= size; i++ ) {
            User user = new User();
            user.setUsername(FAKE_NAME + " - User " + i);
            user.setPassword( "pass_" + i + System.currentTimeMillis());
            userList.add(user);
        }
        return userList;
    }

    public List<Question> getFakeQuestion(int size) {
        List<Question> questionList = new ArrayList<>();
        for(int i = 1; i <= size; i++ ) {
            Question question = new Question();
            question.setText(FAKE_TITLE + " - Question " + i);
            question.setHint(FAKE_HINT + " - Question " + i);
            question.setType("Fake type" + " - Question " + i);
            question.setNonce(i + "sdfxdfqwqesfdfg--" + i);

            Long date = System.currentTimeMillis();
            question.setCreatedAt(new Date(date));
            question.setUpdatedAt(new Date(date));

            questionList.add(question);
        }
        return questionList;
    }


    public Quiz fetchFakeQuiz() {
        Quiz quiz = new Quiz();

        quiz.setName(FAKE_TITLE + String.valueOf(quizCounter));
        quiz.setDescription(FAKE_DESC + System.currentTimeMillis());

        quizCounter++;
        return quiz;
    }
}