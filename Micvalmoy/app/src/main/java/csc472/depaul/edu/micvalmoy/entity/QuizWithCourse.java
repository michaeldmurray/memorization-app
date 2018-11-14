package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;

import java.util.List;

public class QuizWithCourse extends Quiz {

    @Embedded(prefix = "crse_")
    public Course course;

    @Ignore
    public List<Course> courses; // = new ArrayList<>();




    /**
     * Basic getters /setters
     */

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }



    //------------------------------------
    public void addCourse(Course course) {
        courses.add(course);
    }



    //------------------------------------


    @Override
    public String toString() {
        return "Quiz{" +
                " id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}'+
                "QuizWithCourse{" +
                "courses=" + courses.toString() +
                ", courses=" + courses.toString() +
                '}';
    }
}
