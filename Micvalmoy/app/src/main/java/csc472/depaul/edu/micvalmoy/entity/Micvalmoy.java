package csc472.depaul.edu.micvalmoy.entity;

/**
 * @author mrichards
 */

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Option;

@Parcel
public class Micvalmoy {

    User user;

    Quiz quiz;


    /**
     * exam has user answers
     */
    Exam exam;

    List<Category> category = new ArrayList<>();


    List<Course> course = new ArrayList<>();


    //-------------------------------------------------------------

    /**
     * Question object has the answer choices as well as the correct answers
     */
    List<Question> questions = new ArrayList<>();


    //-------------------------------------------------------------

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Micvalmoy{" +
                "user=" + user +
                ", quiz=" + quiz +
                ", exam=" + exam +
                ", category=" + category +
                ", course=" + course +
                ", questions=" + questions +
                '}';
    }
}

