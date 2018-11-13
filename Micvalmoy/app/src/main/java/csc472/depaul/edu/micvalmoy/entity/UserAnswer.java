package csc472.depaul.edu.micvalmoy.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/*

CREATE TABLE user_answers (
id INTEGER primary key AUTOINCREMENT,
    user_id INTEGER NOT NULL references users(id),
    question_id INTEGER NOT NULL references questions(id),
    answer_id INTEGER NOT NULL references question_answer_options(id)
);


 */


@Entity(
        tableName="quiz_questions",
        primaryKeys={"user_id","quiz_id", "question_id"},
        foreignKeys={
                @ForeignKey(
                        entity=Question.class,
                        parentColumns="id",
                        childColumns="user_id",
                        onDelete=CASCADE
                ),
                @ForeignKey(
                        entity=Quiz.class,
                        parentColumns="id",
                        childColumns="quiz_id",
                        onDelete=CASCADE
                ),
                @ForeignKey(
                        entity=Question.class,
                        parentColumns="id",
                        childColumns="question_id",
                        onDelete=CASCADE
                )
        },
        indices={
                @Index(value="user_id"),
                @Index(value="question_id"),
                @Index(value="quiz_id"),
        }
)

public class UserAnswer {


    @ColumnInfo(name = "quiz_id")
    private Long quizId;


    @ColumnInfo(name = "question_id")
    private Long questionId;


    @ColumnInfo(name = "user_id")
    private Long user_id;


    /**
     * Basic getters /setters
     */
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}