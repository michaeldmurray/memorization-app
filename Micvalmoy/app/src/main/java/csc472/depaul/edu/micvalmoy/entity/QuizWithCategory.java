package csc472.depaul.edu.micvalmoy.entity;

/**
 * @author mrichards
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Parcel
public class QuizWithCategory {
    @NonNull
    @ColumnInfo(name = "category_id")
    public Long categoryId;

    @ColumnInfo(name = "quiz_id")
    public Long quizId;

    @Embedded(prefix = "q_")
    public Quiz quiz;

    @Embedded(prefix = "cat_")
    public Category category;

    public QuizWithCategory() {
    }

    /**
     * Basic getters /setters
     */
    @NonNull
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NonNull Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "QuizWithCategory{" +
                "categoryId=" + categoryId +
                ", quizId=" + quizId +
                ", quiz=" + quiz +
                ", category=" + category +
                '}';
    }
}
