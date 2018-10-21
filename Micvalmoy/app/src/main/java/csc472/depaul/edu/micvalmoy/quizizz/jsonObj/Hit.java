package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit {

 @SerializedName("info")
 @Expose
 private QuizInfo quizInfo;

 @SerializedName("_id")
 @Expose
 private String quizId;

 public QuizInfo getQuizInfo() {
  return quizInfo;
 }

 public void getQuizInfo(QuizInfo quizInfo) {
  this.quizInfo = quizInfo;
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
}
