package csc472.depaul.edu.micvalmoy.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Exam;


/**
 * Using Ellipsis to Accept Variable Number of Arguments (Var args)
 * https://www.quora.com/Why-does-3-dot-symbol-is-used-in-varargs-Java
 * Accepts a comma separated list of Quiz objects
 * updateAll(q1,q2)
 */



@Dao
public interface ExamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(Exam exam);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(Exam... exam);

    @Update
    public int update(Exam exam);

    @Update
    public int updateAll(Exam... exam);

    @Delete
    public int delete(Exam exam);


    //-------------------------------------------------

    @Delete
    int deleteAll(Exam... exams);


    @Query("DELETE FROM exams where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM exams where id in (:ids)")
    public int deleteByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM exams")
    List<Exam> getAll();

    @Query("SELECT * FROM exams")
    public LiveData<List<Exam>> fetchAll();


    @Query("SELECT * FROM exams WHERE id =:id")
    public LiveData<Exam> fetchById(Long id);


    @Query("SELECT * FROM exams WHERE id IN (:ids)")
    public LiveData<List<Exam>> fetchByIds(Long... ids);

    //-------------------------------------------------
    @Query("SELECT COUNT(*) FROM exams")
    public int getCount();



/*

    @Query("SELECT ed.id, ed.name ed.description, cat.id as category_id, cat.name as category_name " +
            "FROM user_answers ed " +
            "JOIN user_answers ua on (ed.user_id = ed.id) " +
            "JOIN categories cat on (cat.id = q.category_id) ")
    public List<UserAnswerWithCategory> getCategoryuser_answers();

    @Query("SELECT  * " +
            "FROM user_answers q " +
            "JOIN quiz q on (q.quiz_id = ed.id) " +
            "JOIN categories cat on (cat.id = q.category_id) " +
            "WHERE ed.id = :quizId")
    public UserAnswerWithCategory getCategoryUserAnswer(Long quizId);
*/
}


