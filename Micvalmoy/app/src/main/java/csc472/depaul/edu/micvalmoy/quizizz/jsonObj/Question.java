package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

 @SerializedName("type")
 @Expose
 private String type;

 @SerializedName("structure")
 @Expose
 private Structure structure;

 @SerializedName("createdAt")
 @Expose
 private String createdAt;

 @SerializedName("_id")
 @Expose
 private String id;

 public String getType() {
  return type;
 }

 public void setType(String type) {
  this.type = type;
 }

 public Structure getStructure() {
  return structure;
 }

 public void setStructure(Structure structure) {
  this.structure = structure;
 }

 public String getCreatedAt() {
  return createdAt;
 }

 public void setCreatedAt(String createdAt) {
  this.createdAt = createdAt;
 }

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }


 @Override
 public String toString() {
  return "Question{" +
          "type='" + type + '\'' +
          ", structure=" + structure.toString() +
          ", createdAt='" + createdAt + '\'' +
          ", id='" + id + '\'' +
          '}';
 }
}
