package csc472.depaul.edu.micvalmoy.entity;

/**
 * @author mrichards
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.parceler.Parcel;
import org.parceler.ParcelClass;
import org.parceler.ParcelPropertyConverter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


import csc472.depaul.edu.micvalmoy.tools.OffsetDateTimeParcelConverter;

/*


CREATE TABLE quizzes (
id INTEGER primary key AUTOINCREMENT,
  name VARCHAR(325) NOT NULL
);

 */

@Parcel
@Entity(tableName = "quizzes")
public class Quiz {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    public Long id;

    @ColumnInfo
    public String name;

    @ColumnInfo
    public String description;

    @ParcelPropertyConverter(OffsetDateTimeParcelConverter.class)
    @ColumnInfo
    public OffsetDateTime createdAt;

    @ParcelPropertyConverter(OffsetDateTimeParcelConverter.class)
    @ColumnInfo
    public OffsetDateTime updatedAt;

    String quizzizz_id;


    //---------------------------------------------------------------------------------
    @Ignore
    public List<Question> questionList = new ArrayList<>();

    @Ignore
    public List<Category> categoryList = new ArrayList<>();

    @Ignore
    public List<Course> courseList = new ArrayList<>();
    //---------------------------------------------------------------------------------




    public Quiz() {
        OffsetDateTime date = OffsetDateTime.now();
        this.createdAt = date;
        this.updatedAt = date;
    }

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

    public String getQuizzizz_id() {
        return quizzizz_id;
    }

    public void setQuizzizz_id(String quizzizz_id) {
        this.quizzizz_id = quizzizz_id;
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

//------------------------------------------

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", quizzizz_id='" + quizzizz_id + '\'' +
                ", questionList=" + questionList +
                ", categoryList=" + categoryList +
                ", courseList=" + courseList +
                '}';
    }

}