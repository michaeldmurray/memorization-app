package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Quizizz {

 @SerializedName("data")
 @Expose
 private Data data;

 public Data getData() {
  return data;
 }

 public void setData(Data data) {
  this.data = data;
 }

 public  ArrayList<QuizInfo> getQuizzes(){

  ArrayList<QuizInfo> quizes = new ArrayList<>();

  //** get the Question Information from the list of search hits
  List<Hit> quizizzSearchHits =  this.data.getHits();
  for (Hit hit: quizizzSearchHits){
   QuizInfo quizInfo = hit.getQuizInfo();

   //The ID of the quiz is on this level, not sure if it like this all the time,
   // but for now we will store the id with the quiz info
   String quizId = hit.getQuizId();


   quizInfo.setId(quizId);
   quizes.add(quizInfo);
  }
  return quizes;
 }

}