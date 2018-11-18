package csc472.depaul.edu.micvalmoy.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import csc472.depaul.edu.micvalmoy.dao.QuestionDao;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;
import csc472.depaul.edu.micvalmoy.entity.Question;


public class QuestionRepository implements Repository<Question>{

    private AppDatabase appDatabase;

    public QuestionDao questionDao;

    public QuestionRepository(Context context, AppDatabase appDatabase) {
        this.appDatabase  = appDatabase;
        this.questionDao  = appDatabase.QuestionDao();
    }

    private static QuestionRepository instance;
    public static QuestionRepository getInstance(Context context, AppDatabase appDatabase) {
        if (instance == null) {
            synchronized (QuestionRepository.class) {
                if (instance == null) {
                    instance = new QuestionRepository(context,appDatabase);
                }
            }
        }
        return instance;
    }


    @Override
    public LiveData<Question> fetchById(Long id) {
        return questionDao.fetchById(id);
    }

    @Override
    public LiveData<List<Question>> fetchAll() {
        return questionDao.fetchAll();
    }

    /**
     * Takes in a Question
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<Long> insert(final Question question) {
        final MutableLiveData<Long> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Question, Void, Long>() {
            @Override
            protected Long doInBackground(Question... question) {
                return questionDao.insert(question[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                mutableLiveData.postValue(aLong);
            }
        }.execute(question);

        return mutableLiveData;
    }

    /**
     * takes in a Question
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<List<Long>> insertAll(final Question... questions) {
        final MutableLiveData<List<Long>> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Question, Void, List<Long> >() {

            @Override
            protected List<Long> doInBackground(Question... question) {
                return questionDao.insertAll(question);
            }

            @Override
            protected void onPostExecute(List<Long> aLongs) {
                super.onPostExecute(aLongs);
                mutableLiveData.postValue(aLongs);
            }
        }.execute(questions);

        return mutableLiveData;
    }


    /**
     * takes in a Question
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> update(final Question question) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Question, Void, Integer>() {

            @Override
            protected Integer doInBackground(Question... params) {
                return questionDao.update(params[0]);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(question);

        return mutableLiveData;
    }


    /**
     * takes in a Question
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> updateAll(final Question... questions) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Question, Void, Integer>() {

            @Override
            protected Integer doInBackground(Question... question) {
                return questionDao.updateAll(question);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(questions);

        return mutableLiveData;
    }


    //----------------------------------------------------------------------------

    /**
     * takes in a Question
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> delete(final Question question) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Question, Void, Integer>() {

            @Override
            protected Integer doInBackground(Question... params) {
                return questionDao.delete(question);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(question);

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
                return questionDao.deleteById(Id);
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
                return questionDao.deleteAll();
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
