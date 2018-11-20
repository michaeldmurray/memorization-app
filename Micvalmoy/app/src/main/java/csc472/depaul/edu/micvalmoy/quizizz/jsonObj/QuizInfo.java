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

//quizinfo/questions/structure
//------------------------------------------------
public class QuizInfo {

 @SerializedName("name")
 @Expose
 private String name;

 @SerializedName("id")
 @Expose
 private String id;

 @SerializedName("questions")
 @Expose
 private List<QuizizzQuestion> questions = new ArrayList<>();

 @SerializedName("category")
 @Expose
 private List<String> category = new ArrayList<>();

 @SerializedName("subjects")
 @Expose
 private List<String> subjects = new ArrayList<>();

 @SerializedName("topics")
 @Expose
 private List<String> topics = new ArrayList<>();

 @SerializedName("subtopics")
 @Expose
 private List<String> subtopics = new ArrayList<>();


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


 public List<QuizizzQuestion> getQuestions() {
  return questions;
 }

 public void setQuestions(List<QuizizzQuestion> questions) {
  this.questions = questions;
 }

 public List<String> getCategory() {
  return category;
 }

 public void setCategory(List<String> category) {
  this.category = category;
 }

 public List<String> getSubjects() {
  return subjects;
 }

 public void setSubjects(List<String> subjects) {
  this.subjects = subjects;
 }

 public List<String> getTopics() {
  return topics;
 }

 public void setTopics(List<String> topics) {
  this.topics = topics;
 }

 public List<String> getSubtopics() {
  return subtopics;
 }

 public void setSubtopics(List<String> subtopics) {
  this.subtopics = subtopics;
 }

 @Override
 public String toString() {
  return "QuizInfo{" +
          "name='" + name + '\'' +
          ", id='" + id + '\'' +
          ", questions=" + questions +
          ", category=" + category +
          ", subjects=" + subjects +
          ", topics=" + topics +
          ", subtopics=" + subtopics +
          '}';
 }
}
