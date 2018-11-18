package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.tools.Converters;

/*


CREATE TABLE quizzes (
id INTEGER primary key AUTOINCREMENT,
  name VARCHAR(325) NOT NULL
);

 */

@Entity(tableName = "quizzes")
public class Quiz implements Parcelable , Cloneable{
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    private Long id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private OffsetDateTime createdAt;

    @ColumnInfo
    private OffsetDateTime updatedAt;


    public Quiz() {
        OffsetDateTime date = OffsetDateTime.now();
        this.createdAt = date;
        this.updatedAt = date;
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
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
     * Implemented parcelable and cloneable
     */
    //---------------------------------------------------------------------------------
    @Override
    public Object clone() {
        Parcel parcel = Parcel.obtain();
        this.writeToParcel(parcel, 0);
        byte[] bytes = parcel.marshall();

        Parcel parcel2 = Parcel.obtain();
        parcel2.unmarshall(bytes, 0, bytes.length);
        parcel2.setDataPosition(0);
        return Quiz.CREATOR.createFromParcel(parcel2);
    }

    protected Quiz(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
        description = in.readString();

        String strCreatedAt = in.readString();

        if(strCreatedAt != null){
            createdAt = Converters.toOffsetDateTime(strCreatedAt);
        }

        String strUpdatedAt = in.readString();
        if(strUpdatedAt != null){
            updatedAt = Converters.toOffsetDateTime(strUpdatedAt);
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(name);
        dest.writeString(description);

        if(createdAt != null){
            String strCreatedAt = Converters.fromOffsetDateTime(createdAt);
            dest.writeString(strCreatedAt);
        }else{
            dest.writeString(null);
        }
        if(updatedAt != null){
            String strUpdatedAt = Converters.fromOffsetDateTime(updatedAt);
            dest.writeString(strUpdatedAt);
        }
    else{
        dest.writeString(null);
    }



    }

    @Override
    public int describeContents() {
        return 0;
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