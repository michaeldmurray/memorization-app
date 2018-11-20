package csc472.depaul.edu.micvalmoy.entity;

/**
 * @author mrichards
 */

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.parceler.Parcel;

/*

CREATE TABLE courses (
id INTEGER primary key AUTOINCREMENT,
  name VARCHAR(325) NOT NULL
);

*/


@Parcel
@Entity(tableName = "courses")
public class Course {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    public Long id;

    @ColumnInfo
    public String name;

    public Course() {
    }

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


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}