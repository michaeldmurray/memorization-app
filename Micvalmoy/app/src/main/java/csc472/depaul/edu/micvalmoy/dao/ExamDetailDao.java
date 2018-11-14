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
public interface ExamDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(Quiz quiz);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Quiz> exam_details);

    @Update
    public int update(Quiz quiz);

    @Update
    public int updateAll(Quiz... quiz);

    @Delete
    public void delete(Quiz quiz);

    @Query("DELETE FROM exam_details where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM exam_details where id in (:ids)")
    public int deleteByIds(Long... ids);

    @Query("SELECT * FROM exam_details WHERE id =:id")
    public LiveData<Quiz> fetchById(Long id);


    @Query("SELECT * FROM exam_details WHERE id IN (:ids)")
    public LiveData<Quiz> fetchByIds(Long... ids);


    @Query("SELECT * FROM exam_details")
    public LiveData<List<Quiz>> fetchAll();

    @Query("SELECT COUNT(*) FROM exam_details")
    public int getCount();

    //------------------------------

    @Insert
    void insertAll(Quiz... exam_details);


    @Query("SELECT * FROM exam_details quiz")
    public List<Quiz> getAll();

    @Delete
    public void deleteAll(Quiz... exam_details);


/*

    @Query("SELECT ed.id, ed.name ed.description, cat.id as category_id, cat.name as category_name " +
            "FROM exam_details ed " +
            "JOIN user_answers ua on (ed.user_id = ed.id) " +
            "JOIN categories cat on (cat.id = q.category_id) ")
    public List<QuizWithCategory> getCategoryexam_details();

    @Query("SELECT  * " +
            "FROM exam_details q " +
            "JOIN quiz q on (q.quiz_id = ed.id) " +
            "JOIN categories cat on (cat.id = q.category_id) " +
            "WHERE ed.id = :quizId")
    public QuizWithCategory getCategoryQuiz(Long quizId);
*/
}