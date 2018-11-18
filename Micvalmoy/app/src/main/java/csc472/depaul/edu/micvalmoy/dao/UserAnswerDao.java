package csc472.depaul.edu.micvalmoy.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.UserAnswer;


/**
 * Using Ellipsis to Accept Variable Number of Arguments (Var args)
 * https://www.quora.com/Why-does-3-dot-symbol-is-used-in-varargs-Java
 * Accepts a comma separated list of Quiz objects
 * updateAll(q1,q2)
 */



@Dao
public interface UserAnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(UserAnswer useranswer);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(UserAnswer... useranswer);

    @Update
    public int update(UserAnswer useranswer);

    @Update
    public int updateAll(UserAnswer... useranswer);

    @Delete
    public int delete(UserAnswer useranswer);


    //-------------------------------------------------

    @Delete
    int deleteAll(UserAnswer... userAnswers);


    @Query("DELETE FROM user_answers where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM user_answers where id in (:ids)")
    public int deleteByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM user_answers")
    List<UserAnswer> getAll();

    @Query("SELECT * FROM user_answers")
    public LiveData<List<UserAnswer>> fetchAll();


    @Query("SELECT * FROM user_answers WHERE id =:id")
    public LiveData<UserAnswer> fetchById(Long id);


    @Query("SELECT * FROM user_answers WHERE id IN (:ids)")
    public LiveData<List<UserAnswer>> fetchByIds(Long... ids);

    //-------------------------------------------------



    @Query("SELECT COUNT(*) FROM user_answers")
    public int getCount();

    //-------------------------------------------------



}