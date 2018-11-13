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
import csc472.depaul.edu.micvalmoy.entity.QuizWithCategory;

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

    @Insert
    void insertAll(Quiz... exam_details);


    @Query("SELECT * FROM exam_details quiz")
    public List<Quiz> getAll();

    @Delete
    public void deleteAll(Quiz... exam_details);


/*

    @Query("SELECT ed.name , ed.description, cat.id as category_id, cat.name as category_name " +
            "FROM exam_details ed " +
            "JOIN quiz_categories cat on (q.quiz_id = ed.id) " +
            "JOIN categories cat on (cat.id = q.category_id) ")
    public List<QuizWithCategory> getCategoryexam_details();

    @Query("SELECT  * " +
            "FROM exam_details q " +
            "JOIN Quiz q on (q.quiz_id = ed.id) " +
            "JOIN categories cat on (cat.id = q.category_id) " +
            "WHERE ed.id = :quizId")
    public QuizWithCategory getCategoryQuiz(Long quizId);
*/



/*  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT quiz.id, quiz.name, quiz.description " +
          "FROM exam_details quiz " +
          "LEFT JOIN categories cat ON quiz.id = cat.id")
  public List<QuizWithCategory> getCategoryexam_details();


    @Query("quiz.id, quiz.name, quiz.description, quiz.category_id " +
            "FROM exam_details quiz " +
            "LEFT JOIN categories cat ON quiz.category_id = cat.id " +
            "WHERE quiz.id = :quizId")
    QuizWithCategory getCategoryQuiz(long quizId);*/





}