package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;

import java.util.ArrayList;
import java.util.List;

public class QuestionAndAnswerChoice extends Question {

    @Embedded(prefix = "ans_")
    public QuestionAnswerOption option;

    @Ignore
    public List<QuestionAnswerOption> options = new ArrayList<>();




    /**
     * Basic getters /setters
     */




    //------------------------------------
    public void addOption(QuestionAnswerOption option) {
        options.add(option);
    }



    //------------------------------------


    @Override
    public String toString() {
        return "Question=" +
                super.toString() +
                ','+
                "QuestionWithQuestionAnswerOption=" +
                option.toString() +
                ','+


                "QuestionWithQuestionAnswerOption{" +
                "option="+
                ", option=" + option.toString() +
                '}';
    }
}
