package csc472.depaul.edu.micvalmoy.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Exam;



@Dao
public interface ExamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(Exam exam);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<Exam> exam_details);

    @Update
    public int update(Exam exam);

    @Update
    public int updateAll(Exam... exam);

    @Delete
    public void delete(Exam exam);

    @Query("DELETE FROM exams where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM exams where id in (:ids)")
    public int deleteByIds(Long... ids);

    @Query("SELECT * FROM exams WHERE id =:id")
    public LiveData<Exam> fetchById(Long id);


    @Query("SELECT * FROM exams WHERE id IN (:ids)")
    public LiveData<Exam> fetchByIds(Long... ids);


    @Query("SELECT * FROM exams")
    public LiveData<List<Exam>> fetchAll();

    @Query("SELECT COUNT(*) FROM exams")
    public int getCount();

    //------------------------------

    @Insert
    void insertAll(Exam... exam_details);


    @Query("SELECT * FROM exams")
    public List<Exam> getAll();

    @Delete
    public void deleteAll(Exam... exam_details);
}