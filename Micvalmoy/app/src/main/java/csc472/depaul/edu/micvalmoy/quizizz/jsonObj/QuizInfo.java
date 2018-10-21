package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class QuizInfo {

 @SerializedName("name")
 @Expose
 private String name;

 @SerializedName("id")
 @Expose
 private String id;
 @SerializedName("questions")
 @Expose
 private List< Question > questions = new ArrayList< Question >();

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public List < Question > getQuestions() {
  return questions;
 }

 public void setQuestions(List < Question > questions) {
  this.questions = questions;
 }

}
