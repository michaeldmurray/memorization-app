package csc472.depaul.edu.micvalmoy.dao;

/**
 * @author mrichards
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Course;


/**
 * Using Ellipsis to Accept Variable Number of Arguments (Var args)
 * https://www.quora.com/Why-does-3-dot-symbol-is-used-in-varargs-Java
 * Accepts a comma separated list of Quiz objects
 * updateAll(q1,q2)
 */



@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(Course course);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(Course... course);

    @Update
    public int update(Course course);

    @Update
    public int updateAll(Course... course);

    @Delete
    public int delete(Course course);


    //-------------------------------------------------

    @Delete
    int deleteAll(Course... courses);


    @Query("DELETE FROM courses where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM courses where id in (:ids)")
    public int deleteByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM courses")
    List<Course> getAll();

    @Query("SELECT * FROM courses")
    public LiveData<List<Course>> fetchAll();


    @Query("SELECT * FROM courses WHERE id =:id")
    public LiveData<Course> fetchById(Long id);


    @Query("SELECT * FROM courses WHERE id IN (:ids)")
    public LiveData<List<Course>> fetchByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM courses ORDER BY name asc")
    public LiveData<List<Course>> fetchAllSortByName();


    @Query("SELECT COUNT(*) FROM courses")
    public int getCount();

    @Query("SELECT * FROM courses WHERE name =:id COLLATE NOCASE")
    public Course getCourseByName(String id);


}