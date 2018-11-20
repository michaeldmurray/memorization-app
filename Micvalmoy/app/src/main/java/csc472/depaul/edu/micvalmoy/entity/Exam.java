package csc472.depaul.edu.micvalmoy.entity;

/**
 * @author mrichards
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import csc472.depaul.edu.micvalmoy.tools.OffsetDateTimeParcelConverter;

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

@Parcel
@Entity(
        tableName="exams",
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
        }
)

public class Exam {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    public Long id;

    @ColumnInfo(name = "user_id")
    public Long userId;

    @ColumnInfo(name = "quiz_id")
    public Long quizId;


    @ParcelPropertyConverter(OffsetDateTimeParcelConverter.class)
    @ColumnInfo(name = "start_date")
    public OffsetDateTime startDate;


    @ParcelPropertyConverter(OffsetDateTimeParcelConverter.class)
    @ColumnInfo(name = "end_date")
    public OffsetDateTime endDate;

    @ColumnInfo(name = "duration")
    public int duration;

    @ColumnInfo(name = "exam_result")
    public int examResult;

    @ColumnInfo(name = "exam_score")
    public int examScore;

    public Exam() {
        this.startDate = startDate;
        this.endDate   = endDate;
    }

    @Ignore
    public Exam(OffsetDateTime startDate, OffsetDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Ignore
    public List<UserAnswer> userAnswer = new ArrayList<>();


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

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
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


    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", userId=" + userId +
                ", quizId=" + quizId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", examResult=" + examResult +
                ", examScore=" + examScore +
                '}';
    }
}