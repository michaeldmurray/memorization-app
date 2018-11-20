package csc472.depaul.edu.micvalmoy.entity;

/**
 * @author mrichards
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;


import org.parceler.Parcel;

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


@Parcel
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
                        entity=Exam.class,
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
                @Index(value="option_id")
        }
)

public class UserAnswer {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    public Long id;

    @ColumnInfo(name = "user_id")
    public Long userId;


    @ColumnInfo(name = "exam_id")
    public Long examId;
  

    @ColumnInfo(name = "question_id")
    public Long questionId;


    @ColumnInfo(name = "option_id")
    public Long optionId;

    public UserAnswer() {
    }


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


    @Override
    public String toString() {
        return "UserAnswer{" +
                "id=" + id +
                ", userId=" + userId +
                ", examId=" + examId +
                ", questionId=" + questionId +
                ", optionId=" + optionId +
                '}';
    }

}