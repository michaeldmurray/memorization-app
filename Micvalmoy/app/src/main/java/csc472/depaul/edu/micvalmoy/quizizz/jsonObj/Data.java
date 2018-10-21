package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data {

 @SerializedName("hits")
 @Expose
 private List<Hit> hits = new ArrayList< Hit >();

 public List <Hit> getHits() {
  return hits;
 }

 public void setHits(List < Hit > hits) {
  this.hits = hits;
 }

}
