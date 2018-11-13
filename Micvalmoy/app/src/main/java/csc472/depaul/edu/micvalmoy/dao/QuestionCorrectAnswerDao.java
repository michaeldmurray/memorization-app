package csc472.depaul.edu.micvalmoy.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.QuestionCorrectAnswer;

@Dao
public interface QuestionCorrectAnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(QuestionCorrectAnswer questionCorrectAnswer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<QuestionCorrectAnswer> questionCorrectAnswer);

    @Update
    public int update(QuestionCorrectAnswer questionCorrectAnswer);

    @Update
    public int updateAll(QuestionCorrectAnswer... questionCorrectAnswer);

    @Delete
    public void delete(QuestionCorrectAnswer questionCorrectAnswer);


    @Query("SELECT * FROM question_correct_answers")
    public LiveData<List<QuestionCorrectAnswer>> fetchAll();

    @Query("SELECT COUNT(*) FROM question_correct_answers")
    public int getCount();


    //------------------------------

    @Query("DELETE FROM question_correct_answers where option_id=:id")
    public int deleteByOptionId(Long id);

    @Query("DELETE FROM question_correct_answers where question_id in (:ids)")
    public int deleteByQuestionIds(Long... ids);

    @Query("SELECT * FROM question_correct_answers WHERE question_id =:id")
    public LiveData<QuestionCorrectAnswer> fetchByQuestionId(Long id);


    @Query("SELECT * FROM question_correct_answers WHERE option_id =:id")
    public LiveData<QuestionCorrectAnswer> fetchByOptionId(Long id);

}