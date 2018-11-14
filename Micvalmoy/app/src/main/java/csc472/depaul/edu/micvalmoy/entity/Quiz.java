package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/*


CREATE TABLE quizzes (
id INTEGER primary key AUTOINCREMENT,
  name VARCHAR(325) NOT NULL
);

 */

@Entity(tableName = "quizzes")
public class Quiz implements Parcelable {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    private Long id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String description;

    public Quiz( ) {
    }

    //---------------------------------------------------------------------------------
    @Ignore
    public List<Question> questionList = new ArrayList<>();

    @Ignore
    public List<Category> categoryList = new ArrayList<>();

    @Ignore
    public List<Course> courseList = new ArrayList<>();
    //---------------------------------------------------------------------------------

    /**
     * Basic getters /setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }


//---------------------------------------------------------------------------------
    /**
     * Implemented parcelable
     */
    //---------------------------------------------------------------------------------



    public static Creator<Quiz> getCREATOR() {
        return CREATOR;
    }
    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };


    @Ignore
    protected Quiz(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }
    //------------------------------------------

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", questionList=" + questionList.toString() +
                ", categoryList=" + categoryList.toString() +
                ", courseList=" + courseList.toString() +
                '}';
    }
}