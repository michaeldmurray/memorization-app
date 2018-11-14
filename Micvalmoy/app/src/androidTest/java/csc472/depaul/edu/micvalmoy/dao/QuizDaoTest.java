package csc472.depaul.edu.micvalmoy.dao;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.QuizQuestion;
import csc472.depaul.edu.micvalmoy.entity.QuizWithQuestion;
import csc472.depaul.edu.micvalmoy.mock.LiveDataTestUtil;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.mock.FakeQuizData;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class QuizDaoTest {
    private AppDatabase appDatabase;
    FakeQuizData fakeQuizData;

    CategoryDao categoryDao;
    CourseDao courseDao;
    UserDao userDao;
    QuestionDao questionDao;
    QuestionAnswerOptionDao questionAnswerOptionDao;
    QuestionCorrectAnswerDao questionCorrectAnswerDao;
    QuizDao                  quizDao;
    QuizCategoryDao quizCategoryDao;
    QuizCourseDao quizCourseDao;
    QuizQuestionDao quizQuestionDao;
    UserAnswerDao userAnswerDao;
    ExamDao examDao;




    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();



    @Before
    public void setUp() throws Exception {
        fakeQuizData = new FakeQuizData();

        Context context = InstrumentationRegistry.getTargetContext();
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        courseDao                = appDatabase.CourseDao();
        categoryDao              = appDatabase.CategoryDao();


        questionDao              = appDatabase.QuestionDao();
        questionAnswerOptionDao  = appDatabase.QuestionAnswerOptionDao();
        questionCorrectAnswerDao = appDatabase.QuestionCorrectAnswerDao();

        quizDao                  = appDatabase.QuizDao();
        quizCategoryDao          = appDatabase.QuizCategoryDao();
        quizCourseDao            = appDatabase.QuizCourseDao();
        quizQuestionDao          = appDatabase.QuizQuestionDao();

        userDao                  = appDatabase.UserDao();
        userAnswerDao            = appDatabase.UserAnswerDao();
        examDao            = appDatabase.ExamDao();



    }

    @After
    public void tearDown() throws Exception {
        appDatabase.close();
    }

    @Test
    public void shouldCreateDatabase() {
        assertNotNull(appDatabase);
    }

    @Test
    public void shouldCreateDao() {
        assertNotNull(quizDao);
        assertNotNull(categoryDao);
    }

    //Testing live data
    //------------------------------------------------------------------
    @Test
    public void onFetchingQuizzes_shouldGetEmptyList_IfTable_IsEmpty() throws InterruptedException {
        List < Quiz > quizList = (List < Quiz >)LiveDataTestUtil.getValue(quizDao.fetchAll());
        assertTrue(quizList.isEmpty());
    }

    @Test
    public void onInsertingQuizzes_checkIf_RowCountIsCorrect() throws InterruptedException {
        List < Quiz > quizList = fakeQuizData.getFakeQuizList(5);
        for (Quiz quiz: quizList) {
            quizDao.insert(quiz);
        }
        assertEquals(5, LiveDataTestUtil.getValue(quizDao.fetchAll()).size());
    }

    @Test
    public void onUpdatingAQuiz_checkIf_UpdateHappensCorrectly() throws InterruptedException {
        Quiz quiz = fakeQuizData.fetchFakeQuiz();
        quizDao.insert(quiz);
        quiz.setName(fakeQuizData.FAKE_UPDATED_TITLE);
        quizDao.update(quiz);
        assertEquals(1, LiveDataTestUtil.getValue(quizDao.fetchAll()).size());
        assertEquals(fakeQuizData.FAKE_UPDATED_TITLE,
                LiveDataTestUtil.getValue(quizDao.fetchById(quiz.getId())).getName());
    }

    @Test
    public void onQuizDeletion_CheckIf_QuizIsDeletedFromTable() throws InterruptedException {
        List < Quiz > quizList = fakeQuizData.getFakeQuizList(5);
        for (Quiz quiz: quizList) {
            quizDao.insert(quiz);
        }

        quizDao.delete(quizList.get(2));
        assertNull(LiveDataTestUtil.getValue(quizDao.fetchById(quizList.get(2).getId())));
    }
    //------------------------------------------------------------------







    @Test
    public void shouldInsertQuiz() {
        Quiz quiz = new Quiz();
        quiz.setName("name1");
        quiz.setDescription("description1");
        quizDao.insertAll(quiz);
        List<Quiz> quizzes = quizDao.getAll();

        assertEquals(1, quizzes.size());
        Quiz dbQuiz = quizzes.get(0);
        assertEquals(quiz.getName(), dbQuiz.getName());
        assertEquals(quiz.getDescription(), dbQuiz.getDescription());
        assertEquals(new Long(1), dbQuiz.getId());
    }

    @Test
    public void shouldInsertQuizAutoGenerateID() {
        Quiz quiz = new Quiz();
        quiz.setName("name auto generated quiz");
        quiz.setDescription("description1");
        Long quizID = quizDao.insert(quiz);

        assertNotNull(quizID);
    }

    @Test
    public void add_a_new_quiz_then_add_questions_for_that_quiz() {
        Quiz quiz = new Quiz();
        quiz.setName("name auto generated quiz");
        quiz.setDescription("add_new_quiz_add_questions_for_quiz");

        //Insert into database table "quizzes" - add Quiz into database
        Long quizID = quizDao.insert(quiz);

        //Create questions
        List<Question> fakeQuestions = fakeQuizData.getFakeQuestionList(5);

        assertNull(fakeQuestions.get(0).getId());

        //Convert List to Array so that the list can be added to the database
        Question[] arr_questions = fakeQuestions.toArray(new Question[fakeQuestions.size()]);

        //Insert into database table "questions" - Add questions
        List<Long> insertedQuestionIds =  questionDao.insertAll(arr_questions);

        assertThat("original question has null id before it is passed to the database, there the database created the id", fakeQuestions.get(0).getId(), is(not(insertedQuestionIds.get(0))));



        //Query database- get list of the inserted questions back from the database, uses getByIds to get the raw data instead of fetchById() which returns LiveData
        List<Question> dbQuestions = questionDao.getByIds(insertedQuestionIds.toArray(new Long[insertedQuestionIds.size()]));


        //------------------------------------------------------------
        //Link all the questions with the quiz

        int index = 0;
        for (Long dbQuestionId: insertedQuestionIds) {

            //Insert into database- link each question to quiz
            QuizQuestion quizQuestion = new QuizQuestion();
            quizQuestion.setQuizId(quizID);
            quizQuestion.setQuestionId(dbQuestionId);
            quizQuestion.setSortIndex(index);
            quizQuestion.setEnabled(true); //quiz is enabled by default

            quizQuestionDao.insert(quizQuestion);

            //Doing a loop to add the new ids to the original list of questions
            fakeQuestions.get(index).setId(dbQuestionId);

            index++;
        }

        assertEquals(dbQuestions.get(0).getText(), fakeQuestions.get(0).getText());

        //** get the quiz and its question from the data
        //------------------------------------------------------------
        List<QuizWithQuestion> quizWithQuestion = quizDao.getQuizQuestions();

        //Test to make sure data added successfully
        assertEquals(quizID, quizWithQuestion.get(0).getId());
    }

    @Test
    public void shouldInsertTwoQuizzes() {
        Quiz quiz1 = new Quiz();
        quiz1.setName("name1");
        quiz1.setDescription("description1");

        Quiz quiz2 = new Quiz();
        quiz2.setName("name1");
        quiz2.setDescription("description2");

        quizDao.insertAll(quiz1, quiz2);
        List<Quiz> quizzes = quizDao.getAll();

        assertEquals(2, quizzes.size());
        Quiz dbQuiz1 = quizzes.get(0);
        assertEquals(quiz1.getName(), dbQuiz1.getName());
        assertEquals(quiz1.getDescription(), dbQuiz1.getDescription());
        assertEquals(new Long(1), dbQuiz1.getId());

        Quiz dbQuiz2 = quizzes.get(1);
        assertEquals(quiz2.getName(), dbQuiz2.getName());
        assertEquals(quiz2.getDescription(), dbQuiz2.getDescription());
        assertEquals(new Long(2), dbQuiz2.getId());
    }

    @Test
    public void shouldInsertQuizWithId() {
        Quiz quiz = new Quiz();
        quiz.setId(new Long(99));
        quizDao.insertAll(quiz);
        List<Quiz> quizzes = quizDao.getAll();
        assertEquals(1, quizzes.size());
        Quiz dbQuiz = quizzes.get(0);
        assertEquals(quiz.getId(), dbQuiz.getId());
    }

    @Test
    public void shouldDeleteQuiz() {
        Quiz quiz = new Quiz();
        quiz.setName("name1");
        quizDao.insertAll(quiz);
        List<Quiz> quizzes = quizDao.getAll();

        assertEquals(1, quizzes.size());
        quizDao.deleteAll(quizzes.get(0));
        quizzes = quizDao.getAll();
        assertEquals(0, quizzes.size());
    }

    @Test
    public void shouldUpdateQuiz() {
        Quiz quiz = new Quiz();
        quiz.setName("name1");
        quizDao.insertAll(quiz);
        List<Quiz> quizzes = quizDao.getAll();

        assertEquals(1, quizzes.size());
        Quiz dbQuiz = quizzes.get(0);
        assertEquals(quiz.getName(), dbQuiz.getName());

        dbQuiz.setName("name2");
        quizDao.updateAll(dbQuiz);
        quizzes = quizDao.getAll();
        assertEquals(1, quizzes.size());
        Quiz dbQuiz2 = quizzes.get(0);
        assertEquals(dbQuiz.getName(), dbQuiz2.getName());
    }

}