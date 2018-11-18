package csc472.depaul.edu.micvalmoy.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import csc472.depaul.edu.micvalmoy.dao.UserDao;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;

import csc472.depaul.edu.micvalmoy.entity.User;


public class UserRepository implements Repository<User>{

    private AppDatabase appDatabase;

    public UserDao userDao;

    public UserRepository(Context context, AppDatabase appDatabase) {
        this.appDatabase  = appDatabase;
        userDao  = appDatabase.UserDao();
    }

    private static UserRepository instance;
    public static UserRepository getInstance(Context context, AppDatabase appDatabase) {
        if (instance == null) {
            synchronized (UserRepository.class) {
                if (instance == null) {
                    instance = new UserRepository(context,appDatabase);
                }
            }
        }
        return instance;
    }

    @Override
    public LiveData<User> fetchById(Long id) {
        return userDao.fetchById(id);
    }

    @Override
    public LiveData<List<User>> fetchAll() {
        return userDao.fetchAll();
    }

    /**
     * Takes in a User
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<Long> insert(final User user) {
        final MutableLiveData<Long> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<User, Void, Long>() {
            @Override
            protected Long doInBackground(User... user) {
                return userDao.insert(user[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                mutableLiveData.postValue(aLong);
            }
        }.execute(user);

        return mutableLiveData;
    }

    /**
     * takes in a User
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<List<Long>> insertAll(final User... users) {
        final MutableLiveData<List<Long>> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<User, Void, List<Long> >() {

            @Override
            protected List<Long> doInBackground(User... user) {
                return userDao.insertAll(user);
            }

            @Override
            protected void onPostExecute(List<Long> aLongs) {
                super.onPostExecute(aLongs);
                mutableLiveData.postValue(aLongs);
            }
        }.execute(users);

        return mutableLiveData;
    }


    /**
     * takes in a User
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> update(final User user) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<User, Void, Integer>() {

            @Override
            protected Integer doInBackground(User... params) {
                return userDao.update(params[0]);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(user);

        return mutableLiveData;
    }


    /**
     * takes in a User
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> updateAll(final User... users) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<User, Void, Integer>() {

            @Override
            protected Integer doInBackground(User... user) {
                return userDao.updateAll(user);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(users);

        return mutableLiveData;
    }


    //----------------------------------------------------------------------------

    /**
     * takes in a User
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> delete(final User user) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<User, Void, Integer>() {

            @Override
            protected Integer doInBackground(User... params) {
                return userDao.delete(user);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(user);

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
                return userDao.deleteById(Id);
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
                return userDao.deleteAll();
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