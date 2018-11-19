package csc472.depaul.edu.micvalmoy.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import csc472.depaul.edu.micvalmoy.dao.CourseDao;
import csc472.depaul.edu.micvalmoy.db.AppDatabase;

import csc472.depaul.edu.micvalmoy.entity.Course;


public class CourseRepository implements Repository<Course>{

    private AppDatabase appDatabase;

    public CourseDao courseDao;

    public CourseRepository(Context context, AppDatabase appDatabase) {
        this.appDatabase  = appDatabase;
        this.courseDao  = appDatabase.CourseDao();
    }

    private static CourseRepository instance;
    public static CourseRepository getInstance(Context context, AppDatabase appDatabase) {
        if (instance == null) {
            synchronized (CourseRepository.class) {
                if (instance == null) {
                    instance = new CourseRepository(context,appDatabase);
                }
            }
        }
        return instance;
    }


    @Override
    public LiveData<Course> fetchById(Long id) {
        return courseDao.fetchById(id);
    }

    @Override
    public LiveData<List<Course>> fetchAll() {
        return courseDao.fetchAll();
    }

    /**
     * Takes in a Course
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<Long> insert(final Course course) {
        final MutableLiveData<Long> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Course, Void, Long>() {
            @Override
            protected Long doInBackground(Course... course) {
                return courseDao.insert(course[0]);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                super.onPostExecute(aLong);
                mutableLiveData.postValue(aLong);
            }
        }.execute(course);

        return mutableLiveData;
    }

    /**
     * takes in a Course
     * returns live data --- LiveData<Long>
     */
    @Override
    public LiveData<List<Long>> insertAll(final Course... courses) {
        final MutableLiveData<List<Long>> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Course, Void, List<Long> >() {

            @Override
            protected List<Long> doInBackground(Course... course) {
                return courseDao.insertAll(course);
            }

            @Override
            protected void onPostExecute(List<Long> aLongs) {
                super.onPostExecute(aLongs);
                mutableLiveData.postValue(aLongs);
            }
        }.execute(courses);

        return mutableLiveData;
    }


    /**
     * takes in a Course
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> update(final Course course) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Course, Void, Integer>() {

            @Override
            protected Integer doInBackground(Course... params) {
                return courseDao.update(params[0]);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(course);

        return mutableLiveData;
    }


    /**
     * takes in a Course
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> updateAll(final Course... courses) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Course, Void, Integer>() {

            @Override
            protected Integer doInBackground(Course... course) {
                return courseDao.updateAll(course);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(courses);

        return mutableLiveData;
    }


    //----------------------------------------------------------------------------

    /**
     * takes in a Course
     * returns live data --- LiveData<Integer>
     */
    @Override
    public LiveData<Integer> delete(final Course course) {
        final MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        new AsyncTask<Course, Void, Integer>() {

            @Override
            protected Integer doInBackground(Course... params) {
                return courseDao.delete(course);
            }

            @Override
            protected void onPostExecute(Integer aInteger) {
                super.onPostExecute(aInteger);
                mutableLiveData.postValue(aInteger);
            }
        }.execute(course);

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
                return courseDao.deleteById(Id);
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
                return courseDao.deleteAll();
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
