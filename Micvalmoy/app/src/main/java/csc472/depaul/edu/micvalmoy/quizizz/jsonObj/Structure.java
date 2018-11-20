package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

/**
 * @author mrichards
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.quizizz.QuizizzRepository;

public class Structure {

@SerializedName("kind")
@Expose
String type;

 @SerializedName("options")
 @Expose
 private List< Option > options = new ArrayList< Option >();


 @SerializedName("query")
 @Expose
 private Query query;


 @SerializedName("answer")
 @Expose
 private List < Integer > answer = new ArrayList < Integer > ();

 public List < Option > getOptions() {
  return options;
 }

 public void setOptions(List < Option > options) {
  this.options = options;
 }

 public Query getQuery() {
  return query;
 }

 public void setQuery(Query query) {
  this.query = query;
 }

 public List < Integer > getAnswer() {
  return answer;
 }

 public void setAnswer(List < Integer > answer) {
  this.answer = answer;
 }


 @Override
 public String toString() {
  return "Structure{" +
          "type='" + type + '\'' +
          ", options=" + options +
          ", query=" + query +
          ", answer=" + answer +
          '}';
 }
}
