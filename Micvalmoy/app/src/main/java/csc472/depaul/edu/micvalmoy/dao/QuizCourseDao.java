package csc472.depaul.edu.micvalmoy.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.QuizCourse;


/**
 * Using Ellipsis to Accept Variable Number of Arguments (Var args)
 * https://www.quora.com/Why-does-3-dot-symbol-is-used-in-varargs-Java
 * Accepts a comma separated list of Quiz objects
 * updateAll(q1,q2)
 */



@Dao
public interface QuizCourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(QuizCourse quiz_course);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(QuizCourse... quiz_course);

    @Update
    public int update(QuizCourse quiz_course);

    @Update
    public int updateAll(QuizCourse... quiz_course);

    @Delete
    public int delete(QuizCourse quiz_course);


    //-------------------------------------------------

    @Delete
    int deleteAll(QuizCourse... quiz_courses);


    @Query("DELETE FROM quiz_courses where quiz_id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM quiz_courses where quiz_id in (:ids)")
    public int deleteByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM quiz_courses")
    List<QuizCourse> getAll();

    @Query("SELECT * FROM quiz_courses")
    public LiveData<List<QuizCourse>> fetchAll();


    @Query("SELECT * FROM quiz_courses WHERE quiz_id =:id")
    public LiveData<QuizCourse> fetchById(Long id);


    @Query("SELECT * FROM quiz_courses WHERE quiz_id IN (:ids)")
    public LiveData<List<QuizCourse>> fetchByIds(Long... ids);

    //-------------------------------------------------
    @Query("SELECT COUNT(*) FROM quiz_courses")
    public int getCount();

    //------------------------------

    @Query("DELETE FROM quiz_courses where quiz_id=:id")
    public int deleteByQuizId(Long id);

    @Query("DELETE FROM quiz_courses where course_id in (:ids)")
    public int deleteByCategoryIds(Long... ids);

    @Query("SELECT * FROM quiz_courses WHERE quiz_id =:id")
    public QuizCourse fetchByQuizId(Long id);


    @Query("SELECT * FROM quiz_courses WHERE course_id =:id")
    public QuizCourse fetchByCourseId(Long id);

}