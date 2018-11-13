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

import csc472.depaul.edu.micvalmoy.mock.LiveDataTestUtil;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.mock.FakeQuizData;

import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class QuizDaoTest {

    private QuizDao quizDao;
    private CategoryDao categoryDao;
    private AppDatabase db;

    FakeQuizData fakeQuizData;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();



    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        quizDao     = db.QuizDao();
        categoryDao = db.CategoryDao();

        fakeQuizData = new FakeQuizData();
    }

    @After
    public void tearDown() throws Exception {
        db.close();
    }

    @Test
    public void shouldCreateDatabase() {
        assertNotNull(db);
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
        List < Quiz > quizList = LiveDataTestUtil.getValue(quizDao.fetchAll());
        assertTrue(quizList.isEmpty());
    }

    @Test
    public void onInsertingQuizzes_checkIf_RowCountIsCorrect() throws InterruptedException {
        List < Quiz > quizList = fakeQuizData.getFakeQuizzes(5);
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
        List < Quiz > quizList = fakeQuizData.getFakeQuizzes(5);
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