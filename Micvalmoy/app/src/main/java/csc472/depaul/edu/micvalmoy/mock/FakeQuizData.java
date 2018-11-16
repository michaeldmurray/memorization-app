package csc472.depaul.edu.micvalmoy.mock;

import java.time.OffsetDateTime;
import java.util.ArrayList;
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
    int questionCounter = 0;  

    public List<Quiz> getFakeQuizList(int size) {
        List<Quiz> quizList = new ArrayList<>();
        for(int i = 1; i <= size; i++ ) {
            Quiz quiz = new Quiz();
            quiz.setName(FAKE_NAME + " - Quiz " + i);
            quiz.setDescription(FAKE_DESC + " - Quiz " + i + System.currentTimeMillis());

            quizList.add(quiz);
        }
        return quizList;
    }
    
    public Quiz fetchFakeQuiz() {
        Quiz quiz = new Quiz();

        quiz.setName(FAKE_TITLE + String.valueOf(quizCounter));
        quiz.setDescription(FAKE_DESC + System.currentTimeMillis());

        quizCounter++;
        return quiz;
    }

    public List<Category> getFakeCategoryList(int size) {
        List<Category> categoryList = new ArrayList<>();
        for(int i = 1; i <= size; i++ ) {
            Category category = new Category();
            category.setName(FAKE_NAME + " - Category " + i);
            categoryList.add(category);
        }
        return categoryList;
    }

    public List<Course> getFakeCourseList(int size) {
        List<Course> courseList = new ArrayList<>();
        for(int i = 1; i <= size; i++ ) {
            Course course = new Course();
            course.setName(FAKE_NAME + " - Course " + i);
            courseList.add(course);
        }
        return courseList;
    }

    public List<User> getFakeUserList(int size) {
        List<User> userList = new ArrayList<>();
        for(int i = 1; i <= size; i++ ) {
            User user = new User();
            user.setUsername(FAKE_NAME + " - User " + i);
            user.setPassword( "pass_" + i + System.currentTimeMillis());
            userList.add(user);
        }
        return userList;
    }

    public List<Question> getFakeQuestionList(int size) {
        List<Question> questionList = new ArrayList<>();
        for(int i = 1; i <= size; i++ ) {
            Question question = new Question();
            question.setText(FAKE_QUESTION + " - " + i);
            question.setHint(FAKE_HINT + " - Question " + i);
            question.setType("Fake type" + " - Question " + i);
            question.setNonce(i + "sdfxdfqwqesfdfg--" + i);

            OffsetDateTime date = OffsetDateTime.now();
            question.setCreatedAt(date);
            question.setUpdatedAt(date);

            questionList.add(question);
        }
        return questionList;
    }

    public Question fetchFakeQuestion() {
        Question question = new Question();
        question.setText(FAKE_TITLE + " - " + questionCounter);
        question.setHint(FAKE_HINT + " - Question  " + questionCounter);
        question.setType("Fake type" + " - Question " + questionCounter);
        question.setNonce("dfsdfffqwqesfdfg--" + questionCounter);

        OffsetDateTime date = OffsetDateTime.now();
        question.setCreatedAt(date);
        question.setUpdatedAt(date);


        questionCounter++;
        return question;
    }
}