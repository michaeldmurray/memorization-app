package csc472.depaul.edu.micvalmoy.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;


/**
 * Using Ellipsis to Accept Variable Number of Arguments (Var args)
 * https://www.quora.com/Why-does-3-dot-symbol-is-used-in-varargs-Java
 * Accepts a comma separated list of Quiz objects
 * updateAll(q1,q2)
 */



@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(Question question);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(Question... question);

    @Update
    public int update(Question question);

    @Update
    public int updateAll(Question... question);

    @Delete
    public int delete(Question question);


    //-------------------------------------------------

    @Delete
    int deleteAll(Question... questions);


    @Query("DELETE FROM questions where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM questions where id in (:ids)")
    public int deleteByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM questions")
    List<Question> getAll();

    @Query("SELECT * FROM questions")
    public LiveData<List<Question>> fetchAll();


    @Query("SELECT * FROM questions WHERE id =:id")
    public LiveData<Question> fetchById(Long id);


    @Query("SELECT * FROM questions WHERE id IN (:ids)")
    public LiveData<List<Question>> fetchByIds(Long... ids);

    //-------------------------------------------------
    @Query("SELECT COUNT(*) FROM questions")
    public int getCount();

    //-------------------------------------------------
    //-------------------------------------------------
    @Query("SELECT " +
            "qao.id , " +
            "qao.question_id , " +
            "qao.text , " +
            "qao.is_answer " +
            "FROM questions quest " +
            "LEFT JOIN question_answer_options qao on (qao.question_id = quest.id) " +
            "WHERE qao.is_answer = 1 AND quest.id = (:id)")
    List<QuestionAnswerOption> getCorrectAnswersByQuestionId(Long id);


    @Query("SELECT " +
            "qao.id , " +
            "qao.question_id , " +
            "qao.text , " +
            "qao.is_answer " +
            "FROM questions quest " +
            "LEFT JOIN question_answer_options qao on (qao.question_id = quest.id) " +
            "WHERE quest.id = (:id)")
    List<QuestionAnswerOption> getAllAnswerOptionsById(Long id);
}