package csc472.depaul.edu.micvalmoy.dao;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Question;

import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;
import csc472.depaul.edu.micvalmoy.entity.QuizWithQuestion;
import csc472.depaul.edu.micvalmoy.mock.LiveDataTestUtil;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.mock.FakeQuizData;
import csc472.depaul.edu.micvalmoy.repository.CategoryRepository;
import csc472.depaul.edu.micvalmoy.repository.CourseRepository;
import csc472.depaul.edu.micvalmoy.repository.ExamRepository;
import csc472.depaul.edu.micvalmoy.repository.QuizRepository;
import csc472.depaul.edu.micvalmoy.repository.UserAnswerRepository;
import csc472.depaul.edu.micvalmoy.repository.UserRepository;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;



//https://www.testwithspring.com/lesson/writing-nested-unit-tests/



@RunWith(AndroidJUnit4.class)
public class QuizDaoTest {
    private AppDatabase appDatabase;
    FakeQuizData fakeQuizData;

    CategoryDao categoryDao;
    CourseDao courseDao;
    UserDao userDao;
    QuestionDao questionDao;
    QuestionAnswerOptionDao questionAnswerOptionDao;
    QuizDao                  quizDao;
    QuizCategoryDao quizCategoryDao;
    QuizCourseDao quizCourseDao;

    UserAnswerDao userAnswerDao;
    ExamDao examDao;



    private CategoryRepository categoryRepository;
    private CourseRepository courseRepository;
    private UserRepository userRepository;
/*
    private QuestionRepository questionRepository;
    private QuestionAnswerOptionRepository questionAnswerOptionRepository;
*/

    private QuizRepository quizRepository;
    /*    private QuizCategoryRepository quizCategoryRepository;
        private QuizCourseRepository quizCourseRepository;
        private QuizQuestionRepository quizQuestionRepository;*/
    private UserAnswerRepository userAnswerRepository;
    private ExamRepository examRepository;











