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

@Dao
public interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(Question question);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Question> questions);

    @Update
    public int update(Question question);

    @Update
    public int updateAll(Question... question);

    @Delete
    public void delete(Question question);

    @Query("DELETE FROM questions where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM questions where id in (:ids)")
    public int deleteByIds(Long... ids);

    @Query("SELECT * FROM questions WHERE id =:id")
    public LiveData<Question> fetchById(Long id);


    @Query("SELECT * FROM questions WHERE id IN (:ids)")
    public LiveData<Question> fetchByIds(Long... ids);


    @Query("SELECT * FROM questions")
    public LiveData<List<Question>> fetchAll();

    @Query("SELECT COUNT(*) FROM questions")
    public int getCount();

    //------------------------------
    @Query("SELECT * FROM questions ORDER BY text asc")
    public LiveData<List<Question>> fetchAllSortByText();

}