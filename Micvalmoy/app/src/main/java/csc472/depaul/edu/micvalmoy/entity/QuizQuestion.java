package csc472.depaul.edu.micvalmoy.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/*

-- A question belongs to a quiz, A quiz belongs to a course
CREATE TABLE quiz_questions (
  quiz_id INTEGER REFERENCES quizzes,
  question_id INTEGER REFERENCES questions,
  sort_index INTEGER,
  enabled INTEGER NOT NULL DEFAULT 1, --  Boolean values are stored as INTEGERs 0 (false) and 1 (true)
  PRIMARY KEY (quiz_id, question_id)
);


 */



@Entity(
        tableName="quiz_questions",
        primaryKeys={"quiz_id", "question_id"},
        foreignKeys={
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
                        onDelete=CASCADE)
        },
        indices={
                @Index(value="quiz_id"),
                @Index(value="question_id")
        }
)

public class QuizQuestion {
    @NonNull
    @ColumnInfo(name = "quiz_id")
    private Long quizId;

    @NonNull
    @ColumnInfo(name = "question_id")
    private Long questionId;


    @ColumnInfo(name = "sort_index")
    private Integer sortIndex;

    @NonNull
    private boolean enabled;

    public QuizQuestion() {
        this.sortIndex = 0;
        this.enabled = true;
    }

    /**
     * Basic getters /setters
     */
    @NonNull
    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(@NonNull Long quizId) {
        this.quizId = quizId;
    }

    @NonNull
    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(@NonNull Long questionId) {
        this.questionId = questionId;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    @NonNull
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(@NonNull boolean enabled) {
        this.enabled = enabled;
    }
}