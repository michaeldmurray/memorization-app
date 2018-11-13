package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Ignore;

import java.util.ArrayList;
import java.util.List;

public class QuizWithCategory extends Quiz {

    @Embedded(prefix = "category_")
    public Category category;


    @Ignore
    public List<Category> categories; //  = new ArrayList<>();


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Basic getters /setters
     */



    //------------------------------------
    public void addCategory(Category category) {
        categories.add(category);
    }


    //------------------------------------
    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}'+
                "QuizWithCategory{" +
                "categories=" + categories.toString() +
                '}';
    }
}
