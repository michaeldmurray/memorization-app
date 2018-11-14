package csc472.depaul.edu.micvalmoy.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import csc472.depaul.edu.micvalmoy.dao.CategoryDao;
import csc472.depaul.edu.micvalmoy.dao.CourseDao;
import csc472.depaul.edu.micvalmoy.dao.ExamDao;
import csc472.depaul.edu.micvalmoy.dao.QuestionAnswerOptionDao;
import csc472.depaul.edu.micvalmoy.dao.QuestionCorrectAnswerDao;
import csc472.depaul.edu.micvalmoy.dao.QuestionDao;
import csc472.depaul.edu.micvalmoy.dao.QuizCategoryDao;
import csc472.depaul.edu.micvalmoy.dao.QuizCourseDao;
import csc472.depaul.edu.micvalmoy.dao.QuizQuestionDao;
import csc472.depaul.edu.micvalmoy.dao.UserAnswerDao;
import csc472.depaul.edu.micvalmoy.dao.UserDao;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;

import csc472.depaul.edu.micvalmoy.entity.QuizWithCategory;
import csc472.depaul.edu.micvalmoy.entity.Category;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.dao.QuizDao;


public class QuizRepository {
    private AppDatabase appDatabase;
    // Note the use of MutableLiveData, this allows changes to be made

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

    public QuizRepository(Context context) {
        appDatabase = AppDatabase.getDatabase(context);

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
        examDao                  = appDatabase.ExamDao();

    }

    private static QuizRepository instance;
    public static QuizRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (QuizRepository.class) {
                if (instance == null) {
                    instance = new QuizRepository(context);
                }
            }
        }
        return instance;
    }



    public  LiveData<Long> insertQuiz(final Quiz quiz) {
        final MutableLiveData<Long> longMutableLiveData = new MutableLiveData<>();
        new AsyncTask<Void, Void, Long>() {
            @Override
            protected Long doInBackground(Void... voids) {

                return appDatabase.QuizDao().insert(quiz);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                longMutableLiveData.setValue(aLong);
            }
        }.execute();

        return longMutableLiveData;
    }

    public LiveData<Integer> deleteQuizByIds(final Long... id) {
        final MutableLiveData<Integer> longMutableLiveData = new MutableLiveData<>();

        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                return appDatabase.QuizDao().deleteByIds(id);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                longMutableLiveData.setValue(aInteger);
            }
        }.execute();

        return longMutableLiveData;
    }

    public LiveData<Integer> updateQuiz(final Quiz quiz) {
        final MutableLiveData<Integer> integerMutableLiveData = new MutableLiveData<>();
        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                return appDatabase.QuizDao().update(quiz);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                integerMutableLiveData.setValue(aInteger);
            }
        }.execute();
        return integerMutableLiveData;
    }

    //------EDIT QUIZ --------------------------------------------------------------
    //------------------------------------------------------------------------------

    public LiveData<List<Quiz>> getQuizzes( ) {
        return appDatabase.QuizDao().fetchAll();
    }
    public LiveData<Quiz>  getQuizById(Long quizId) {
        return appDatabase.QuizDao().fetchById(quizId);
    }

    public LiveData<List<Category>> getCategories( ) {
        return appDatabase.CategoryDao().fetchAll();
    }

    public void saveQuiz(QuizWithCategory quizWithCategory){
        Quiz quiz = new Quiz();
        quiz.setName("Dummy quiz 1");
        quiz.setDescription(" quiz 1 This quiz has dummy data, delete it soon-");

        long quizId = appDatabase.QuizDao().insert(quiz);
        //Let's add some dummy data to the database.
    }



}
