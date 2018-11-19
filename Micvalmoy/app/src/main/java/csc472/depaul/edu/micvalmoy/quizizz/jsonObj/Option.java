package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Option {

 @SerializedName("type")
 @Expose
 private String type;
 @SerializedName("hasMath")
 @Expose
 private boolean hasMath;
 @SerializedName("media")
 @Expose
 private List<Object> media = new ArrayList< Object >();
 @SerializedName("text")
 @Expose
 private String text;

 public String getType() {
  return type;
 }

 public void setType(String type) {
  this.type = type;
 }

 public boolean isHasMath() {
  return hasMath;
 }

 public void setHasMath(boolean hasMath) {
  this.hasMath = hasMath;
 }

 public List < Object > getMedia() {
  return media;
 }

 public void setMedia(List < Object > media) {
  this.media = media;
 }

 public String getText() {
  return text;
 }

 public void setText(String text) {
  this.text = text;
 }


 @Override
 public String toString() {
  return "Option{" +
          "type='" + type + '\'' +
          ", hasMath=" + hasMath +
          ", media=" + media +
          ", text='" + text + '\'' +
          '}';
 }
}
