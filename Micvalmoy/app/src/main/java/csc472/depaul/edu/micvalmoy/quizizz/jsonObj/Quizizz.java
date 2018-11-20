package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

/**
 * @author mrichards
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Quizizz {

    @SerializedName("data")@Expose
    private Data data;

    public List < QuizInfo > getQuizInfoList() {

        List < QuizInfo > quizzes = new ArrayList < >();

        //** get the Question Information from the list of search quizzes
        List < QuizData > quizDataList = this.data.getQuizDataList();
        for (QuizData quizData: quizDataList) {
            QuizInfo quizInfo = quizData.getQuizInfo();

            //The ID of the quiz is on this level, not sure if it like this all the time,
            // but for now we will store the id with the quiz info
            String quizId = quizData.getQuizId();

            quizInfo.setId(quizId);
            quizzes.add(quizInfo);
        }
        return quizzes;
    }

    public QuizInfo getQuizInfo() {
        QuizData quizData = this.data.getQuizData();

        QuizInfo quizInfo = quizData.getQuizInfo();

        //The ID of the quiz is on this level, not sure if it like this all the time,
        // but for now we will store the id with the quiz info
        String quizId = quizData.getQuizId();

        quizInfo.setId(quizId);

        return quizInfo;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Quizizz{" + "data=" + data + '}';
    }
}