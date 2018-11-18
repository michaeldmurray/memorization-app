package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data {

    @SerializedName("hits")
    @Expose
    private List<QuizData> searchResultQuizLists = new ArrayList<QuizData>();

    @SerializedName("quiz")
    QuizData quizData;

    public List<QuizData> getSearchResultQuizLists() {
        return searchResultQuizLists;
    }

    public void setSearchResultQuizLists(List<QuizData> searchResultQuizLists) {
        this.searchResultQuizLists = searchResultQuizLists;
    }

    public QuizData getQuizData() {
        return quizData;
    }

    public void setQuizData(QuizData quizData) {
        this.quizData = quizData;
    }


    @Override
    public String toString() {
        return "Data{" +
                "searchResultQuizLists=" + searchResultQuizLists.toString() +
                ", quizData=" + quizData.toString() +
                '}';
    }
}