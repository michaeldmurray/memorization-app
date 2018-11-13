package csc472.depaul.edu.micvalmoy.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/*

CREATE TABLE user_answers (
    id INTEGER primary key AUTOINCREMENT,
    user_id INTEGER NOT NULL references users(id),
    exam_id INTEGER NOT NULL references exam_details(id),    
    question_id INTEGER NOT NULL references questions(id),
    option_id INTEGER NOT NULL references question_answer_options(id),
);


 */



@Entity(
        tableName="user_answers",
        foreignKeys={
                @ForeignKey(
                        entity=User.class,
                        parentColumns="id",
                        childColumns="user_id",
                        onDelete=CASCADE
                ),

                @ForeignKey(
                        entity=ExamDetail.class,
                        parentColumns="id",
                        childColumns="exam_id",
                        onDelete=CASCADE
                ),

                @ForeignKey(
                        entity=Question.class,
                        parentColumns="id",
                        childColumns="question_id",
                        onDelete=CASCADE
                ),

                @ForeignKey(
                        entity=QuestionAnswerOption.class,
                        parentColumns="id",
                        childColumns="option_id",
                        onDelete=CASCADE
                ),
        },
        indices={
                @Index(value="user_id"),
                @Index(value="exam_id"),                
                @Index(value="question_id"),
                @Index(value="option_id"),
        }
)

public class ExamDetailDao {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    private Long id;

    @ColumnInfo(name = "user_id")
    private Long userId;


    @ColumnInfo(name = "exam_id")
    private Long examId;
  

    @ColumnInfo(name = "question_id")
    private Long questionId;


    @ColumnInfo(name = "option_id")
    private Long optionId;



    /**
     * Basic getters /setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }
}