package csc472.depaul.edu.micvalmoy.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;


/*

CREATE TABLE question_answer_options (
    id INTEGER primary key AUTOINCREMENT,
    question_id INTEGER NOT NULL references questions(id),
    text varchar(150) NOT NULL
);


*/


@Entity(
        tableName="question_answer_options",
        foreignKeys={
                @ForeignKey(
                        entity=Question.class,
                        parentColumns="id",
                        childColumns="question_id",
                        onDelete=CASCADE)
        },
        indices={
                @Index(value="id"),
                @Index(value="question_id")
        }
)

public class QuestionAnswerOption{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private Long id;


    @NonNull
    @ColumnInfo(name = "question_id")
    private Long questionId;

    @NonNull
    private String text;



    /**
     * Basic getters /setters
     */

    public Long getId() {
        return id;
    }
    @NonNull
    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(@NonNull Long questionId) {
        this.questionId = questionId;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }
}