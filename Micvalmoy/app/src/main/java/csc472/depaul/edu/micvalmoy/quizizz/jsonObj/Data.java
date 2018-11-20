package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

/**
 * @author mrichards
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Micvalmoy;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.quizizz.QuizizzJson;
import timber.log.Timber;

public class Data {

    @SerializedName("hits")
    @Expose
    private List<QuizData> quizDataList = new ArrayList<QuizData>();

    @SerializedName("quiz")
    @Expose
    QuizData quizData;


    @SerializedName("micvalmoy")
    @Expose
    Micvalmoy micvalmoy;





    public List<QuizData> getQuizDataList() {
        return quizDataList;
    }

    public void setQuizDataList(List<QuizData> quizDataList) {
        this.quizDataList = quizDataList;
    }

    public QuizData getQuizData() {
        return quizData;
    }

    public void setQuizData(QuizData quizData) {
        this.quizData = quizData;
    }

    public Micvalmoy getMicvalmoy() {
        return micvalmoy;
    }

    public void setMicvalmoy(Micvalmoy micvalmoy) {
        this.micvalmoy = micvalmoy;
    }

    @Override
    public String toString() {
        return "Data{" +
                "quizDataList=" + quizDataList +
                ", quizData=" + quizData +
                ", micvalmoy=" + micvalmoy +
                '}';
    }
}