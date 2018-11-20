package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

/**
 * @author mrichards
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizData {

    @SerializedName("createdBy")
    @Expose
    QuizCreatedBy createdBy;


    @SerializedName("info")
    @Expose
    private QuizInfo quizInfo;

    @SerializedName("_id")
    @Expose
    private String quizId;


    public QuizCreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(QuizCreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public QuizInfo getQuizInfo() {
        return quizInfo;
    }

    public void setQuizInfo(QuizInfo quizInfo) {
        this.quizInfo = quizInfo;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    @Override
    public String toString() {
        return "QuizData{" +
                "quizInfo=" + quizInfo +
                ", quizId='" + quizId + '\'' +
                '}';
    }
}
