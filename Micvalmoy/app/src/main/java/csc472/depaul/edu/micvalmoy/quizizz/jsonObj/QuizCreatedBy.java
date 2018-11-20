package csc472.depaul.edu.micvalmoy.quizizz.jsonObj;

/**
 * @author mrichards
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizCreatedBy {

    @SerializedName("local")
    @Expose
    Local local;

    @SerializedName("occupation")
    @Expose
    String occupation;

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "QuizCreatedBy{" +
                "local='" + local + '\'' +
                ", occupation='" + occupation + '\'' +
                '}';
    }


    class Local{
        @SerializedName("username")
        @Expose
        String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "Local{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }
}
