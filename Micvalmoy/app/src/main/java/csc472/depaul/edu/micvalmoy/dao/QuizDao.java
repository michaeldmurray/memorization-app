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

import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.entity.QuizWithCategory;
import csc472.depaul.edu.micvalmoy.entity.QuizWithCourse;
import csc472.depaul.edu.micvalmoy.entity.QuizWithQuestion;


@Dao
public interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(Quiz quiz);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(Quiz... quiz);

    @Update
    public int update(Quiz quiz);

    @Update
    public int updateAll(Quiz... quiz);

    @Delete
    public int delete(Quiz quiz);


    //-------------------------------------------------

    @Delete
    int deleteAll(Quiz... quizzes);


    @Query("DELETE FROM quizzes where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM quizzes where id in (:ids)")
    public int deleteByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM quizzes")
    List<Quiz> getAll();

    @Query("SELECT * FROM quizzes")
    public LiveData<List<Quiz>> fetchAll();


    @Query("SELECT * FROM quizzes WHERE id =:id")
    public LiveData<Quiz> fetchById(Long id);


    @Query("SELECT * FROM quizzes WHERE id IN (:ids)")
    public LiveData<List<Quiz>> fetchByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM quizzes ORDER BY name asc")
    public LiveData<List<Quiz>> fetchAllSortByName();


    @Query("SELECT COUNT(*) FROM quizzes")
    public int getCount();




    //___________________________________________________
    //QuizWithQuestion
    //Get all the questions for a quiz
    //___________________________________________________



    @Query("SELECT " +
            "q.id            as q_id, " +
            "q.name          as q_name, " +
            "q.description   as q_description, " +
            "q.createdAt     as q_createdAt, " +
            "q.updatedAt     as q_updatedAt, " +
            "q.quizzizz_id   as q_quizzizz_id, " +
            "quest.id        as quest_id, " +
            "quest.quiz_id   as quest_quiz_id, " +
            "quest.text      as quest_text , " +
            "quest.hint      as quest_hint, " +
            "quest.type      as quest_type, " +
            "quest.nonce     as quest_nonce, " +
            "quest.sort_index as quest_sort_index, " +
            "quest.enabled   as quest_enabled, " +
            "quest.createdAt as quest_createdAt, " +
            "quest.updatedAt as quest_updatedAt " +
            "FROM quizzes q " +
            "JOIN questions quest on (quest.quiz_id = q.id) " +
            "WHERE q.id = :quizId")
    public List<QuizWithQuestion> getQuizQuestionByQuizId(Long quizId);




    //___________________________________________________
    //QuizWithCourse
    //Get all the courses for a quiz
    //___________________________________________________


    @Query("SELECT " +
            "q.id             as q_id, " +
            "q.name           as q_name, " +
            "q.description    as q_description, " +
            "q.createdAt      as q_createdAt, " +
            "q.updatedAt      as q_updatedAt, " +
            "q.quizzizz_id    as q_quizzizz_id, " +
            "crse.id as crse_id, " +
            "crse.name as crse_name, " +
            "qcrse.quiz_id as quiz_id, " +
            "qcrse.course_id as course_id " +
            "FROM quizzes q " +
            "JOIN quiz_courses qcrse on (qcrse.quiz_id = q.id) " +
            "JOIN courses crse on (crse.id = qcrse.course_id) " +
            "WHERE q.id = :quizId")
    public List<QuizWithCourse> getQuizCourseByQuizId(Long quizId);


    @Query("SELECT " +
            "q.id             as q_id, " +
            "q.name           as q_name, " +
            "q.description    as q_description, " +
            "q.createdAt      as q_createdAt, " +
            "q.updatedAt      as q_updatedAt, " +
            "q.quizzizz_id    as q_quizzizz_id, " +
            "crse.id as crse_id, " +
            "crse.name as crse_name, " +
            "qcrse.quiz_id as quiz_id, " +
            "qcrse.course_id as course_id " +
            "FROM quizzes q " +
            "JOIN quiz_courses qcrse on (qcrse.quiz_id = q.id) " +
            "JOIN courses crse on (crse.id = qcrse.course_id) " +
            "WHERE crse.id = :course_id")
    public List<QuizWithCourse> getQuizCourseByCourseID(Long course_id);






    //___________________________________________________
    //QuizWithCategory
    //Get all the courses for a quiz
    //___________________________________________________

    @Query("SELECT " +
            "q.id            as q_id, " +
            "q.name          as q_name, " +
            "q.description   as q_description, " +
            "q.createdAt     as q_createdAt, " +
            "q.updatedAt     as q_updatedAt, " +
            "q.quizzizz_id   as q_quizzizz_id, " +
            "cat.id          as cat_id, " +
            "cat.name        as cat_name, " +
            "qcat.quiz_id as quiz_id, " +
            "qcat.category_id as category_id " +
            "FROM quizzes q " +
            "JOIN quiz_categories qcat on (qcat.quiz_id = q.id) " +
            "JOIN categories cat on (cat.id = qcat.category_id) " +
            "WHERE q.id = :quizId")
    public List<QuizWithCategory> getQuizCategoryByQuizId(Long quizId);



    @Query("SELECT " +
            "q.id            as q_id, " +
            "q.name          as q_name, " +
            "q.description   as q_description, " +
            "q.createdAt     as q_createdAt, " +
            "q.updatedAt     as q_updatedAt, " +
            "q.quizzizz_id   as q_quizzizz_id, " +
            "cat.id          as cat_id, " +
            "cat.name        as cat_name, " +
            "qcat.quiz_id as quiz_id, " +
            "qcat.category_id as category_id " +
            "FROM quizzes q " +
            "JOIN quiz_categories qcat on (qcat.quiz_id = q.id) " +
            "JOIN categories cat on (cat.id = qcat.category_id) " +
            "WHERE cat.id = :category_id")
    public QuizWithCategory getQuizCategoryByCategoryID(Long category_id);
}