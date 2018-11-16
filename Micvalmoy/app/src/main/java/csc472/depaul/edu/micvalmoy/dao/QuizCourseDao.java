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

@Dao
public interface QuizCourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(QuizCourse quizCourse);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long>  insertAll(List<QuizCourse> quizCourse);

    @Update
    public int update(QuizCourse quizCourse);

    @Update
    public int updateAll(QuizCourse... quizCourse);

    @Delete
    public void delete(QuizCourse quizCourse);


    @Query("SELECT * FROM quiz_courses")
    public LiveData<List<QuizCourse>> fetchAll();

    @Query("SELECT COUNT(*) FROM quiz_courses")
    public int getCount();


    //------------------------------

    @Query("DELETE FROM quiz_courses where quiz_id=:id")
    public int deleteByQuizId(Long id);

    @Query("DELETE FROM quiz_courses where course_id in (:ids)")
    public int deleteByCourseIds(Long... ids);

    @Query("SELECT * FROM quiz_courses WHERE quiz_id =:id")
    public LiveData<QuizCourse> fetchByQuizId(Long id);


    @Query("SELECT * FROM quiz_courses WHERE course_id =:id")
    public LiveData<QuizCourse> fetchByCourseId(Long id);

}