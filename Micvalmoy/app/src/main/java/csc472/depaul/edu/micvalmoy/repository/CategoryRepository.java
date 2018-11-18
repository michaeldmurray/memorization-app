package csc472.depaul.edu.micvalmoy.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import csc472.depaul.edu.micvalmoy.dao.CategoryDao;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;

import csc472.depaul.edu.micvalmoy.entity.Category;


public class CategoryRepository implements Repository<Category>{

    private AppDatabase appDatabase;

    public CategoryDao categoryDao;

    public CategoryRepository(Context context, AppDatabase appDatabase) {
        this.appDatabase  = appDatabase;
        this.categoryDao  = appDatabase.CategoryDao();
    }

    private static CategoryRepository instance;
    public static CategoryRepository getInstance(Context context, AppDatabase appDatabase) {
        if (instance == null) {
            synchronized (CategoryRepository.class) {
                if (instance == null) {
                    instance = new CategoryRepository(context,appDatabase);
                }
            }
        }
        return instance;
    }

    @Override
    public LiveData<Category> fetchById(Long id) {
        return categoryDao.fetchById(id);
    }

    @Override
    public LiveData<List<Category>> fetchAll() {
        return categoryDao.fetchAll();
    }

    /**
     * Takes in a Category
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<Long> insert(final Category category) {
        final MutableLiveData<Long> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Category, Void, Long>() {
            @Override
            protected Long doInBackground(Category... category) {
                return categoryDao.insert(category[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                mutableLiveData.postValue(aLong);
            }
        }.execute(category);

        return mutableLiveData;
    }

    /**
     * takes in a Category
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<List<Long>> insertAll(final Category... categories) {
        final MutableLiveData<List<Long>> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Category, Void, List<Long> >() {

            @Override
            protected List<Long> doInBackground(Category... category) {
                return categoryDao.insertAll(category);
            }

            @Override
            protected void onPostExecute(List<Long> aLongs) {
                super.onPostExecute(aLongs);
                mutableLiveData.postValue(aLongs);
            }
        }.execute(categories);

        return mutableLiveData;
    }


    /**
     * takes in a Category
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> update(final Category category) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Category, Void, Integer>() {

            @Override
            protected Integer doInBackground(Category... params) {
                return categoryDao.update(params[0]);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(category);

        return mutableLiveData;
    }


    /**
     * takes in a Category
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> updateAll(final Category... categories) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Category, Void, Integer>() {

            @Override
            protected Integer doInBackground(Category... category) {
                return categoryDao.updateAll(category);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(categories);

        return mutableLiveData;
    }


    //----------------------------------------------------------------------------

    /**
     * takes in a Category
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> delete(final Category category) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Category, Void, Integer>() {

            @Override
            protected Integer doInBackground(Category... params) {
                return categoryDao.delete(category);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(category);

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
                return categoryDao.deleteById(Id);
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
                return categoryDao.deleteAll();
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
