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

 public  List<QuizInfo> getQuizzes(){

  List<QuizInfo> quizzes = new ArrayList<>();

  //** get the Question Information from the list of search quizzes
  List<QuizData> quizizzSearchQuizLists =  this.data.getSearchResultQuizLists();
  for (QuizData quizData: quizizzSearchQuizLists){
   QuizInfo quizInfo = quizData.getQuizInfo();

   //The ID of the quiz is on this level, not sure if it like this all the time,
   // but for now we will store the id with the quiz info
   String quizId = quizData.getQuizId();


   quizInfo.setId(quizId);
   quizzes.add(quizInfo);
  }
  return quizzes;
 }

 public  QuizInfo getQuizz(){
  QuizData quizData =  this.data.getQuizData();

   QuizInfo quizInfo = quizData.getQuizInfo();

   //The ID of the quiz is on this level, not sure if it like this all the time,
   // but for now we will store the id with the quiz info
   String quizId = quizData.getQuizId();

   quizInfo.setId(quizId);

  return quizInfo;
 }


    @Override
    public String toString() {
        return "Quizizz{" +
                "data=" + data.toString() +
                '}';
    }
}