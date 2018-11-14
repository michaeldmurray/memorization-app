package csc472.depaul.edu.micvalmoy.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Quiz;



@Dao
public interface ExamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(Quiz quiz);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Quiz> exam_details);

    @Update
    public int update(Quiz quiz);

    @Update
    public int updateAll(Quiz... quiz);

    @Delete
    public void delete(Quiz quiz);

    @Query("DELETE FROM exams where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM exams where id in (:ids)")
    public int deleteByIds(Long... ids);

    @Query("SELECT * FROM exams WHERE id =:id")
    public LiveData<Quiz> fetchById(Long id);


    @Query("SELECT * FROM exams WHERE id IN (:ids)")
    public LiveData<Quiz> fetchByIds(Long... ids);


    @Query("SELECT * FROM exams")
    public LiveData<List<Quiz>> fetchAll();

    @Query("SELECT COUNT(*) FROM exams")
    public int getCount();

    //------------------------------

    @Insert
    void insertAll(Quiz... exam_details);


    @Query("SELECT * FROM exams quiz")
    public List<Quiz> getAll();

    @Delete
    public void deleteAll(Quiz... exam_details);
}