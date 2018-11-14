package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class QuizWithCategory{
    @NonNull
    @ColumnInfo(name = "category_id")
    public Long categoryId;

    @ColumnInfo(name = "quiz_id")
    public Long quizId;

    @Embedded(prefix = "qz_")
    public Quiz quiz;

    @Embedded(prefix = "cat_")
    public Category category;



    /**
     * Basic getters /setters
     */


    //------------------------------------
  /*   @Override
   public String toString() {
        return "Quiz{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}'+
                "QuizWithCategory{" +
                "categories=" + categories.toString() +
                '}';
    }*/
}
