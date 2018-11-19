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

import csc472.depaul.edu.micvalmoy.dao.QuestionDao;
import csc472.depaul.edu.micvalmoy.dao.QuizCategoryDao;
import csc472.depaul.edu.micvalmoy.dao.QuizCourseDao;
import csc472.depaul.edu.micvalmoy.dao.QuizDao;
import csc472.depaul.edu.micvalmoy.dao.UserAnswerDao;
import csc472.depaul.edu.micvalmoy.dao.UserDao;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;

import csc472.depaul.edu.micvalmoy.entity.Quiz;
import timber.log.Timber;


public class QuizRepository implements Repository<Quiz>{
    private AppDatabase appDatabase;

    QuizDao quizDao;

    /*
    public CategoryDao categoryDao;
    public CourseDao courseDao;
    public UserDao userDao;
    public QuestionDao questionDao;
    public QuestionAnswerOptionDao questionAnswerOptionDao;
    public QuestionCorrectAnswerDao questionCorrectAnswerDao;

    public QuizCategoryDao quizCategoryDao;
    public QuizCourseDao quizCourseDao;
    public UserAnswerDao userAnswerDao;
    public ExamDao examDao;
    */


    public QuizRepository(Context context, AppDatabase appDatabase) {
        this.appDatabase         = appDatabase;
        quizDao                  = appDatabase.QuizDao();

        /*
        courseDao                = appDatabase.CourseDao();
        categoryDao              = appDatabase.CategoryDao();


        questionDao              = appDatabase.QuestionDao();
        questionAnswerOptionDao  = appDatabase.QuestionAnswerOptionDao();

        quizCategoryDao          = appDatabase.QuizCategoryDao();
        quizCourseDao            = appDatabase.QuizCourseDao();

        userDao                  = appDatabase.UserDao();
        userAnswerDao            = appDatabase.UserAnswerDao();
        examDao                  = appDatabase.ExamDao();
        */
    }


    private static QuizRepository instance;
    public static QuizRepository getInstance(Context context, AppDatabase appDatabase) {
        if (instance == null) {
            synchronized (QuizRepository.class) {
                if (instance == null) {
                    instance = new QuizRepository(context,appDatabase);
                }
            }
        }
        return instance;
    }

    @Override
    public LiveData<Quiz> fetchById(Long id) {
        return quizDao.fetchById(id);
    }

    //@Override
    public LiveData<List<Quiz>> fetchAll() {
        return quizDao.fetchAll();
    }

    /**
     * Takes in a Quiz
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<Long> insert(final Quiz quiz) {
        final MutableLiveData<Long> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Quiz, Void, Long>() {
            @Override
            protected Long doInBackground(Quiz... quiz) {
                return quizDao.insert(quiz[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                mutableLiveData.postValue(aLong);
            }
        }.execute(quiz);

        return mutableLiveData;
    }

    /**
     * takes in a Quiz
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<List<Long>> insertAll(final Quiz... quizzes) {
        final MutableLiveData<List<Long>> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Quiz, Void, List<Long> >() {

            @Override
            protected List<Long> doInBackground(Quiz... quiz) {
                return quizDao.insertAll(quiz);
            }

            @Override
            protected void onPostExecute(List<Long> aLongs) {
                super.onPostExecute(aLongs);
                mutableLiveData.postValue(aLongs);
            }
        }.execute(quizzes);

        return mutableLiveData;
    }


    /**
     * takes in a Quiz
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> update(final Quiz quiz) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Quiz, Void, Integer>() {

            @Override
            protected Integer doInBackground(Quiz... params) {
                return quizDao.update(params[0]);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(quiz);

        return mutableLiveData;
    }


    /**
     * takes in a Quiz
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> updateAll(final Quiz... quizzes) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Quiz, Void, Integer>() {

            @Override
            protected Integer doInBackground(Quiz... quiz) {
                return quizDao.updateAll(quiz);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(quizzes);

        return mutableLiveData;
    }


    //----------------------------------------------------------------------------

    /**
     * takes in a Quiz
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> delete(final Quiz quiz) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Quiz, Void, Integer>() {

            @Override
            protected Integer doInBackground(Quiz... params) {
                return quizDao.delete(params[0]);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(quiz);

        return mutableLiveData;
    }

    //----------------------------------------------------------------------------

    /**
     * takes in a Long
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> deleteById(final Long Id) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                return quizDao.deleteById(Id);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute();

        return mutableLiveData;
    }


    /**
     * takes in a string
     */
    @Override
    public LiveData<Integer> deleteAll() {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Void, Void, Integer>() {

            @Override
            protected Integer doInBackground(Void... voids) {
                return quizDao.deleteAll();
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute();

        return mutableLiveData;
    }
}
