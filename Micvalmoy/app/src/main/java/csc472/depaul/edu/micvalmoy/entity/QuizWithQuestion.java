package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;

import java.util.List;

public class QuizWithQuestion extends Quiz {

    @Embedded(prefix = "question_")
    public Question question;

    /**
     * Basic getters /setters
     */



    //------------------------------------

    @Override
    public String toString() {
        return "QuizWithQuestion{" +
                "question=" + question +
                ", Quiz=" + super.toString() +
                '}';
    }
}
