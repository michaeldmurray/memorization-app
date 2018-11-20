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

import static android.arch.persistence.room.ForeignKey.CASCADE;


/*

CREATE TABLE question_answer_options (
    id INTEGER primary key AUTOINCREMENT,
    question_id INTEGER NOT NULL references questions(id),
    text varchar(150) NOT NULL,
    is_answer INTEGER  NOT NULL,
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
@Parcel
public class QuestionAnswerOption{
    @NonNull
    @PrimaryKey(autoGenerate = true)
    public Long id;


    @NonNull
    @ColumnInfo(name = "question_id")
    public Long questionId;

    @NonNull
    public String text;

    @NonNull
    @ColumnInfo(name = "is_answer")
    boolean isAnswer;

    //By default all options are not the answer
    public QuestionAnswerOption() {
        this.isAnswer = false;
    }

    /**
     * Basic getters /setters
     */


    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
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

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }
}