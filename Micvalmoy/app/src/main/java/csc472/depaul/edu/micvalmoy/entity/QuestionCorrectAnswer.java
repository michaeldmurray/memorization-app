package csc472.depaul.edu.micvalmoy.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/*

-- A question belongs to a option, A option belongs to a course
CREATE TABLE question_correct_answers (
    question_id INTEGER NOT NULL references questions(id),
    option_id INTEGER NOT NULL references question_answer_options(id),
    PRIMARY KEY (question_id,option_id)
);



 */



@Entity(
        tableName="question_correct_answers",
        primaryKeys={"question_id","option_id"},
        foreignKeys={


                @ForeignKey(
                        entity=Question.class,
                        parentColumns="id",
                        childColumns="question_id",
                        onDelete=CASCADE),
                @ForeignKey(
                        entity=QuestionAnswerOption.class,
                        parentColumns="id",
                        childColumns="option_id",
                        onDelete=CASCADE)
        },
        indices={
                @Index(value="question_id"),
                @Index(value="option_id")
        }
)

public class QuestionCorrectAnswer{


    @NonNull
    @ColumnInfo(name = "question_id")
    public final Long questionId;

    @NonNull
    @ColumnInfo(name = "option_id")
    public final Long optionId;

    public QuestionCorrectAnswer(Long questionId, Long optionId) {
        this.questionId=questionId;
        this.optionId=optionId;
    }


    /**
     * Basic getters /setters
     */
    @NonNull
    public Long getQuestionId() {
        return questionId;
    }

    @NonNull
    public Long getOptionId() {
        return optionId;
    }
}