package csc472.depaul.edu.micvalmoy.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import csc472.depaul.edu.micvalmoy.dao.ExamDao;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;

import csc472.depaul.edu.micvalmoy.entity.Exam;


public class ExamRepository implements Repository<Exam>{

    private AppDatabase appDatabase;

    public ExamDao examDao;

    public ExamRepository(Context context, AppDatabase appDatabase) {
        this.appDatabase  = appDatabase;
        this.examDao  = appDatabase.ExamDao();
    }

    private static ExamRepository instance;
    public static ExamRepository getInstance(Context context, AppDatabase appDatabase) {
        if (instance == null) {
            synchronized (ExamRepository.class) {
                if (instance == null) {
                    instance = new ExamRepository(context,appDatabase);
                }
            }
        }
        return instance;
    }

    @Override
    public LiveData<Exam> fetchById(Long id) {
        return examDao.fetchById(id);
    }

    @Override
    public LiveData<List<Exam>> fetchAll() {
        return examDao.fetchAll();
    }

    /**
     * Takes in a Exam
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<Long> insert(final Exam exam) {
        final MutableLiveData<Long> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Exam, Void, Long>() {
            @Override
            protected Long doInBackground(Exam... exam) {
                return examDao.insert(exam[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                mutableLiveData.postValue(aLong);
            }
        }.execute(exam);

        return mutableLiveData;
    }

    /**
     * takes in a Exam
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<List<Long>> insertAll(final Exam... exams) {
        final MutableLiveData<List<Long>> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Exam, Void, List<Long> >() {

            @Override
            protected List<Long> doInBackground(Exam... exam) {
                return examDao.insertAll(exam);
            }

            @Override
            protected void onPostExecute(List<Long> aLongs) {
                super.onPostExecute(aLongs);
                mutableLiveData.postValue(aLongs);
            }
        }.execute(exams);

        return mutableLiveData;
    }


    /**
     * takes in a Exam
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> update(final Exam exam) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Exam, Void, Integer>() {

            @Override
            protected Integer doInBackground(Exam... params) {
                return examDao.update(params[0]);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(exam);

        return mutableLiveData;
    }


    /**
     * takes in a Exam
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> updateAll(final Exam... exams) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Exam, Void, Integer>() {

            @Override
            protected Integer doInBackground(Exam... exam) {
                return examDao.updateAll(exam);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(exams);

        return mutableLiveData;
    }


    //----------------------------------------------------------------------------

    /**
     * takes in a Exam
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> delete(final Exam exam) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Exam, Void, Integer>() {

            @Override
            protected Integer doInBackground(Exam... params) {
                return examDao.delete(exam);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(exam);

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
                return examDao.deleteById(Id);
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
                return examDao.deleteAll();
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
