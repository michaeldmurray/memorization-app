package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {

 @SerializedName("text")
 @Expose
 private String text;

 public String getText() {
  return text;
 }

 public void setText(String text) {
  this.text = text;
 }

    @Override
    public String toString() {
        return "Query{" +
                "text='" + text + '\'' +
                '}';
    }
}
