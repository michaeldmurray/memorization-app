package csc472.depaul.edu.micvalmoy.entity;

/**
 * @author mrichards
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.parceler.Parcel;

/*
CREATE TABLE categories (
id INTEGER primary key AUTOINCREMENT,
  name VARCHAR(325) NOT NULL
);


 */
@Parcel
@Entity(tableName = "categories")
public class Category {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    public Long id;

    @ColumnInfo
    public String name;

    @Ignore
    public boolean selected;

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

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Category() {
    }



    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", selected=" + selected +
                '}';
    }
}