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


@Dao
public interface UserAnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(UserAnswer quiz);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long>  insertAll(List<UserAnswer> user_answers);

    @Update
    public int update(UserAnswer quiz);

    @Update
    public int updateAll(UserAnswer... quiz);

    @Delete
    public void delete(UserAnswer quiz);

    @Query("DELETE FROM user_answers where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM user_answers where id in (:ids)")
    public int deleteByIds(Long... ids);

    @Query("SELECT * FROM user_answers WHERE id =:id")
    public LiveData<UserAnswer> fetchById(Long id);


    @Query("SELECT * FROM user_answers WHERE id IN (:ids)")
    public LiveData<UserAnswer> fetchByIds(Long... ids);


    @Query("SELECT * FROM user_answers")
    public LiveData<List<UserAnswer>> fetchAll();

    @Query("SELECT COUNT(*) FROM user_answers")
    public int getCount();

    //------------------------------

    @Insert
    void insertAll(UserAnswer... user_answers);


    @Query("SELECT * FROM user_answers quiz")
    public List<UserAnswer> getAll();

    @Delete
    public void deleteAll(UserAnswer... user_answers);


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