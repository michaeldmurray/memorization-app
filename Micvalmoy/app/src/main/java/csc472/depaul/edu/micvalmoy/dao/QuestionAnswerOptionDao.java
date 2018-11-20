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

import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;
import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;


/**
 * Using Ellipsis to Accept Variable Number of Arguments (Var args)
 * https://www.quora.com/Why-does-3-dot-symbol-is-used-in-varargs-Java
 * Accepts a comma separated list of Quiz objects
 * updateAll(q1,q2)
 */



@Dao
public interface QuestionAnswerOptionDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(QuestionAnswerOption option);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(QuestionAnswerOption... option);

    @Update
    public int update(QuestionAnswerOption option);

    @Update
    public int updateAll(QuestionAnswerOption... option);

    @Delete
    public int delete(QuestionAnswerOption option);


    //-------------------------------------------------

    @Delete
    int deleteAll(QuestionAnswerOption... options);


    @Query("DELETE FROM question_answer_options where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM question_answer_options where id in (:ids)")
    public int deleteByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM question_answer_options")
    List<QuestionAnswerOption> getAll();

    @Query("SELECT * FROM question_answer_options")
    public LiveData<List<QuestionAnswerOption>> fetchAll();


    @Query("SELECT * FROM question_answer_options WHERE id =:id")
    public LiveData<QuestionAnswerOption> fetchById(Long id);


    @Query("SELECT * FROM question_answer_options WHERE id IN (:ids)")
    public LiveData<List<QuestionAnswerOption>> fetchByIds(Long... ids);

    //-------------------------------------------------
    @Query("SELECT COUNT(*) FROM question_answer_options")
    public int getCount();
}


