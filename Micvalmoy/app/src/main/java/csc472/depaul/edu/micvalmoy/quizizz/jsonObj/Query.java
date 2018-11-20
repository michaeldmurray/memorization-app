package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

/**
 * @author mrichards
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query {

    @SerializedName("text")
    @Expose
    private String text;

    //text or html, etc
    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("hasMath")
    @Expose
    private boolean hasMath;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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

    @Override
    public String toString() {
        return "Query{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", hasMath=" + hasMath +
                '}';
    }
}
