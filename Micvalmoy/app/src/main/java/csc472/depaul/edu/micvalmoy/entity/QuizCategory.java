package csc472.depaul.edu.micvalmoy.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/*
-- A quiz belongs to a category
CREATE  TABLE quiz_categories (
  quiz_id INTEGER REFERENCES quizzes,
  category_id INTEGER REFERENCES categories,

  PRIMARY KEY (quiz_id,category_id)
);

 */



@Entity(
        tableName="quiz_categories",
        primaryKeys={"quiz_id","category_id"},
        foreignKeys={
                @ForeignKey(
                        entity=Quiz.class,
                        parentColumns="id",
                        childColumns="quiz_id",
                        onDelete=CASCADE
                ),
                @ForeignKey(
                        entity=Category.class,
                        parentColumns="id",
                        childColumns="category_id",
                        onDelete=CASCADE
                )
        },
        indices={
                @Index(value="quiz_id"),
                @Index(value="category_id")
        }
)

public class QuizCategory {
    @NonNull
    @ColumnInfo(name = "category_id")
    public final Long categoryId;

    @ColumnInfo(name = "quiz_id")
    @NonNull public final Long quizId;

    public QuizCategory(Long categoryId, Long quizId) {
        this.categoryId=categoryId;
        this.quizId=quizId;
    }

    /**
     * Basic getters /setters
     */
    @NonNull
    public Long getCategoryId() {
        return categoryId;
    }

    @NonNull
    public Long getQuizId() {
        return quizId;
    }


    @Override
    public String toString() {
        return "QuizCategory{" +
                "categoryId=" + categoryId +
                ", quizId=" + quizId +
                '}';
    }
}