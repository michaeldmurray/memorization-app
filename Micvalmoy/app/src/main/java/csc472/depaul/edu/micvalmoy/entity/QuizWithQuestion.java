package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;

import java.util.List;

public class QuizWithQuestion extends Quiz {

    @Embedded(prefix = "question_")
    public Question question;


    @Ignore
    public List<Question> questions; //  = new ArrayList<>();

    /**
     * Basic getters /setters
     */

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }




    //------------------------------------
    public void addQuestion(Question question) {
        questions.add(question);
    }


    //------------------------------------
    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + getId() +
                ", description='" + getDescription() + '\'' +
                '}'+
                "QuizWithQuestion{" +
                "questions=" + questions.toString() +
                '}';
    }
}
