package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class QuizWithCourse{
    @NonNull
    @ColumnInfo(name = "course_id")
    public Long courseId;

    @ColumnInfo(name = "quiz_id")
    public Long quizId;

    @Embedded(prefix = "q_")
    public Quiz quiz;

    @Embedded(prefix = "crse_")
    public Course course;



    /**
     * Basic getters /setters
     */
    @NonNull
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(@NonNull Long courseId) {
        this.courseId = courseId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
