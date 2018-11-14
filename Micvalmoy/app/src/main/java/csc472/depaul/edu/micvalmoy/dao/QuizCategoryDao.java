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

@Dao
public interface QuizCategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(QuizCategory quizCategory);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuizCategory> quizCategory);

    @Update
    public int update(QuizCategory quizCategory);

    @Update
    public int updateAll(QuizCategory... quizCategory);

    @Delete
    public void delete(QuizCategory quizCategory);


    @Query("SELECT * FROM quiz_categories")
    public LiveData<List<QuizCategory>> fetchAll();

    @Query("SELECT COUNT(*) FROM quiz_categories")
    public int getCount();


    //------------------------------

    @Query("DELETE FROM quiz_categories where quiz_id=:id")
    public int deleteByQuizId(Long id);

    @Query("DELETE FROM quiz_categories where category_id in (:ids)")
    public int deleteByCategoryIds(Long... ids);

    @Query("SELECT * FROM quiz_categories WHERE quiz_id =:id")
    public LiveData<QuizCategory> fetchByQuizId(Long id);


    @Query("SELECT * FROM quiz_categories WHERE category_id =:id")
    public LiveData<QuizCategory> fetchByCategoryId(Long id);

}