package csc472.depaul.edu.micvalmoy.entity;

/**
 * @author mrichards
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

import org.parceler.Parcel;

import java.util.List;


@Parcel
public class QuizWithQuestion {
/*
    @NonNull
    @ColumnInfo(name = "question_id")
    public Long questionId;

    @ColumnInfo(name = "quiz_id")
    public Long quizId;
*/

    @Embedded(prefix = "q_")
    public Quiz quiz;

    @Embedded(prefix = "quest_")
    public Question question;


    public QuizWithQuestion() {
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }


    /**
     * Basic getters /setters
     */
    @Override
    public String toString() {
        return "QuizWithQuestion{" +
                "quiz=" + quiz +
                ", question=" + question +
                '}';
    }


}
