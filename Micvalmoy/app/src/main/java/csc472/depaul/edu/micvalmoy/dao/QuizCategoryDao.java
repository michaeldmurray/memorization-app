package csc472.depaul.edu.micvalmoy.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.QuizCategory;


/**
 * Using Ellipsis to Accept Variable Number of Arguments (Var args)
 * https://www.quora.com/Why-does-3-dot-symbol-is-used-in-varargs-Java
 * Accepts a comma separated list of Quiz objects
 * updateAll(q1,q2)
 */



@Dao
public interface QuizCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(QuizCategory quiz_category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(QuizCategory... quiz_category);

    @Update
    public int update(QuizCategory quiz_category);

    @Update
    public int updateAll(QuizCategory... quiz_category);

    @Delete
    public int delete(QuizCategory quiz_category);


    //-------------------------------------------------

    @Delete
    int deleteAll(QuizCategory... quiz_categories);


    @Query("DELETE FROM quiz_categories where quiz_id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM quiz_categories where quiz_id in (:ids)")
    public int deleteByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM quiz_categories")
    List<QuizCategory> getAll();

    @Query("SELECT * FROM quiz_categories")
    public LiveData<List<QuizCategory>> fetchAll();


    @Query("SELECT * FROM quiz_categories WHERE quiz_id =:id")
    public LiveData<QuizCategory> fetchById(Long id);


    @Query("SELECT * FROM quiz_categories WHERE quiz_id IN (:ids)")
    public LiveData<List<QuizCategory>> fetchByIds(Long... ids);

    //-------------------------------------------------
    @Query("SELECT COUNT(*) FROM quiz_categories")
    public int getCount();


    //------------------------------

    @Query("DELETE FROM quiz_categories where quiz_id=:id")
    public int deleteByQuizId(Long id);

    @Query("DELETE FROM quiz_categories where category_id in (:ids)")
    public int deleteByCategoryIds(Long... ids);

    @Query("SELECT * FROM quiz_categories WHERE quiz_id =:id")
    public QuizCategory fetchByQuizId(Long id);


    @Query("SELECT * FROM quiz_categories WHERE category_id =:id")
    public QuizCategory fetchByCategoryId(Long id);

}