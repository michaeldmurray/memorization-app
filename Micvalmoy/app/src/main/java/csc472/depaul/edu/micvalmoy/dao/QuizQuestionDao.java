package csc472.depaul.edu.micvalmoy.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.QuizQuestion;

@Dao
public interface QuizQuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(QuizQuestion quizQuestion);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuizQuestion> quizQuestion);

    @Update
    public int update(QuizQuestion quizQuestion);

    @Update
    public int updateAll(QuizQuestion... quizQuestion);

    @Delete
    public void delete(QuizQuestion quizQuestion);


    @Query("SELECT * FROM quiz_questions")
    public LiveData<List<QuizQuestion>> fetchAll();

    @Query("SELECT COUNT(*) FROM quiz_questions")
    public int getCount();


    //------------------------------

    @Query("DELETE FROM quiz_questions where quiz_id=:id")
    public int deleteByQuizId(Long id);

    @Query("DELETE FROM quiz_questions where question_id in (:ids)")
    public int deleteByQuestionIds(Long... ids);

    @Query("SELECT * FROM quiz_questions WHERE quiz_id =:id")
    public LiveData<QuizQuestion> fetchByQuizId(Long id);


    @Query("SELECT * FROM quiz_questions WHERE question_id =:id")
    public LiveData<QuizQuestion> fetchByQuestionId(Long id);

}