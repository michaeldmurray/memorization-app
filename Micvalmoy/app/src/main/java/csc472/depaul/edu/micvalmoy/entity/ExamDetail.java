package csc472.depaul.edu.micvalmoy.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/*

CREATE TABLE exam_details (
    id INTEGER primary key AUTOINCREMENT,
    user_id INTEGER references users(id),    
    quiz_id INTEGER REFERENCES quizzes,
    start_date date not null,
    end_date date not null,
    duration INTEGER,
    exam_result varchar(10) NOT NULL, -- PASS/FAIL
    exam_score INTEGER NOT NULL,      -- e.g. 40
    total_questions INTEGER NOT NULL  -- total no. of questions in the test
);


 */


@Entity(
        tableName="exam_details",
        foreignKeys={
                @ForeignKey(
                        entity=User.class,
                        parentColumns="id",
                        childColumns="user_id",
                        onDelete=CASCADE
                ),
                @ForeignKey(
                        entity=Quiz.class,
                        parentColumns="id",
                        childColumns="quiz_id",
                        onDelete=CASCADE
                )
        },
        indices={
                @Index(value="user_id"),
                @Index(value="quiz_id"),
                @Index(value="question_id"),
        }
)

public class ExamDetail {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    private Long id;

    @ColumnInfo(name = "user_id")
    private Long userId;

    @ColumnInfo(name = "quiz_id")
    private Long quizId;


    @ColumnInfo(name = "question_id")
    private Long questionId;


    @ColumnInfo(name = "start_date")
    private Date startDate;

    @ColumnInfo(name = "end_date")
    private Date endDate;

    @ColumnInfo(name = "duration")
    private int duration;

    @ColumnInfo(name = "exam_result")
    private int examResult;

    @ColumnInfo(name = "exam_score")
    private int examScore;

    @ColumnInfo(name = "total_questions")
    private int totalQuestions;

    

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

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getExamResult() {
        return examResult;
    }

    public void setExamResult(int examResult) {
        this.examResult = examResult;
    }

    public int getExamScore() {
        return examScore;
    }

    public void setExamScore(int examScore) {
        this.examScore = examScore;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}