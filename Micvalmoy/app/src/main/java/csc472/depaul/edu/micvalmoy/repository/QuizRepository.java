package csc472.depaul.edu.micvalmoy.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import csc472.depaul.edu.micvalmoy.db.AppDatabase;

import csc472.depaul.edu.micvalmoy.entity.QuizWithCategory;
import csc472.depaul.edu.micvalmoy.entity.Category;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.dao.QuizDao;


public class QuizRepository {
    private AppDatabase appDatabase;
    // Note the use of MutableLiveData, this allows changes to be made

    QuizDao quizDao;

    public QuizRepository(Context context) {
        appDatabase = AppDatabase.getDatabase(context);

        quizDao = appDatabase.QuizDao();
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
