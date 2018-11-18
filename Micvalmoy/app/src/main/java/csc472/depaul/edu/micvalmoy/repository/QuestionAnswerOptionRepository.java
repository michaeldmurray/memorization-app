package csc472.depaul.edu.micvalmoy.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import csc472.depaul.edu.micvalmoy.dao.QuestionAnswerOptionDao;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;
import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;


public class QuestionAnswerOptionRepository implements Repository<QuestionAnswerOption>{

    private AppDatabase appDatabase;

    public QuestionAnswerOptionDao QuestionAnswerOptionDao;

    public QuestionAnswerOptionRepository(Context context, AppDatabase appDatabase) {
        this.appDatabase  = appDatabase;
        this.QuestionAnswerOptionDao  = appDatabase.QuestionAnswerOptionDao();
    }

    private static QuestionAnswerOptionRepository instance;
    public static QuestionAnswerOptionRepository getInstance(Context context, AppDatabase appDatabase) {
        if (instance == null) {
            synchronized (QuestionAnswerOptionRepository.class) {
                if (instance == null) {
                    instance = new QuestionAnswerOptionRepository(context,appDatabase);
                }
            }
        }
        return instance;
    }

    @Override
    public LiveData<QuestionAnswerOption> fetchById(Long id) {
        return QuestionAnswerOptionDao.fetchById(id);
    }

    @Override
    public LiveData<List<QuestionAnswerOption>> fetchAll() {
        return QuestionAnswerOptionDao.fetchAll();
    }

    /**
     * Takes in a QuestionAnswerOption
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<Long> insert(final QuestionAnswerOption option) {
        final MutableLiveData<Long> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<QuestionAnswerOption, Void, Long>() {
            @Override
            protected Long doInBackground(QuestionAnswerOption... option) {
                return QuestionAnswerOptionDao.insert(option[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                mutableLiveData.postValue(aLong);
            }
        }.execute(option);

        return mutableLiveData;
    }

    /**
     * takes in a QuestionAnswerOption
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<List<Long>> insertAll(final QuestionAnswerOption... options) {
        final MutableLiveData<List<Long>> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<QuestionAnswerOption, Void, List<Long> >() {

            @Override
            protected List<Long> doInBackground(QuestionAnswerOption... option) {
                return QuestionAnswerOptionDao.insertAll(options);
            }

            @Override
            protected void onPostExecute(List<Long> aLongs) {
                super.onPostExecute(aLongs);
                mutableLiveData.postValue(aLongs);
            }
        }.execute(options);

        return mutableLiveData;
    }


    /**
     * takes in a QuestionAnswerOption
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> update(final QuestionAnswerOption option) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<QuestionAnswerOption, Void, Integer>() {

            @Override
            protected Integer doInBackground(QuestionAnswerOption... params) {
                return QuestionAnswerOptionDao.update(params[0]);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(option);

        return mutableLiveData;
    }


    /**
     * takes in a QuestionAnswerOption
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> updateAll(final QuestionAnswerOption... options) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<QuestionAnswerOption, Void, Integer>() {

            @Override
            protected Integer doInBackground(QuestionAnswerOption... option) {
                return QuestionAnswerOptionDao.updateAll(option);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(options);

        return mutableLiveData;
    }


    //----------------------------------------------------------------------------

    /**
     * takes in a QuestionAnswerOption
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> delete(final QuestionAnswerOption option) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<QuestionAnswerOption, Void, Integer>() {

            @Override
            protected Integer doInBackground(QuestionAnswerOption... params) {
                return QuestionAnswerOptionDao.delete(option);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(option);

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
                return QuestionAnswerOptionDao.deleteById(Id);
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
                return QuestionAnswerOptionDao.deleteAll();
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