    ///////////////////////////////////////////////////////


    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() throws Exception {
        fakeQuizData = new FakeQuizData();

        //Context
        Context application = InstrumentationRegistry.getTargetContext();
        appDatabase = Room.inMemoryDatabaseBuilder(application, AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        courseDao                = appDatabase.CourseDao();
        categoryDao              = appDatabase.CategoryDao();
        questionDao              = appDatabase.QuestionDao();
        questionAnswerOptionDao  = appDatabase.QuestionAnswerOptionDao();
        quizDao                  = appDatabase.QuizDao();
        quizCategoryDao          = appDatabase.QuizCategoryDao();
        quizCourseDao            = appDatabase.QuizCourseDao();
        userDao                  = appDatabase.UserDao();
        userAnswerDao            = appDatabase.UserAnswerDao();
        examDao                  = appDatabase.ExamDao();
        /////////////////////////////////////////////////////////////////////////

        categoryRepository               = CategoryRepository.getInstance(application,appDatabase);
        courseRepository                 = CourseRepository.getInstance(application,appDatabase);
        userRepository                   = UserRepository.getInstance(application,appDatabase);


        quizRepository                   = QuizRepository.getInstance(application,appDatabase);
/*      quizCategoryRepository           = QuizCategoryRepository.getInstance(application,appDatabase);
        quizCourseRepository             = QuizCourseRepository.getInstance(application,appDatabase);

        questionRepository               = QuestionRepository.getInstance(application,appDatabase);
        questionAnswerOptionRepository   = QuestionAnswerOptionRepository.getInstance(application,appDatabase);
        */

        examRepository                   = ExamRepository.getInstance(application,appDatabase);
        userAnswerRepository             = UserAnswerRepository.getInstance(application,appDatabase);





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
        List <Quiz> quizList = (List<Quiz>)LiveDataTestUtil.getValue(quizDao.fetchAll());
        assertTrue(quizList.isEmpty());
    }

    @Test
    public void onInsertingQuizzes_checkIf_RowCountIsCorrect() throws InterruptedException {
        List < Quiz > quizList = fakeQuizData.getFakeQuizList(5);
        for (Quiz quiz: quizList) {
            quizDao.insert(quiz);
        }
        assertEquals(5, (int) LiveDataTestUtil.getValue(quizDao.fetchAll()).size());
    }

    @Test
    public void onUpdatingAQuiz_checkIf_UpdateHappensCorrectly() throws InterruptedException {
        Quiz quiz = fakeQuizData.fetchFakeQuiz();

        //Insert into database table "quizzes" - add Quiz into database
        Long quizID = quizDao.insert(quiz);

        //id is autoincrement in the database, add it to original object
        quiz.setId(quizID);
        quiz.setName(fakeQuizData.FAKE_UPDATED_TITLE);

        quizDao.update(quiz);
        assertEquals(1, (int)LiveDataTestUtil.getValue(quizDao.fetchAll()).size());
        assertEquals(fakeQuizData.FAKE_UPDATED_TITLE,
                (String)LiveDataTestUtil.getValue(quizDao.fetchById(quizID)).getName());
    }

    @Test
    public void onQuizDeletion_CheckIf_QuizIsDeletedFromTable() throws InterruptedException {
        List < Quiz > quizList = fakeQuizData.getFakeQuizList(5);
        for (Quiz quiz: quizList) {
            quizDao.insert(quiz);
        }

        quizDao.delete(quizList.get(2));
        assertNull((Quiz)LiveDataTestUtil.getValue(quizDao.fetchById(quizList.get(2).getId())));
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


    public class ContextA {
        Long quizID = new Long(5);

        @Test
        public void nestedClassTest() {
            System.out.println("nested class test" + 5);
        }
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
    //-----------------------------------------------------------------------------
    //-----------------------------------------------------------------------------
    //-----------------------------------------------------------------------------

    @Test
    public void add_a_new_quiz_then_add_a_few_questions_for_that_quiz() {

        //----Create quiz
        Quiz quiz = new Quiz();
        quiz.setName("name auto generated quiz");
        quiz.setDescription("add_new_quiz_add_questions_for_quiz");

        //Add Quiz into database - Insert into database table "quizzes" -
        Long quizID = quizDao.insert(quiz);
        assertNotNull("question id is not null",quizID);

        //Create 5 questions
        List<Question> fakeQuestions = fakeQuizData.getFakeQuestionList(5,quizID);
        assertNull(fakeQuestions.get(0).getId());


        //Convert List to Array so that the list can be added to the database
        Question[] arr_questions = fakeQuestions.toArray(new Question[fakeQuestions.size()]);

        //Insert into database table "questions" - Add questions
        List<Long> insertedQuestionIds =  questionDao.insertAll(arr_questions);

        assertThat("original question has null id before it is passed to the database, there the database created the id", fakeQuestions.get(0).getId(), is(not(insertedQuestionIds.get(0))));

        assertEquals(5, insertedQuestionIds.size());

        //Query database- get list of the inserted questions back from the database, uses getByIds to get the raw data instead of fetchById() which returns LiveData


        List<Question> dbQuestions = null;
        try {

            Long[] arr_insertedQuestionIds = insertedQuestionIds.toArray(new Long[insertedQuestionIds.size()]);

            LiveData<List<Question>> live_dbQuestions = questionDao.fetchByIds(arr_insertedQuestionIds);

            dbQuestions =  (List<Question>)LiveDataTestUtil.getValue(live_dbQuestions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //------------------------------------------------------------
        //Link all the questions with the quiz

        int index = 0;
        for (Long dbQuestionId: insertedQuestionIds) {


            //Doing a loop to add the new ids to the original list of questions
            fakeQuestions.get(index).setId(dbQuestionId);

            //Add options getQuizQuestionByQuizId

            index++;
        }

        assertEquals(dbQuestions.get(0).getText(), fakeQuestions.get(0).getText());

        List<Long> updatedQuestionIds =  questionDao.insertAll(arr_questions);

        //** get the quiz and its question from the database
        //------------------------------------------------------------
        List<QuizWithQuestion> quizWithQuestion = quizDao.getQuizQuestionByQuizId(quizID);

        //Test to make sure data added successfully - get quiz id
        assertEquals(quizID, quizWithQuestion.get(0).getQuiz().getId());

        //===================================================================
        //===================================================================
        //add answers to question
        ///set questions as the answers
        int size = 5;
        Long question_id = fakeQuestions.get(3).getId();

        List<QuestionAnswerOption> optList = fakeQuizData.getFakeFakeOpttionsList(size,question_id);

        QuestionAnswerOption opt1 = fakeQuizData.fetchFakeQuestionAnswerOption(question_id);
        opt1.setText("I am answer 1");
        opt1.setAnswer(true);

        QuestionAnswerOption opt2 = fakeQuizData.fetchFakeQuestionAnswerOption(question_id);
        opt2.setText("I am answer 2");
        opt2.setAnswer(true);

        optList.add(opt1);
        optList.add(opt2);

        for (QuestionAnswerOption option: optList) {
           Long optID =  questionAnswerOptionDao.insert(option);
           assertNotNull("checking if option id is null", optID);
        }
        //------------------------------------------------
        List<QuestionAnswerOption> allOptions = questionDao.getAllAnswerOptionsById(question_id);
        List<QuestionAnswerOption> answersFromDB = questionDao.getCorrectAnswersByQuestionId(question_id);
        assertEquals(2, answersFromDB.size());
    }

}