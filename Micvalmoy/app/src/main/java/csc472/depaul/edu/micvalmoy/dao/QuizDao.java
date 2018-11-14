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
import csc472.depaul.edu.micvalmoy.entity.QuizWithQuestion;


@Dao
public interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(Quiz quiz);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long>  insertAll(List<Quiz> quizzes);

    @Update
    public int update(Quiz quiz);

    @Update
    public int updateAll(Quiz... quiz);

    @Delete
    public void delete(Quiz quiz);

    @Query("DELETE FROM quizzes where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM quizzes where id in (:ids)")
    public int deleteByIds(Long... ids);

    @Query("SELECT * FROM quizzes WHERE id =:id")
    public LiveData<Quiz> fetchById(Long id);


    @Query("SELECT * FROM quizzes WHERE id IN (:ids)")
    public LiveData<Quiz> fetchByIds(Long... ids);


    @Query("SELECT * FROM quizzes")
    public LiveData<List<Quiz>> fetchAll();

    @Query("SELECT COUNT(*) FROM quizzes")
    public int getCount();

    //------------------------------
    @Query("SELECT * FROM quizzes ORDER BY name asc")
    public LiveData<List<Quiz>> fetchAllSortByName();

    @Insert
    void insertAll(Quiz... quizzes);


    @Query("SELECT * FROM quizzes")
    public List<Quiz> getAll();

    @Delete
    public void deleteAll(Quiz... quizzes);



    @Query("SELECT q.id, q.name,q.description, cat.id as category_id, cat.name as category_name " +
            "FROM quizzes q " +
            "JOIN quiz_categories qcat on (qcat.quiz_id = q.id) " +
            "JOIN categories cat on (cat.id = qcat.category_id) ")
    public List<QuizWithCategory> getCategoryQuizzes();

    @Query("SELECT q.id, q.name,q.description, cat.id as category_id, cat.name as category_name " +
            "FROM quizzes q " +
            "JOIN quiz_categories qcat on (qcat.quiz_id = q.id) " +
            "JOIN categories cat on (cat.id = qcat.category_id) " +
            "WHERE q.id = :quizId")
    public QuizWithCategory getCategoryQuiz(Long quizId);

/*

    @Query("SELECT q.id, q.name,q.description, quest.id as question_id, quest.text as question_name " +
            "FROM quizzes q " +
            "JOIN quiz_questions qquest on (qquest.quiz_id = q.id) " +
            "JOIN questions quest on (quest.id = qquest.question_id) ")
    public List<QuizWithQuestion> getQuestionQuizzes();

    @Query("SELECT q.id, q.name,q.description, quest.id as question_id, quest.text as question_name " +
            "FROM quizzes q " +
            "JOIN quiz_questions qquest on (qquest.quiz_id = q.id) " +
            "JOIN questions quest on (quest.id = qquest.question_id) " +
            "WHERE q.id = :quizId")
    public QuizWithQuestion getQuestionQuiz(Long quizId);
*/



    @Query("SELECT * " +
            "FROM quizzes q " +
            "JOIN quiz_questions qquest on (qquest.quiz_id = q.id) " +
            "JOIN questions quest on (quest.id = qquest.question_id) ")
    public List<QuizWithQuestion> getQuizQuestions();

    @Query("SELECT * " +
            "FROM quizzes q " +
            "JOIN quiz_questions qquest on (qquest.quiz_id = q.id) " +
            "JOIN questions quest on (quest.id = qquest.question_id) " +
            "WHERE q.id = :quizId")
    public QuizWithQuestion getQuizQuestionByQuizId(Long quizId);

    @Query("SELECT * " +
            "FROM quizzes q " +
            "JOIN quiz_questions qquest on (qquest.quiz_id = q.id) " +
            "JOIN questions quest on (quest.id = qquest.question_id) " +
            "WHERE quest.id = :question_id")
    public QuizWithQuestion getQuizQuestionByQuestionID(Long question_id);

/*  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("SELECT quiz.id, quiz.name, quiz.description " +
          "FROM quizzes quiz " +
          "LEFT JOIN categories cat ON quiz.id = cat.id")
  public List<QuizWithCategory> getCategoryQuizzes();


    @Query("quiz.id, quiz.name, quiz.description, quiz.category_id " +
            "FROM quizzes quiz " +
            "LEFT JOIN categories cat ON quiz.category_id = cat.id " +
            "WHERE quiz.id = :quizId")
    QuizWithCategory getCategoryQuiz(long quizId);*/





}