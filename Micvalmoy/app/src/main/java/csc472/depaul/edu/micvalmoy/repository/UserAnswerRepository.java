package csc472.depaul.edu.micvalmoy.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import csc472.depaul.edu.micvalmoy.dao.UserAnswerDao;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;

import csc472.depaul.edu.micvalmoy.entity.UserAnswer;


public class UserAnswerRepository implements Repository<UserAnswer>{

    private AppDatabase appDatabase;

    public UserAnswerDao userAnswerDao;

    public UserAnswerRepository(Context context, AppDatabase appDatabase) {
        this.appDatabase  = appDatabase;
        this.userAnswerDao  = appDatabase.UserAnswerDao();
    }

    private static UserAnswerRepository instance;
    public static UserAnswerRepository getInstance(Context context, AppDatabase appDatabase) {
        if (instance == null) {
            synchronized (UserAnswerRepository.class) {
                if (instance == null) {
                    instance = new UserAnswerRepository(context,appDatabase);
                }
            }
        }
        return instance;
    }


    @Override
    public LiveData<UserAnswer> fetchById(Long id) {
        return userAnswerDao.fetchById(id);
    }

    @Override
    public LiveData<List<UserAnswer>> fetchAll() {
        return userAnswerDao.fetchAll();
    }

    /**
     * Takes in a UserAnswer
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<Long> insert(final UserAnswer useranswer) {
        final MutableLiveData<Long> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<UserAnswer, Void, Long>() {
            @Override
            protected Long doInBackground(UserAnswer... useranswer) {
                return userAnswerDao.insert(useranswer[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                mutableLiveData.postValue(aLong);
            }
        }.execute(useranswer);

        return mutableLiveData;
    }

    /**
     * takes in a UserAnswer
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<List<Long>> insertAll(final UserAnswer... useranswers) {
        final MutableLiveData<List<Long>> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<UserAnswer, Void, List<Long> >() {

            @Override
            protected List<Long> doInBackground(UserAnswer... useranswer) {
                return userAnswerDao.insertAll(useranswer);
            }

            @Override
            protected void onPostExecute(List<Long> aLongs) {
                super.onPostExecute(aLongs);
                mutableLiveData.postValue(aLongs);
            }
        }.execute(useranswers);

        return mutableLiveData;
    }


    /**
     * takes in a UserAnswer
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> update(final UserAnswer useranswer) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<UserAnswer, Void, Integer>() {

            @Override
            protected Integer doInBackground(UserAnswer... params) {
                return userAnswerDao.update(params[0]);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(useranswer);

        return mutableLiveData;
    }


    /**
     * takes in a UserAnswer
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> updateAll(final UserAnswer... useranswers) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<UserAnswer, Void, Integer>() {

            @Override
            protected Integer doInBackground(UserAnswer... useranswer) {
                return userAnswerDao.updateAll(useranswer);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(useranswers);

        return mutableLiveData;
    }


    //----------------------------------------------------------------------------

    /**
     * takes in a UserAnswer
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> delete(final UserAnswer useranswer) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<UserAnswer, Void, Integer>() {

            @Override
            protected Integer doInBackground(UserAnswer... params) {
                return userAnswerDao.delete(useranswer);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(useranswer);

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
                return userAnswerDao.deleteById(Id);
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
                return userAnswerDao.deleteAll();
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
